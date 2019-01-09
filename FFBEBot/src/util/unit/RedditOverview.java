package util.unit;

import java.util.ArrayList;
import java.util.TreeMap;
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
	public static final TreeMap<Integer,unitAlias> alias = new TreeMap<Integer,unitAlias>();
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
				if(uNum>-1&&u.unitID==uNum
						||u.name.toLowerCase().contains(unitName)
						||u.JPname.toLowerCase().contains(unitName)
						||(alias.containsKey(u.unitID)&&alias.get(u.unitID).check(unitName))){
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
		public String dbUrl;
		public String famitsuUrl;
		public int unitID;
		public String name;
		public String JPname;
		public String origin;
		public String role;
		public int baseR;
		public int maxR;
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
				if(url.startsWith("https://www.reddit.com/r/FFBraveExvius/")){
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
	public static void buildAlias(){
		alias.put(126, new unitAlias(126,new String[]{"cod"}));
		alias.put(222, new unitAlias(222,new String[]{"wol"}));
		alias.put(385, new unitAlias(385,new String[]{"majin fina","dark fina"}));
		alias.put(387, new unitAlias(387,new String[]{"eru","elle"}));
		alias.put(390, new unitAlias(390,new String[]{"ruruka","luka"}));
		alias.put(396, new unitAlias(396,new String[]{"orlandeau","orlandu"}));
		alias.put(445, new unitAlias(445,new String[]{"soze","sohze"}));
		alias.put(448, new unitAlias(448,new String[]{"heretic","heltich"}));
		alias.put(451, new unitAlias(451,new String[]{"eileen","aileen"}));
		alias.put(493, new unitAlias(493,new String[]{"summer fina","beach time fina"}));
		alias.put(496, new unitAlias(496,new String[]{"summer majin fina","seabreeze dark fina"}));
		alias.put(513, new unitAlias(513,new String[]{"forren","fohlen"}));
		alias.put(518, new unitAlias(518,new String[]{"illus","ilias"}));
		alias.put(540, new unitAlias(540,new String[]{"runera","lunera"}));
		alias.put(573, new unitAlias(573,new String[]{"dark veritas","veritas of the dark"}));
		alias.put(575, new unitAlias(575,new String[]{"fire veritas","veritus of the flame"}));
		alias.put(577, new unitAlias(577,new String[]{"earth veritas","veritas of the earth"}));
		alias.put(600, new unitAlias(600,new String[]{"light veritas","veritas of the light"}));
		alias.put(602, new unitAlias(602,new String[]{"wind veritas","veritas of the heavens"}));
		alias.put(605, new unitAlias(605,new String[]{"water veritas","veritas of the waters"}));
		alias.put(610, new unitAlias(610,new String[]{"grom","guromu"}));
		alias.put(649, new unitAlias(649,new String[]{"lorraine","loren"}));
		alias.put(656, new unitAlias(656,new String[]{"sylvia","silvia"}));
		alias.put(726, new unitAlias(726,new String[]{"orif","olif"}));
		alias.put(729, new unitAlias(729,new String[]{"mistair","mystea"}));
		alias.put(732, new unitAlias(732,new String[]{"shaly","charie"}));
		alias.put(766, new unitAlias(766,new String[]{"ouga","ohga"}));
		alias.put(772, new unitAlias(772,new String[]{"cg lasswell","pyro glacial lasswell","pg lasswell"}));
		alias.put(793, new unitAlias(793,new String[]{"cg sakura","blossom sage sakura","bs sakura"}));
		alias.put(795, new unitAlias(795,new String[]{"vern","verun"}));
		alias.put(798, new unitAlias(798,new String[]{"sedona","cedona"}));
		alias.put(815, new unitAlias(815,new String[]{"cg fina","lotus mage fina"}));
		alias.put(819, new unitAlias(819,new String[]{"wado","wadow"}));
		alias.put(843, new unitAlias(843,new String[]{"cg jake","nameless gunner jake","no good jake","ng jake"}));
		alias.put(875, new unitAlias(875,new String[]{"kid rydia","pure summoner rydia"}));
		alias.put(886, new unitAlias(886,new String[]{"cg lid","heavenly technician lid","ht lid"}));
		alias.put(910, new unitAlias(910,new String[]{"silt","shylt"}));
		alias.put(916, new unitAlias(916,new String[]{"ch nichol","maritime strategist nichol","ms nichol"}));
		alias.put(918, new unitAlias(918,new String[]{"lekisa","lexa"}));
		alias.put(921, new unitAlias(921,new String[]{"elvis","elbis"}));
		alias.put(941, new unitAlias(941,new String[]{"cg reagan","raegen"}));
		alias.put(943, new unitAlias(943,new String[]{"luminui","ryumynui"}));
		alias.put(946, new unitAlias(946,new String[]{"xyle","zile"}));
		alias.put(952, new unitAlias(952,new String[]{"wandering rain","vagrant knight rain"}));
		alias.put(965, new unitAlias(965,new String[]{"lamira","ramira"}));
		alias.put(989, new unitAlias(989,new String[]{"nal","nalu"}));
		alias.put(992, new unitAlias(992,new String[]{"pezzotta","peccoitta"}));
		alias.put(1038, new unitAlias(1038,new String[]{"cg hyou","cg hyoh"}));
		alias.put(1086, new unitAlias(1086,new String[]{"cg citra","citra"}));
		alias.put(1089, new unitAlias(1089,new String[]{"makmedi","macmedi"}));
		alias.put(1092, new unitAlias(1092,new String[]{"lottie","lotti"}));
		alias.put(1171, new unitAlias(1171,new String[]{"cg sieghart","sieghard"}));
		alias.put(1177, new unitAlias(1177,new String[]{"theobalt","theobald"}));
		alias.put(1180, new unitAlias(1190,new String[]{"kanon","canon"}));
	}
	public static class unitAlias{
		@SuppressWarnings("unused")
		private int id;//unused for now, may be useful in serializing this
		private String[] alias;
		private unitAlias(int id,String[] alias){
			this.id = id;
			this.alias=alias;
		}
		public boolean check(String unit){
			for(String s:alias){
				if(s.contains(unit)) return true;
			}
			return false;
		}
	}
}
