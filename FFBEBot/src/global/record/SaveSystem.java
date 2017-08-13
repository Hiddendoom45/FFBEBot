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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Library.summon.Unit;
import XML.Attribute;
import XML.XMLStAXFile;
import XML.Elements;
import global.Main;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
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
			preloadExvius(null);
			preloadReddit(null);
			writeData();
		}
		if(!new File("units").exists()){
			preloadSummons(null);
		}
		setDailyTime();
		Settings.executor.scheduleWithFixedDelay(new Runnable(){
			public void run(){
				setDailyTime();
			}
		},Settings.dailyTime+86400000-System.currentTimeMillis(),86400000,TimeUnit.MILLISECONDS);
		load();
	}
	/**
	 * preloads reddit data
	 * @param count counter to use if triggered manually to count progress
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
	/**
	 * updates data with any new units
	 */
	public static boolean updateReddit(Counter count){
		Gson overviews=new Gson();
		RedditOverview.unitData[] overview=RedditOverview.preloadReddit();
		Data.redditO=overviews.toJson(overview);
		JsonObject units=new JsonParser().parse(Data.redditUnits).getAsJsonObject();
		if(overview.length-units.entrySet().size()==0){
			count.terminate();
			return false;
		}
		if(!(count==null)){count.setMessage("Updating Reddit Units...(%count%/"+(overview.length-units.entrySet().size())+")");}
		int index=0;
		for(RedditOverview.unitData u:overview){
			try{
				if(units.get(u.name)==null){//only add if it's not a unit that it has had before
					index++;
					units.add(u.name, overviews.toJsonTree(new RedditUnit(u.unitUrl)));
					System.out.println("updated "+u.name);
					if(!(count==null)){count.setI(index);}
				}
			}catch(Exception e){
				Log.logError(e);
			}
		}
		
		if(!(count==null)){count.terminate();}
		Data.redditUnits=overviews.toJson(units);
		Log.log("System", "Reddit Overview Updated");
		return true;
	}
	
	/**
	 * preloads exvicus units
	 * @param count counter to use if triggered manually to count progress
	 */
	public static void preloadExvius(Counter count){
		Gson overviews=new Gson();
		UnitOverview.unitData[] overview=UnitOverview.preload();
		Data.exvicusO=overviews.toJson(overview);
		JsonObject units=new JsonObject();
		int index=0;
		if(!(count==null)){count.setMessage("Loading Exvius Units...(%count%/"+overview.length+")");}
		
		for(UnitOverview.unitData u:overview){
			if(!u.isNew){//avoid red text new entries containing nothing
				try{
					units.add(u.name,overviews.toJsonTree(new UnitInfo(u.unitUrl)));
				}catch(Exception e){
					Log.logError(e);
					units.add(u.name, overviews.toJsonTree(getExviusUnit(u.name)));
				}
				System.out.println("preloaded "+u.name);
			}
			index++;
			if(!(count==null)){count.setI(index);}

		}
		if(!(count==null)){count.terminate();}
		Data.exvicusUnits=overviews.toJson(units);
		Log.log("System", "Exvius Overview Loaded");
	}
	public static boolean updateExvius(Counter count){
		Gson overviews=new Gson();
		UnitOverview.unitData[] overview=UnitOverview.preload();
		Data.exvicusO=overviews.toJson(overview);
		JsonObject units=new JsonParser().parse(Data.exvicusUnits).getAsJsonObject();
		if(overview.length-units.entrySet().size()==0){
			count.terminate();
			return false;
		}
		int index=0;
		if(!(count==null)){count.setMessage("Updating Exvius Units...(%count%/"+(overview.length-units.entrySet().size())+")");}
		
		for(UnitOverview.unitData u:overview){
			try{
				if(units.get(u.name)==null&&u.isNew==false){//if unit is not in array and is nonNew
					units.add(u.name, overviews.toJsonTree(new UnitInfo(u.unitUrl)));
					if(!(count==null)){count.setI(index);}
				}
			}catch(Exception e){
				Log.logError(e);
			}
		}
		if(!(count==null)){count.terminate();}
		Data.exvicusUnits=overviews.toJson(units);
		Log.log("System", "Exvius Overview Updated");
		return true;
	}
	/**
	 * preloads summons
	 * @param count counter to use if triggered manually to count progress
	 */
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
	public static boolean updateSummons(){
		new File("units").mkdir();
		if(new File("units").listFiles().length-Unit.values().length==0){
			return false;
		}
		for(Unit u:Unit.values()){
			if(!new File("units/"+u.name).exists()){
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
			}
		}
		return true;
	}
	/**
	 * Gets reddit unit from the JSON string
	 * @param name name of the unit to get
	 * @return Reddit unit object of the unit
	 */
	public static RedditUnit getRedditUnit(String name){
		return new Gson().fromJson(new JsonParser().parse(Data.redditUnits).getAsJsonObject().get(name),RedditUnit.class);
	}
	/**
	 * Gets exvicus unit from the JSON string
	 * @param name name of the unit to get
	 * @return Unit info object of the unit
	 */
	public static UnitInfo getExviusUnit(String name){
		return new Gson().fromJson(new JsonParser().parse(Data.exvicusUnits).getAsJsonObject().get(name),UnitInfo.class);
	}
	/**
	 * Writes preloaded data
	 */
	public static void writeData(){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.preloadData));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		if(doc==null){
			doc=new Elements("root");
		}
		file.endReader();
		try{
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("preload")){
				doc.getChilds().remove(i);
			}
		}
		}catch(Exception e){
			Log.log("ERROR", "no elements to remove for preload");
		}
		doc.add(Data.parseDataToElements());
		if(!file.writeXMLFile())Log.log("XMLERR", "something went wrong with starting to write new file");
		if(!file.startWriter())Log.log("XMLERR", "something went wrong with starting the XML writer");
		if(!file.writeElement(doc))Log.log("XMLERR", "something went wrong wtih writing the document");		
		if(!file.endWriter())Log.log("XMLERR", "something went wrong with closing the XML writer");
	}
	/**
	 * Loads saved data from file
	 */
	public static void load(){
		loadGuilds();//load guilds
		//load users
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
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
	/**
	 * Loads guild info from file
	 */
	public static void loadGuilds(){
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
		file.endReader();
	}
	public static void setDailyTime(){
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long Current=System.currentTimeMillis();
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
		int mili=cal.get(Calendar.MILLISECOND);
		hour-=9;
		if(hour<0){
			hour+=24;
		}
		Settings.dailyTime=Current-(hour*3600000)-(minute*60000)-(second*1000)-(mili);
	}
	/**
	 * Get the locally saved user data
	 * @param id id of the user
	 * @return Data object representing user data
	 */
	public static Data getUser(String id){
		if(Data.users.containsKey(id)){
			return Data.users.get(id);
		}
		else{
			return new Data(id);
		}
	}
	/**
	 * Gets all users which are recorded by bot(called at least once in any method) i.e. lapis
	 * @return vector of all users the bot is tracking
	 */
	public static Vector<Data> getRegisteredUsers(){
		Vector<Data> users=new Vector<Data>();
		for(String key:Data.users.keySet()){
			users.add(Data.users.get(key));
		}
		return users;
	}
	/**
	 * Set the user data to local save
	 * @param user user data to save locally
	 */
	public static void setUser(Data user){
		Data.users.put(user.id, user);
		//pushUserData();
	}
	/**
	 * Unlike guilds user data is saved locally until this is called, wherein it is written to the data file
	 * This is due to the nature that guilds are more likely to be called more often
	 */
	public static void pushUserData(){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("user")){
				if(Data.users.containsKey(doc.getChilds().get(i).getAttribute("id").getValue())){
					doc.getChilds().remove(i);
					i--;
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
		DriveManager.update(new DriveFile(Settings.dataSource,DataEnum.FFBEData.id));
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
					i--;//decrement due to something being removed
				}
			}
		}
		doc.getChilds().add(guild.parseToElements());
		file.writeXMLFile();
		file.startWriter();
		file.writeElement(doc);
		file.endWriter();
		DriveManager.update(new DriveFile(Settings.dataSource,DataEnum.FFBEData.id));
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
		if(event.isFromType(ChannelType.PRIVATE)){
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
		if(event.isFromType(ChannelType.PRIVATE)){
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
	public static boolean hasPermission(MessageReceivedEvent event,Permission permission){
		return hasPermission(event.getGuild(),(Channel)event.getChannel(),permission);
	}
	public static boolean hasPermission(Guild guild,Channel channel,Permission permission){
		List<Permission> permissions=guild.getMemberById(Main.jda.getSelfUser().getId()).getPermissions(channel);
		for(Permission s:permissions){
			if(s.equals(permission)){
				return true;
			}
		}
		return false;
	}
	public static String[] getOverrides() {
		String input="";
		try {
			if(new File(Settings.overrideSource).exists()){//to avoid error
			BufferedReader in=new BufferedReader(new FileReader(new File(Settings.overrideSource)));
			while(in.ready()){
				input+=in.readLine()+",";
			}
			in.close();
			}
		} catch (IOException e) {
			Log.logError(e);
		}
		if(input.length()>0)input=input.substring(0, input.length()-1);
		String[] overrides=input.split(",");
		return overrides;
	}
	/**
	 * remove an override string so that it cannot be used again
	 * @param overide override string to remove
	 */
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
		if(input.length()>1)input=input.substring(0, input.length()-1);
		try{
			BufferedWriter out=new BufferedWriter(new FileWriter(new File(Settings.overrideSource)));
			out.write(input);
			out.close();
		}catch(Exception e){
			
		}
	}
}
