package global.record;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

import Library.summon.banner.Banner;
import util.Lib;
import util.ModuleController;
import XML.Attribute;
import XML.Elements;
/**
 * mixed bag of static settings for runtime and also used as the object representing a guild(not sure why I'm doing this) 
 * @author Allen
 *
 */
public class Settings {
	//general static settings, stored here to change easily
	public static final String token=Secrets.token;
	public static final String prefix="-!";
	public static final String modPrefix="~!";
	public static final String UA="Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
	public static final String ExvicusURL="http://exvius.gamepedia.com/";
	public static final String ExvicusIMGURL="https://hydra-media.cursecdn.com/exvius.gamepedia.com/";
	public static final String saveSource="FFBEBotLog";//for the log
	public static final String dataSource="FFBEBotData";//for the guild based data
	public static final String preloadData="FFBEBotPreload";//for preloaded data
	public static final String overrideSource="override";//for overrides to allow other people other than owner to executethose commands
	public static final String join="User: %userMention% has joined %guildName%";//default join message
	public static final String overridePrefix="!";//prefix used for override commands
	public static final String overrideArg="-";//prefix used to denote arguments for override commands
	public static final String ownerID="206193542693912578";//ID of owner
	public static final long ID=System.currentTimeMillis();//ID for the bot based on when it was started
	public static final Semaphore upload=new Semaphore(1);//used to prevent bot from uploading 2 things at once, so that it won't end up deleting one of the images
	public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(30);//used for various threaded activities
	public static long dailyTime;
	public static HashMap<String,Settings> guilds=new HashMap<String,Settings>();//map of guilds and settings stored locally for easy access	
	public static Banner DefaultBanner=Banner.DevChoice;//default banner used by summon simulators
	
	//guild settings
	public String guildPrefix="";
	public String guildModPrefix="";
	public String joinMsg="";
	public String joinPM="";
	public String id="";
	public String[] modded=new String[]{};
	public HashMap<String,ModuleController> disabled=new HashMap<String,ModuleController>();//hashmap of disabled modules module/module controller
	public boolean tJoinMsg=false;
	public boolean tJoinPM=false;
	//in preparation for custom messages for each server
	public Settings(String id){
		this.id=id;
	}
	public Settings(Elements root){
		id=root.getAttribute("id").getValue();
		joinMsg=Lib.getString(root,"join");
		guildPrefix=Lib.getString(root,"prefix");
		guildModPrefix=Lib.getString(root,"modPrefix");
		modded=Lib.textArray(root,"modded");
		tJoinMsg=Lib.getBooleanSetting(false,root,"tJoin");
		tJoinPM=Lib.getBooleanSetting(false,root,"tJoinPM");
		for(Elements e:Lib.elementArray(root, "moduleControl")){
			disabled.put(e.getAttribute("name").getValue(), new ModuleController(e));
		}
	}
	
	public Settings addModded(String id){
		if(!Lib.contains(id, modded)){
			String[] newMod=new String[modded.length+1];
			int i=0;
			for(String s:modded){
				newMod[i]=s;
				i++;
			}
			newMod[i]=id;
			modded=newMod;
		}
		return this;
	}
	public Settings removeModded(String id){
		if(Lib.contains(id ,modded)){
			String[] newMod=new String[modded.length-1];
			int i=0;
			for(String s:modded){
				if(!s.equals(id)){
					newMod[i]=s;
					i++;
				}
			}
			modded=newMod;
		}
		return this;
	}
	public Elements parseToElements(){
		Elements root=new Elements("guild");
		root.getAttributes().add(new Attribute("id",id));
		
		Elements settings=new Elements("settings");
		root.add(settings);
		
		Elements join=new Elements("join");
		join.setText(joinMsg);
		settings.add(join);
		
		
		Elements mPrefix=new Elements("modPrefix");
		mPrefix.setText(guildModPrefix);
		settings.add(mPrefix);
		
		Elements prefix=new Elements("prefix");
		prefix.setText(guildPrefix);
		settings.add(prefix);
		
		for(String s:modded){
			Elements mod=new Elements("modded").setText(s);
			root.add(mod);
		}
		
		for(String s:this.disabled.keySet()){
			root.add(this.disabled.get(s).parseToElements());
		}
		
		Elements toggle=new Elements("toggle");
		root.add(toggle);
		
		Elements tJoin=new Elements("tJoin").setText(""+tJoinMsg);
		toggle.add(tJoin);
		
		Elements tJoinPM=new Elements("tJoinPM").setText(""+this.tJoinPM);
		toggle.add(tJoinPM);
		
		return root;
	}
	
}
