package global.record;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import util.Lib;

import XML.Attribute;
import XML.Elements;
/**
 * mixed bag of static settings for runtime and also used as the object representing a guild(not sure why I'm doing this) 
 * @author Allen
 *
 */
public class Settings {
	public static final String token=Secrets.token;
	public static final String prefix="-!";
	public static final String modPrefix="~!";
	public static final String UA="Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
	public static final String ExvicusURL="https://exviuswiki.com/";
	public static final String saveSource="FFBEBotLog";//for the log
	public static final String dataSource="FFBEBotData";//for the guild based data
	public static final String overrideSource="override";
	public static final String join="User: %userMention% has joined %guildName%";
	public static final String overridePrefix="!";
	public static final String overrideArg="-";
	public static final long ID=System.currentTimeMillis();//ID for the bot based on when it was started
	public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);//used for various threaded activities
	public static HashMap<String,Settings> guilds=new HashMap<String,Settings>();//map of guilds and settings stored locally for easy access
	
	public String guildPrefix="";
	public String guildModPrefix="";
	public String defaultSite="";
	public String joinMsg="";
	public String joinPM="";
	public String id="";
	public String[] modded;
	public boolean tJoinMsg=true;
	public boolean tJoinPM=false;
	//in preparation for custom messages for each server
	public Settings(String id){
		this.id=id;
	}
	public Settings(Elements root){
		id=root.getAttribute("id").getValue();
		joinMsg=getSetting(root,"join");
		guildPrefix=getSetting(root,"prefix");
		guildModPrefix=getSetting(root,"modPrefix");
		defaultSite=getSetting(root,"defaultSite");
		modded=textArray(root,"modded");
		tJoinMsg=getBooleanSetting(true,root,"tJoin");
		tJoinPM=getBooleanSetting(false,root,"tJoinPM");
	}
	private String[] textArray(Elements ele,String setting){
		String[] out=new String[ele.getChilds(setting).size()];
		int i=0;
		for(Elements e:ele.getChilds(setting)){
			out[i]=e.getText();
			i++;
		}
		return out;
	}
	private String getSetting(Elements set,String setting){
		try{
			return set.getChilds(setting).get(0).getText();
		}
		catch(IndexOutOfBoundsException e){
			return "";
		}
	}
	public boolean getBooleanSetting(boolean normal,Elements ele,String setting){
		if(ele.getChilds(setting).size()<=0){
			return normal;
		}
		if(normal){
			return ele.getChilds(setting).get(0).getText().equals("false")?false:true;
		}
		else{
			return ele.getChilds(setting).get(0).getText().equals("true")?true:false;
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
		
		Elements dSite=new Elements("defaultSite");
		dSite.setText(defaultSite);
		settings.add(dSite);
		
		Elements toggle=new Elements("toggle");
		root.add(toggle);
		
		Elements tJoin=new Elements("tJoin").setText(""+tJoinMsg);
		toggle.add(tJoin);
		
		Elements tJoinPM=new Elements("tJoinPM").setText(""+this.tJoinPM);
		toggle.add(tJoinPM);
		
		
		return root;
	}
}
