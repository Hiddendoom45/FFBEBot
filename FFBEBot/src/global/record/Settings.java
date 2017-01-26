package global.record;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

import util.Lib;

import XML.Attribute;
import XML.Elements;
/**
 * mixed bag of static settings for runtime and also used as the object representing a guild(not sure why I'm doing this) 
 * @author Allen
 *
 */
public class Settings {
	public static final String token=Secrets.testToken;
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
	public static final Semaphore upload=new Semaphore(1);//used to prevent bot from uploading 2 things at once, so that it won't end up deleteing one of the images
	public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);//used for various threaded activities
	public static HashMap<String,Settings> guilds=new HashMap<String,Settings>();//map of guilds and settings stored locally for easy access

	public static String redditO;
	public static String redditUnits;
	public static String exvicusO;
	public static String exvicusUnits;
	public static boolean useAveragePing=true;
	
	public String guildPrefix="";
	public String guildModPrefix="";
	public String joinMsg="";
	public String joinPM="";
	public String id="";
	public String[] modded=new String[]{};
	public boolean tJoinMsg=false;
	public boolean tJoinPM=false;
	//in preparation for custom messages for each server
	public Settings(String id){
		this.id=id;
	}
	public Settings(Elements root){
		id=root.getAttribute("id").getValue();
		joinMsg=getString(root,"join");
		guildPrefix=getString(root,"prefix");
		guildModPrefix=getString(root,"modPrefix");
		modded=textArray(root,"modded");
		tJoinMsg=getBooleanSetting(false,root,"tJoin");
		tJoinPM=getBooleanSetting(false,root,"tJoinPM");
	}
	/**
	 * A wrapper for getting an array for an element easily, assuming the arrays is text separated by ','
	 * @param ele element to search for array
	 * @param setting name of the element whose text is the array
	 * @return
	 */
	private String[] textArray(Elements ele,String setting){
		String[] out=new String[ele.getChilds(setting).size()];
		int i=0;
		for(Elements e:ele.getChilds(setting)){
			out[i]=e.getText();
			i++;
		}
		return out;
	}
	/**
	 * A wrapper to get String value for element without crashing process if it doesn't exist
	 * @param root Element you want to search for the element
	 * @param tagname name of element you want to get
	 * @return
	 */
	private static String getString(Elements root,String tagname){
		try{
			return root.getChilds(tagname).get(0).getText();
		}
		catch(IndexOutOfBoundsException e){
			Log.logError(e);
			return "";
		}
	}
	/**
	 * A wrapper to get the boolean value for an element without crashing process if it doesn't exist
	 * @param normal default value if boolean value is not found
	 * @param ele Element you want to search for the element
	 * @param tagname name of element you want to get
	 * @return
	 */
	public boolean getBooleanSetting(boolean normal,Elements ele,String tagname){
		if(ele.getChilds(tagname).size()<=0){
			return normal;
		}
		if(normal){
			return ele.getChilds(tagname).get(0).getText().equals("false")?false:true;
		}
		else{
			return ele.getChilds(tagname).get(0).getText().equals("true")?true:false;
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
		
		Elements toggle=new Elements("toggle");
		root.add(toggle);
		
		Elements tJoin=new Elements("tJoin").setText(""+tJoinMsg);
		toggle.add(tJoin);
		
		Elements tJoinPM=new Elements("tJoinPM").setText(""+this.tJoinPM);
		toggle.add(tJoinPM);
		
		return root;
	}
	public static void setData(Elements preload){
		Settings.redditO=getString(preload,"redditOverview");
		Settings.redditUnits=getString(preload,"redditUnits");
		Settings.exvicusO=getString(preload,"exvicusOverview");
		Settings.exvicusUnits=getString(preload,"exvicusUnits");
	}
	public static Elements parseDataToElements(){
		Elements root=new Elements("preload");
		Elements current;
		
		current=new Elements("redditOverview").setText(Settings.redditO);
		root.add(current);
		
		current=new Elements("redditUnits").setText(Settings.redditUnits);
		root.add(current);
		
		current=new Elements("exvicusOverview").setText(Settings.exvicusO);
		root.add(current);
		
		current=new Elements("exvicusUnits").setText(Settings.exvicusUnits);
		root.add(current);
		
		return root;
	}
}
