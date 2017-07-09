package util.unit;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import global.record.Data;
import global.record.Log;
import global.record.Settings;
import util.Lib;

public class RedditOverview {
	public ArrayList<String> possible=new ArrayList<String>();
	public ArrayList<unitData> possibleData=new ArrayList<unitData>();
	public RedditOverview(String unitName){
		try{
			for(unitData u:new Gson().fromJson(Data.redditO,unitData[].class)){
				if(u.name.toLowerCase().contains(unitName.toLowerCase())||u.JPname.toLowerCase().contains(unitName.toLowerCase())){
					this.possible.add(u.name);
					this.possibleData.add(u);
				}
				}
			
		}catch(Exception e){
			Log.logError(e);
		}
	}
	public static unitData[] preloadReddit(){
		unitData[] udata = null;
		Document doc = null;
		try{
			for(int i=0;i<10;i++){
				try{
					doc = Jsoup.connect("https://www.reddit.com/r/FFBraveExvius/wiki/units").userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(Exception e){Log.logError(e);}
			}
			Element data=doc.getElementsByClass("wiki").get(0);
			Elements units=data.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			udata=new unitData[units.size()];
			int i=0;
			for(Element u:units){
				udata[i]=new unitData(u);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return udata;
	}
	public ArrayList<String> getNames(){
		return possible;
	}
	public unitData getData(int selection){
		return possibleData.get(selection);
	}
	public static class unitData{
		public String unitUrl;
		public String name;
		public String JPname;
		public String origin;
		public int baseR;
		public int maxR;
		public unitData(Element row){
			unitUrl=row.getElementsByAttribute("href").get(1).absUrl("href");
			name=row.child(2).text();
			JPname=row.child(1).text();
			origin=row.child(3).text();
			baseR=Lib.extractNumber(row.child(4).child(0).attr("href"));
			maxR=Lib.extractNumber(row.child(5).child(0).attr("href"));
		}
		
	}
}
