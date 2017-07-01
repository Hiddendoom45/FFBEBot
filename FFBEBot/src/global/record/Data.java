package global.record;



import java.util.ArrayList;
import java.util.HashMap;

import Library.pulls.PullUnit;
import XML.Attribute;
import XML.Elements;
import util.Lib;

/**
 * Class for holding data variables related to preloading and it is the class used to represent te data for a user
 * @author Allen
 *
 */
public class Data {
	public static String redditO;
	public static String redditUnits;
	public static String exvicusO;
	public static String exvicusUnits;
	public static HashMap<String,Data> users=new HashMap<String,Data>();
	
	public String id;//userID
	public int lapis=0;//lapis user has
	public long dailyTime;
	public long dailyPullTime;
	public boolean base5guarantee=false;
	public int SacredCrystal;
	public ArrayList<PullUnit> units=new ArrayList<PullUnit>();//stores units pulled in order of when they were pulled
	
	
	public Data(String id){
		this.id=id;
	}
	public Data(Elements root){
		id=root.getAttribute("id").getValue();//this should always be there, so no error checking
		lapis=Lib.getNumber(root,"lapis")==-1?0:Lib.getNumber(root,"lapis");
		dailyTime=Lib.getLong(root, "dailyTime")==-1?0:Lib.getLong(root, "dailyTime");
		dailyPullTime=Lib.getLong(root, "dailyPullTime")==-1?0:Lib.getLong(root, "dailyPullTime");
		base5guarantee=Lib.getBooleanSetting(false, root, "base5guarantee");
		SacredCrystal=Lib.getNumber(root, "SC")==-1?0:Lib.getNumber(root, "SC");
		ArrayList<Elements> units=root.getChilds("Unit");
		for(Elements e:units){
			this.units.add(new PullUnit(e));
		}
	}
	public boolean dailyReady(){
		if(dailyTime>Settings.dailyTime){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean dailyPullReady(){
		if(dailyPullTime>Settings.dailyTime){
			return false;
		}
		else{
			return true;
		}
	}
	public Elements parseToElements(){
		Elements root=new Elements("user");
		root.getAttributes().add(new Attribute("id",id));
		Elements current;
		
		
		Elements currency=new Elements("currency");
		current=new Elements("lapis",""+lapis);
		currency.add(current);
		current=new Elements("dailyTime",""+dailyTime);
		currency.add(current);
		current=new Elements("dailyPullTime",""+dailyPullTime);
		currency.add(current);
		current=new Elements("base5guarantee",""+base5guarantee);
		currency.add(current);
		current=new Elements("SC",""+SacredCrystal);
		currency.add(current);
		root.add(currency);
		
		Elements unit=new Elements("units");
		for(PullUnit u:units){
			unit.add(u.parseToElements());
		}
		root.add(unit);
		
		return root;
	}
	
	//static stuff in relation to preload stuff
	public static void setData(Elements preload){
		redditO=Lib.getString(preload,"redditOverview");
		redditUnits=Lib.getString(preload,"redditUnits");
		exvicusO=Lib.getString(preload,"exvicusOverview");
		exvicusUnits=Lib.getString(preload,"exvicusUnits");
	}
	public static Elements parseDataToElements(){
		Elements root=new Elements("preload");
		Elements current;
		current=new Elements("redditOverview").setText(redditO);
		root.add(current);
		
		current=new Elements("redditUnits").setText(redditUnits);
		root.add(current);
		
		current=new Elements("exvicusOverview").setText(exvicusO);
		root.add(current);
		
		current=new Elements("exvicusUnits").setText(exvicusUnits);
		root.add(current);
		
		return root;
	}
}
