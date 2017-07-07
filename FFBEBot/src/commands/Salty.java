package commands;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Library.summon.Unit;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.unit.UnitOverview;

public class Salty extends CommandGenerics implements Command, Selection {
	//used to keep track of current selections going on
	private HashMap<Long,UnitOverview> saved=new HashMap<Long,UnitOverview>();
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try{
			if(args.length>0){
				UnitOverview overview=new UnitOverview(args.length>0?args[0].toLowerCase():"null");
				ArrayList<String> possible =new ArrayList<String>();//possible image links
				ArrayList<String> name=new ArrayList<String>();//names for the image links
				possible=overview.getNames();
				name=overview.possible;
				if(possible.size()>1){
					long ID=System.currentTimeMillis();
					saved.put(ID, overview);
					Select select=new Select(possible, 0, this, name);
					select.ID=ID;
					Selector.setSelection(select, event);
				}
				else if(possible.size()>0){
					sendImage(event, overview.getData(0).imgUrl);
				}
				else{
					Lib.sendMessage(event, "Unit not found");
				}
			}
			else{
				help(event);
			}
		}
		catch(Exception e){
			Log.logError(e);
		};
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"salty [unitname]\n"
				+ "\tcreates a salty image\n"
				+ "\t[unitname] unit you're salty about(has to be a GL unit)(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		System.out.println("3");
		try{
			Log.log("status", "salty image of "+chosen.names.get(chosen.selected)+" sent");
			sendImage(event, saved.get(chosen.ID).getData(chosen.selected).imgUrl);
		}catch(Exception e){
			Log.logError(e);
		}
	}
	@Override
	public int getInputType() {
		return 0;
	}
	public static void sendImage(MessageReceivedEvent event,String imgurl)throws IOException{
		BufferedImage large=null;
	    large = ImageIO.read(new URL("https://pbs.twimg.com/profile_images/671501876265803776/-M6ppcKt.jpg").openStream());
	    BufferedImage small=null;
	    boolean found=false;
	    try{
	    for(Unit u:Unit.values()){
	    	if(u.upgradeurl.length==0){
	    		if(u.url[u.url.length-1].equals(imgurl)){
	    			small=ImageIO.read(new File("units/"+u.name+"/"+(u.baseRarity()+u.url.length)));
	    			found=true;
	    		}
	    	}
	    	else{
	    		if(u.upgradeurl[u.upgradeurl.length-1].equals(imgurl)){
	    			small=ImageIO.read(new File("units/"+u.name+"/"+(u.baseRarity()+u.upgradeurl.length)));
	    			found=true;
	    		}
	    	}
	    }
	    }catch(Exception e){
	    	Log.log("ERROR", "unable to read unit image from data for "+imgurl);
	    }
	    if(!found){
	    	URL url=new URL(imgurl);
	    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    	connection.setRequestProperty("User-Agent",Settings.UA);
	    	small = ImageIO.read(connection.getInputStream());
	    }

	    int w = Math.max(large.getWidth(), small.getWidth());
	    int h = Math.max(large.getHeight(), small.getHeight());

	    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    // paint both images, preserving the alpha channels
	    Graphics g = combined.getGraphics();
	    g.drawImage(large, 0, 0, null);
	    g.drawImage(small, (large.getWidth()/2)-(small.getWidth()/2)-20, (large.getHeight()/2)-(small.getHeight()/2)+10, null);
	    try {
			Settings.upload.acquire();
		} catch (InterruptedException e) {}
	    ImageIO.write(combined, "PNG", new File("salty.png"));
	    Lib.sendFile(event, "null", new File("salty.png"));
	    Settings.upload.release();
	    Files.delete(new File("salty.png").toPath());
	}

}
