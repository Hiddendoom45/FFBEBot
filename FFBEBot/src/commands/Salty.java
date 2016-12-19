package commands;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;

public class Salty implements Command, Selection {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", "Salty image of "+(args.length>0?args[0]:"")+" sent to "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild().getName()));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length==0){
			Lib.sendMessage(event, "https://exviuswiki.com/Unit_List");
		}
		else{
			Document doc = null;
			try{
				while(true){
					doc = Jsoup.connect("https://exviuswiki.com/Unit_List").userAgent(Settings.UA).timeout(10000).get();
					if(!(doc==null))break;
				}
				ArrayList<String> possible =new ArrayList<String>();//possible image links
				ArrayList<String> name=new ArrayList<String>();//names for the image links
				Elements first=doc.getElementsByTag("tbody");//get tables
				Elements units = Lib.getNested(first, "tr");//get rows
				for(Element unit:units){
					if(unit.getElementsByTag("td").size()>0){//if has data in rows
						Elements eunit=new Elements();
						eunit.add(unit);
						Element eName=Lib.getNestedItem(eunit, 1, "td").first();//get second cell, unit name
						if(eName.text().toLowerCase().contains(args.length>0?args[0].toLowerCase():"null")){
							Element eSource=Lib.getNestedItem(eunit, 0, "td").first();//get image source
							possible.add(eSource.getElementsByAttribute("src").get(0).attr("src").replace(" ", "_"));
							name.add(eName.text());
						}
					}
				}
				if(possible.size()>1){
					Select select=new Select(possible, 0, this, name);
					Selector.setSelection(select, event);
				}
				else if(possible.size()>0){
					sendImage(event, Settings.ExvicusURL+possible.get(0).replace(" ", "_"));
				}
				else{
					Lib.sendMessage(event, "Unit not found");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			};
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"salty [unit]"
				+ "creates a salty image of the specified unit";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {

	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		try{
			Log.log("status", "salty image of "+chosen.names.get(chosen.selected)+" sent");
			sendImage(event, Settings.ExvicusURL+chosen.options.get(chosen.selected).replace(" ", "_"));
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
	    URL url=new URL(imgurl);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestProperty("User-Agent",Settings.UA);
	    small = ImageIO.read(connection.getInputStream());

	    int w = Math.max(large.getWidth(), small.getWidth());
	    int h = Math.max(large.getHeight(), small.getHeight());

	    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    // paint both images, preserving the alpha channels
	    Graphics g = combined.getGraphics();
	    g.drawImage(large, 0, 0, null);
	    g.drawImage(small, (large.getWidth()/2)-(small.getWidth()/2)-20, (large.getHeight()/2)-(small.getHeight()/2)+10, null);

	    ImageIO.write(combined, "PNG", new File("twoInOne.png"));
	    event.getChannel().sendFile(new File("twoInOne.png"),null );
	    Files.delete(new File("twoInOne.png").toPath());
	}

}
