package commands;

import java.awt.Color;
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
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.summon.Pull;

public class Summon implements Command {
	private static int width=160;
	private static int height=200;
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("FORBIDDEN", "Summon wrath evoked by "+event.getAuthorName());
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0&&Lib.isNumber(args[0])){
			
			sendImage(event, Pull.pull(Integer.parseInt(args[0])));
		}
		else{
			Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
					+ "\n\tsummons [amount] units from the rare summon pool");
		}
		
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
			int w;
			if(units.size()<5){
				w=units.size()*width;
			}
			else {
				w=width*5;
			}
		int h=height*(((units.size()-1)/5)+1);
		BufferedImage base=new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Dimension size=new Dimension(0,0);
		Graphics g=base.getGraphics();
		g.setColor(Color.yellow);
		for(SummonedUnit s:units){
			BufferedImage small=null;
			URL url=new URL(s.url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent",Settings.UA);
			small = ImageIO.read(connection.getInputStream());
			System.out.println(small.getWidth()+","+small.getHeight());
			int sx=(width/2-(small.getWidth()));
			int sy=(height-small.getHeight()*2)-24;
			BufferedImage stand;
			if(s.rarity==3){
				stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/3star.png"));
			}
			else if(s.rarity==4){
				stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/4star.png"));
			}
			else if(s.rarity==5){
				stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/5star.png"));
			}
			else{
				stand=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/none.png"));
			}
			BufferedImage back=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/BG.png"));
			if(size.width==0&&size.height%5==0){
				g.drawImage(back,size.width,size.height,width*5,height*5,null);
			}
			BufferedImage star=ImageIO.read(getClass().getResourceAsStream("/Lib/summon/raritystar.png"));
			g.drawImage(stand, size.width, size.height+32,null);
			g.drawImage(small, sx+size.width, sy+size.height,(small.getWidth()*2),(small.getHeight()*2), null);
			int starSize=20;
			for(int i=0;i<s.rarity;i++){
				int ry=size.height+height-starSize;
				int rx=size.width+((width-(s.rarity*starSize))/2+(starSize*i));
				g.drawImage(star,rx,ry,starSize,starSize,null);
			}

			//String rare="";
			//for(int i=0;i<s.rarity;i++){
			//	rare+="★";
			//}
			//g.drawString(rare, size.width, size.height+height-5);
			increment(size);
			System.out.println(size);
		}
		ImageIO.write(base, "PNG", new File("summons.png"));
		event.getChannel().sendFile(new File("summons.png"),null );
		Files.delete(new File("summons.png").toPath());
		} catch (IOException e) {
			Log.logError(e);
		}
	}
	private Dimension increment(Dimension size){
		if(size.width<width*4){
			size.width+=width;
		}
		else{
			size.width=0;
			size.height+=height;
		}
		return size;
	}

}
