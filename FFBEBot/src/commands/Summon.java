package commands;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Lib.summon.SummonedUnit;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;
import util.rng.summon.Pull;

public class Summon implements Command {
	private static int width=160;
	private static int height=200;
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("FORBIDDEN", "Summon wrath evoked by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
			public void run(){
				if(args.length>0&&Lib.isNumber(args[0])){
					int num=Integer.parseInt(args[0]);
					sendImage(event, Pull.pull(num));
				}
				else{
					Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
							+ "\n\tsummons [amount] units from the rare summon pool");
				}
			}
		});
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
				+ "\n\tsummons [amount] units from the rare summon pool");
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void sendImage(MessageReceivedEvent event, ArrayList<SummonedUnit> units){
		try {
			//setup the size of the base image
			int columns=5;
			if(units.size()>25){
				columns=(int) Math.sqrt(units.size()-1)+1;
			}
			int w;
			if(units.size()<columns){
				w=units.size()*width;
			}
			else {
				w=width*columns;
			}
			int h=height*(((units.size()-1)/columns)+1);
			
			//setup main variables
			int index=0;
			Counter count=new Counter("Summoning Units...(%count%/"+units.size()+")",event);
			BufferedImage base=new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Dimension size=new Dimension(0,0);//used to determine position of rectangle bounding unit(top/right corner position)
			Graphics g=base.getGraphics();// graphics used for everything
			for(SummonedUnit s:units){
				
				//Load image for the unit, small is the unit image variable
				BufferedImage small=null;
				if(s.getImageLocation().exists()){//use preloaded image if available
					small=ImageIO.read(s.getImageLocation());
				}
				else{
					URL url=new URL(s.url);//get image stuffs
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestProperty("User-Agent",Settings.UA);//to bypass https
					small = ImageIO.read(connection.getInputStream());
				}
				//System.out.println(small.getWidth()+","+small.getHeight());//Keeping it here if I need to check max sizes of images
				
				
				int sx=(width/2-(small.getWidth()));//shift to centre unit
				int sy=(height-small.getHeight()*2)-24;//shift to make unit stand on stand
				BufferedImage stand;//stand determined by rarity of unit
				if(s.rarity==3){
					stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/3star.png"));
				}
				else if(s.rarity==4){
					stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/4star.png"));
				}
				else if(s.rarity==5){
					stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/5star.png"));
				}
				else{//error case
					stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/none.png"));
				}
				
				
				BufferedImage back=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/BG.png"));//draw background
				if(size.width==0&&size.height%(height*5)==0){
					g.drawImage(back,size.width,size.height,width*columns,height*columns,null);
				}
				
				//draw stuff
				g.drawImage(stand, size.width, size.height+32,null);
				g.drawImage(small, sx+size.width, sy+size.height,(small.getWidth()*2),(small.getHeight()*2), null);
				//star stuffs
				BufferedImage star=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/raritystar.png"));
				int starSize=20;//square size of stars
				for(int i=0;i<s.rarity;i++){//draw the stars, one by one, centered
					int ry=size.height+height-starSize;//stars at very bottom of rectangle
					int rx=size.width+((width-(s.rarity*starSize))/2+(starSize*i));//star location determined by # and how many in total centres it
					g.drawImage(star,rx,ry,starSize,starSize,null);//draw the star
				}

				//String rare="";
				//for(int i=0;i<s.rarity;i++){
				//	rare+="★";
				//}
				//g.drawString(rare, size.width, size.height+height-5);
				increment(size,columns);
				count.setI(index);
				index++;
			}
			if(units.size()>250){
				base=compress(base);
			}
			g.dispose();
			count.setMessage("Uploading...");
			try {
				Settings.upload.acquire();
			} catch (InterruptedException e) {}
			ImageIO.write(base, "PNG", new File("summons.png"));
			event.getChannel().sendFile(new File("summons.png"),new MessageBuilder().appendString(
					Lib.FormatMessage(event, "%userMention% summoned "+units.size()+" units from the rare summon banner")).build());
			Settings.upload.release();
			Files.delete(new File("summons.png").toPath());
			count.terminate();
		} catch (IOException e) {
			Log.logError(e);
		}
	}
	private BufferedImage compress(BufferedImage compress){
		BufferedImage base=new BufferedImage(compress.getWidth()/2, compress.getHeight()/2, BufferedImage.TYPE_INT_ARGB);
		Graphics g=base.getGraphics();
		g.drawImage(compress, 0, 0, compress.getWidth()/2,compress.getHeight()/2,null);
		g.dispose();
		return base;
	}
	private Dimension increment(Dimension size,int columns){
		if(size.width<width*(columns-1)){
			size.width+=width;
		}
		else{
			size.width=0;
			size.height+=height;
		}
		return size;
	}

}
