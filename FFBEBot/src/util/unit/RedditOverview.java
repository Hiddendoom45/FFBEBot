package util.unit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		unitName = unitName.toLowerCase();
		try{
			int uNum=-1;
			if(Lib.isNumber(unitName)){
				uNum=Lib.extractNumber(unitName);
			}
			for(unitData u:new Gson().fromJson(Data.redditO,unitData[].class)){
				if(uNum>-1&&u.unitID==uNum||UnitAlias.testAlias(unitName, u.unitID)){
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
					doc = Jsoup.connect("https://old.reddit.com/r/FFBraveExvius/wiki/units").userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(Exception e){Log.logError(e);}
			}
			Element data=doc.getElementsByClass("wiki-page-content").first().getElementsByClass("wiki").get(0);
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
		public String dbUrl;
		public String famitsuUrl;
		public int unitID;
		public String name;
		public String JPname;
		public String origin;
		public String role;
		public int baseR;
		public int maxR;
		public String family;
		public String enhanceBatch;
		public unitData(Element row){
			try{
				unitID = Integer.parseInt(row.child(0).text());
			}catch(NumberFormatException e){
				try{
				Matcher m = Pattern.compile("\\Q#I/Icons/u\\E([\\d]+)\\/").matcher(row.child(1).getElementsByTag("a").attr("href"));
				if(m.matches()){
					unitID = Integer.parseInt(m.group(1));
				}
				else{
					throw new Exception("Does not match");
				}
				}catch(Exception e1){
					unitID = -1;
				}
			}
			for(Element e:row.child(3).getElementsByTag("a")){
				String url = e.absUrl("href");
				if(url.startsWith("https://old.reddit.com/r/FFBraveExvius/")){
					unitUrl = nullURLCheck(url);
				}
				else if (url.startsWith("https://exvius.gg/")||url.startsWith("https://exviusdb.com/")){
					dbUrl = url;
				}
				else if(url.startsWith("https://wiki.famitsu.com/")){
					famitsuUrl = url;
				}
			}
			String[] names = Lib.parseText(row.child(2)).split("\n");
			name=names[0].trim();
			JPname=names[1].trim();
			origin=row.child(4).text();
			role=row.child(5).text();
			family=row.child(6).text();
			baseR=(int)row.child(7).text().codePoints().filter(c -> c=='★').count();
			maxR=baseR+(int)row.child(7).text().codePoints().filter(c -> c=='✰').count();
			enhanceBatch = row.child(8).text();
		}
		private String nullURLCheck(String url){
			if(url.endsWith("/tg")){
				return null;
			}
			return url;
		}
	}
}
