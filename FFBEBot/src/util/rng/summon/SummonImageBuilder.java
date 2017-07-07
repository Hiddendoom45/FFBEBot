package util.rng.summon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

import Library.summon.UnitSpecific;
import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Counter;

/**
 * Class to build the image used to display units, as it is now used in places other than the summon simulation command
 * @author Allen
 *
 */
public class SummonImageBuilder {
	//values in are defaults
	private int h=200;
	private int w=160;
	private int col=5;
	private double factor;
	private String[] basePlates=new String[]{"/Library/summon/none.png","/Library/summon/3star.png","/Library/summon/4star.png","/Library/summon/5star.png","/Library/summon/6star.png"};
	private ArrayList<UnitSpecific> units=new ArrayList<UnitSpecific>();
	private boolean dynamicRows=false;
	private boolean numbered=false;
	/**
	 * Creates a new summon image builder with parameters specified
	 * @param h h of the unit
	 * @param w w of the unit
	 */
	public SummonImageBuilder(double factor){
		w=(int) (w*factor);
		h=(int) (h*factor);
		this.factor=factor;
	}
	/**
	 * Specifies the number of columns for the units, if not specified defaults to 5, if built dynamically 
	 * then this parameter is ignored
	 * @param col number of columns
	 * @return
	 */
	public SummonImageBuilder setColumnSize(int col){
		this.col=col;
		return this;
	}
	/**
	 * Builds the number of columns dynamically, if set to true it will try to keep the image such that it's shape will be a square
	 * ensures that the col num is floor of squareroot(#units)   
	 * @return
	 */
	public SummonImageBuilder buildColumnsDynamically(){
		dynamicRows=true;
		return this;
	}
	/**
	 * adds the numbers seen when selecting units to the image
	 * @return
	 */
	public SummonImageBuilder buildWithNumbers(){
		numbered=true;
		return this;
	}
	/**
	 * sets the base plate for a specific rarity
	 * @param index index for rarity 0=none, 1==3star, 2==4star etc
	 * @param location file location within jar of the image
	 * @return
	 */
	public SummonImageBuilder basePlate(int index, String location){
		basePlates[index]=location;
		return this;
	}
	/**
	 * Adds the unit to the list of units to build the image for
	 * @return
	 */
	public SummonImageBuilder addUnit(UnitSpecific unit){
		units.add(unit);
		return this;
	}
	/**
	 * Adds the list of units to the list of units to build the image for
	 * @param units
	 * @return
	 */
	public SummonImageBuilder addUnit(Collection<? extends UnitSpecific> units){
		for(UnitSpecific u:units){
			this.units.add(u);
		}
		return this;
	}
	/**
	 * Build the image with the settings specified, returns the image
	 * @return
	 */
	public BufferedImage build(MessageReceivedEvent event,Counter count){
		try {
			//setup the size of the base image
			int columns=col;
			if(dynamicRows){
				if(units.size()>25){
					columns=(int) Math.sqrt(units.size()-1)+1;
				}
			}
			int width;//width of the base image
			if(units.size()<columns){//columns based on num units
				width=units.size()*w;
			}
			else {
				width=w*columns;//else based on colum size
			}
			int height=h*(((units.size()-1)/columns)+1);
			
			//setup main variables
			int index=0;//keep track of current index in list of units
			BufferedImage base=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Dimension size=new Dimension(0,0);//used to determine position of rectangle bounding unit(top/right corner position)
			Graphics g=base.getGraphics();// graphics used for everything
			
			//main loop going through each unit and adding to image
			for(UnitSpecific s:units){
				//Load image for the unit, small is the unit image variable
				BufferedImage small=null;
				if(s.getImageLocation()==null){//use preloaded image if available
					URL url=new URL(s.imgUrl);//get image stuffs
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestProperty("User-Agent",Settings.UA);//to bypass https
					small = ImageIO.read(connection.getInputStream());
				}
				else{
					small=ImageIO.read(s.getImageLocation());
				}
				//System.out.println(small.getw()+","+small.geth());//Keeping it here if I need to check max sizes of images
				//size of stuff with factor
				int sw=(int) (small.getWidth()*factor);
				int sh=(int) (small.getHeight()*factor);
				int yShiftD=(int) (24*factor);//to makeunit stand on stand
				int yShiftStand=(int) (32*factor);//balencing for the stand
				
				int sx=(w/2-(sw));//shift to centre unit
				int sy=(h-sh*2)-yShiftD;//shift to make unit stand on stand
				BufferedImage stand;//stand determined by rarity of unit
				if(s.rarity==3){
					stand=ImageIO.read(getClass().getResourceAsStream(basePlates[1]));
				}
				else if(s.rarity==4){
					stand=ImageIO.read(getClass().getResourceAsStream(basePlates[2]));
				}
				else if(s.rarity==5){
					stand=ImageIO.read(getClass().getResourceAsStream(basePlates[3]));
				}
				else if(s.rarity==6){
					stand=ImageIO.read(getClass().getResourceAsStream(basePlates[4]));
				}
				else{//error case
					stand=ImageIO.read(getClass().getResourceAsStream(basePlates[0]));
				}
				
				if(size.width==0&&size.height%(h*5)==0){//draw background only at specific points
					BufferedImage back=ImageIO.read(getClass().getResourceAsStream("/Library/summon/BG.png"));//draw background
					g.drawImage(back,size.width,size.height,w*columns,h*columns,null);
				}
				//draw stuff
				g.drawImage(stand,size.width, size.height+yShiftStand,(int)(stand.getWidth()*factor) ,(int)(stand.getHeight()*factor),null);
				g.drawImage(small, sx+size.width, sy+size.height,(sw*2),(sh*2), null);
				//star stuffs
				BufferedImage star=ImageIO.read(getClass().getResourceAsStream("/Library/summon/raritystar.png"));
				int starSize=(int) (20*factor);//square size of stars
				for(int i=0;i<s.rarity;i++){//draw the stars, one by one, centered
					int ry=size.height+h-starSize;//stars at very bottom of rectangle
					int rx=size.width+((w-(s.rarity*starSize))/2+(starSize*i));//star location determined by # and how many in total centres it
					g.drawImage(star,rx,ry,starSize,starSize,null);//draw the star
				}
				//draw numbers
				if(numbered){
					//index==num
					int nx=size.width+(int)(112*factor);
					int ny=size.height+(int)(132*factor);
					BufferedImage numback=ImageIO.read(getClass().getResourceAsStream("/Library/summon/unitnumback.png"));
					BufferedImage fnt=ImageIO.read(getClass().getResourceAsStream("/Library/comnum18.png"));
					g.drawImage(numback, nx, ny, null);
					if(index<10){
						//calculate location of number
						int nfx=(int) (nx+(17*factor));
						int nfy=(int)(ny+(8*factor));
						int nfw=(int) (nfx+(15*factor));
						int nfh=(int) (nfy+(18*factor));
						//index of number location in font file
						int fx=index*15;
						int fy=0;
						int fw=15;
						int fh=18;
						g.drawImage(fnt, nfx, nfy, nfw, nfh, fx, fy, fx+fw, fy+fh,  null);
					}
					else{
						//calculate location of number, numerics are shift factors
						int nfx=(int) (nx+(11*factor));
						int nfy=(int)(ny+(7*factor));
						int nfw=(int) (nfx+(15*factor));
						int nfh=(int) (nfy+(18*factor));
						//index of number location in font file
						int fx=index/10*15;
						int fy=0;
						int fw=15;
						int fh=18;
						g.drawImage(fnt,nfx,nfy,nfw,nfh,fx,fy,fx+fw,fy+fh,null);
						//for second digit
						//calculate location of number
						nfx=(int) (nx+(24*factor));
						nfw=(int) (nfx+(15*factor));
						fx=index%10*15;
						g.drawImage(fnt,nfx,nfy,nfw,nfh,fx,fy,fx+fw,fy+fh,null);
					}
					
				}
				//deprecated code to draw stars
				//String rare="";
				//for(int i=0;i<s.rarity;i++){
				//	rare+="★";
				//}
				//g.drawString(rare, size.w, size.h+h-5);
				increment(size,columns);
				if(!(count==null)){
					count.setI(index);
				}
				index++;
			}
			g.dispose();
			return base;
		} catch (IOException e) {
			Log.logError(e);
		}
		return null;
	}
	private Dimension increment(Dimension size,int columns){
		if(size.width<w*(columns-1)){
			size.width+=w;
		}
		else{
			size.width=0;
			size.height+=h;
		}
		return size;
	}
}
