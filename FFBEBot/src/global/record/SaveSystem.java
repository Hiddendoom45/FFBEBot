package global.record;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Library.summon.Unit;
import XML.Attribute;
import XML.XMLStAXFile;
import XML.Elements;
import global.Main;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Counter;
import util.unit.RedditOverview;
import util.unit.RedditUnit;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
/**
 * Class containing all the static methods relating to saving/loading data for the bot
 * @author Allen
 *
 */
public class SaveSystem {
	/**
	 * loads basic data
	 */
	public static void setup(){
		if(!new File(Settings.dataSource).exists()){
			List<Guild> guilds=Main.jda.getGuilds();
			Elements root=new Elements("FFBEBot");
			for(Guild g:guilds){
				root.add(new Settings(g.getId()).parseToElements());
			}
			XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
			file.writeXMLFile();
			file.startWriter();
			file.writeElement(root);
			file.endWriter();
		}
		if(!new File(Settings.preloadData).exists()){
			XMLStAXFile file=new XMLStAXFile(new File(Settings.preloadData));
			file.writeXMLFile();
			file.startWriter();
			file.endWriter();
			preloadSummons(null);
			preloadExvicus(null);
			preloadReddit(null);
			writeData();
		}
		load();
	}
	/**
	 * preloads reddit data
	 * @param count counter used to count progress
	 */
	public static void preloadReddit(Counter count){
		
		Gson overviews=new Gson();
		RedditOverview.unitData[] overview=RedditOverview.preloadReddit();
		Data.redditO=overviews.toJson(overview);
		JsonObject units=new JsonObject();
		int index=0;
		if(!(count==null)){count.setMessage("Loading Reddit Units...(%count%/"+overview.length+")");}
		
		for(RedditOverview.unitData u:overview){
			try{
			units.add(u.name, overviews.toJsonTree(new RedditUnit(u.unitUrl)));
			}catch(Exception e){
				Log.logError(e);
				units.add(u.name,  overviews.toJsonTree(getRedditUnit(u.name)));
			}
			System.out.println("preloaded "+u.name);
			index++;
			if(!(count==null)){count.setI(index);}
		}
		
		if(!(count==null)){count.terminate();}
		Data.redditUnits=overviews.toJson(units);
		Log.log("System", "Reddit Overview Loaded");
	}
	public static RedditUnit getRedditUnit(String name){
		return new Gson().fromJson(new JsonParser().parse(Data.redditUnits).getAsJsonObject().get(name),RedditUnit.class);
	}
	public static void preloadExvicus(Counter count){
		Gson overviews=new Gson();
		UnitOverview.unitData[] overview=UnitOverview.preload();
		Data.exvicusO=overviews.toJson(overview);
		JsonObject units=new JsonObject();
		int index=0;
		if(!(count==null)){count.setMessage("Loading Exvicus Units...(%count%/"+overview.length+")");}
		
		for(UnitOverview.unitData u:overview){
			try{
				units.add(u.name,overviews.toJsonTree(new UnitInfo(u.unitUrl)));
			}catch(Exception e){
				Log.logError(e);
				units.add(u.name, overviews.toJsonTree(getExvicusUnit(u.name)));
			}
			System.out.println("preloaded "+u.name);
			index++;
			if(!(count==null)){count.setI(index);}
		}
		if(!(count==null)){count.terminate();}
		Data.exvicusUnits=overviews.toJson(units);
		Log.log("System", "Exvicus Overview loaded");
	}
	public static UnitInfo getExvicusUnit(String name){
		return new Gson().fromJson(new JsonParser().parse(Data.exvicusUnits).getAsJsonObject().get(name),UnitInfo.class);
	}
	public static void writeData(){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.preloadData));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		try{
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("preload")){
				doc.getChilds().remove(i);
			}
		}
		}catch(Exception e){
			Log.log("ERRRO", "no elements to remove for preload");
		}
		doc.add(Data.parseDataToElements());
		if(!file.writeXMLFile())Log.log("XMLERR", "something went wrong with starting to write new file");
		if(!file.startWriter())Log.log("XMLERR", "something went wrong with starting the XML writer");
		if(!file.writeElement(doc))Log.log("XMLERR", "something went wrong wtih writing the document");		
		if(!file.endWriter())Log.log("XMLERR", "something went wrong with closing the XML writer");
	}
	public static void preloadSummons(Counter count){
		int index=0;
		if(!(count==null)){count.setMessage("Loading Summoned Units...(%count%/"+Unit.values().length+")");}
		new File("units").mkdir();
		for(Unit u:Unit.values()){
			int i=u.base;
			for(String url:u.url){
				new File("units/"+u.name).mkdir();
				try{
				URL input=new URL(url);
				HttpURLConnection connection = (HttpURLConnection) input.openConnection();
				connection.setRequestProperty("User-Agent",Settings.UA);
				BufferedImage image=ImageIO.read(connection.getInputStream());
				ImageIO.write(image, "PNG",new File("units/"+u.name+"/"+i+".png"));
				}catch(Exception e){
					Log.logError(e);
				}
				i++;
			}

			for(String url:u.upgradeurl){
				try{
				URL input=new URL(url);
				HttpURLConnection connection = (HttpURLConnection) input.openConnection();
				connection.setRequestProperty("User-Agent",Settings.UA);
				ImageIO.write(ImageIO.read(connection.getInputStream()), "PNG",new File("units/"+u.name+"/"+i+".png"));
				}catch(Exception e){
					Log.logError(e);
				}
				i++;
			}
			index++;
			if(!(count==null)){count.setI(index);}
		}
		if(!(count==null)){count.terminate();}
	}
	/**
	 * Loads saved data from file
	 */
	public static void load(){
		Settings.guilds.clear();
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		try{
		ArrayList<Elements> guilds=file.parseToElements("guild");
		for(Elements e:guilds){
			try{
			Settings.guilds.put(e.getAttribute("id").getValue(), new Settings(e));
			}catch(Exception e1){
				Log.log("ERROR", "error putting guild(likely missing id attribute)"+e);
				Log.logError(e1);
			}
		}
		}catch(Exception e){
			Log.log("ERROR", "error loading guilds");
		}
		file.resetReader();
		ArrayList<Elements> users=file.parseToElements("user");
		for(Elements e:users){
			try{
				Data.users.put(e.getAttribute("id").getValue(), new Data(e));
			}catch(Exception e2){
				Log.log("ERROR", "error putting user(likely missing id attribute)"+e);
				Log.logError(e2);
			}
		}
		file.readNewXMLFile(new File(Settings.preloadData));
		try{
			Elements preload=file.parseToElements("preload").get(0);
			Data.setData(preload);
		}catch(Exception e){
			Log.log("ERROR", "error loading preload data");
		}
		file.endReader();
	}
	public static Data getUser(String id){
		if(Data.users.containsKey(id)){
			return Data.users.get(id);
		}
		else{
			return new Data(id);
		}
	}
	public static void setUser(Data user){
		Data.users.put(user.id, user);
	}
	public static void pushUserData(){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("user")){
				if(Data.users.containsKey(doc.getChilds().get(i).getAttribute("id").getValue())){
					doc.getChilds().remove(i);
				}
			}
		}
		for(String key:Data.users.keySet()){
			doc.getChilds().add(Data.users.get(key).parseToElements());
		}
		file.writeXMLFile();
		file.startWriter();
		file.writeElement(doc);
		file.endWriter();
	}
	public static Settings getGuild(String id){
		try{
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements guild=file.parseToElements(new Attribute("id",id)).get(0);
		file.endReader();
		return new Settings(guild);
		}
		catch(Exception e){
			return new Settings(id);
		}
	}
	public static String getSetting(String id,String tag){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements guild=file.parseToElements(new Attribute("id",id)).get(0);
		String s= guild.getChilds(tag).get(0).getText();
		file.endReader();
		return s;
	}
	public static void setSetting(Settings guild){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("guild")){
				if(doc.getChilds().get(i).getAttribute("id").getValue().equals(guild.id)){
					doc.getChilds().remove(i);
				}
			}
		}
		doc.getChilds().add(guild.parseToElements());
		file.writeXMLFile();
		file.startWriter();
		file.writeElement(doc);
		file.endWriter();
	}
	public static String getJoin(GuildMemberJoinEvent event){
		return getJoin(event.getGuild());
	}
	public static String getJoin(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String join=Settings.guilds.get(guild.getId()).joinMsg;
			if(join.equals("")){
				return Settings.join;
			}
			else{
				return join;
			}
		}
		else{
			return Settings.join;
		}
	}
	public static String getPrefix(MessageReceivedEvent event){
		if(event.isPrivate()){
			return Settings.prefix;
		}
		else{
			return getPrefix(event.getGuild());
		}
	}
	public static String getPrefix(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String prefix=Settings.guilds.get(guild.getId()).guildPrefix;
			if(prefix.equals("")){
				return Settings.prefix;
			}
			else{
				return prefix;
			}
		}
		else{
			return Settings.prefix;
		}
		
	}
	public static String getModPrefix(MessageReceivedEvent event){
		if(event.isPrivate()){
			return Settings.modPrefix;
		}
		else{
			return getModPrefix(event.getGuild());
		}
	}
	public static String getModPrefix(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String modPrefix=Settings.guilds.get(guild.getId()).guildModPrefix;
			if(modPrefix.equals("")){
				return Settings.modPrefix;
			}
			else{
				return modPrefix;
			}
		}
		else{
			return Settings.modPrefix;
		}
	}
	public static String[] getOverrides() {
		String input="";
		try {
			BufferedReader in=new BufferedReader(new FileReader(new File(Settings.overrideSource)));
			while(in.ready()){
				input+=in.readLine()+",";
			}
			in.close();
		} catch (IOException e) {
			Log.logError(e);
		}
		input=input.substring(0, input.length()-1);
		String[] overrides=input.split(",");
		return overrides;
	}
	public static void removeOverride(String overide){
		String input="";
		try {
			BufferedReader in=new BufferedReader(new FileReader(new File(Settings.overrideSource)));
			while(in.ready()){
				String current=in.readLine();
				if(!current.equals(overide)){
					input+=current+"\n";
				}
			}
			in.close();
		} catch (IOException e) {
			Log.logError(e);
		}
		if(input.length()>1){
			input=input.substring(0, input.length()-1);
		}
		try{
			BufferedWriter out=new BufferedWriter(new FileWriter(new File(Settings.overrideSource)));
			out.write(input);
			out.close();
		}catch(Exception e){
			
		}
	}
}
