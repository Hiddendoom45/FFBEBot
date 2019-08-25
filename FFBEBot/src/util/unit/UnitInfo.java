package util.unit;

import java.io.IOException;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Library.ElementFilter;
import global.record.Log;
import global.record.Settings;
import util.Lib;
import util.unit.exvius.*;

public class UnitInfo {
	public String URL="";
	public String loreOverview="";
	public String unitName="";
	public String imgOverviewURL="";
	public int minRarity=0;
	public int maxRarity=0;
	public String job="";
	public String role="";
	public String[] chains= new String[]{};
	public String origin="";
	public String gender="";
	public String race;
	public int[] No=new int[]{};
	public String trustName="";
	public String trustLink="";
	public TrustInfo trustDetails;
	public String sTrustName="";
	public String sTrustLink="";
	public TrustInfo sTrustDetails;
	public unitStats stats;
	public unitStatIncrease statIncrease;//TODO delete once preload files are updated
	public String[] weapons=new String[]{};
	public String[] armours=new String[]{};
	public unitAbilities Special;
	public unitAbilities Magic;
	public String[] sprites=new String[]{};
	public AwakenInfo[] awakening = new AwakenInfo[]{};
	public unitQuotes background;
	public unitQuotes fusionQuotes;
	public unitQuotes awakeningQuotes;
	public unitQuotes summonQuotes;
	public unitQuotes TMQuotes;
	public UnitInfo(String page)throws IOException{
		if(page.contains("redlink")){//to avoid all the error recording for nonexistant pages, due to wiki using new page creation, logs all sorts of errors that are irrelvent
			return;
		}
		try{
			Document doc=null;
			for(int i=0;i<4;i++){
				try{
					doc = Jsoup.connect(page).userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(org.jsoup.HttpStatusException e1){
					if(i==3){
						Log.log("ERROR", "page doesn't exist:"+page);
						return;
					}
				}
				catch(Exception e){Log.logError(e);}
			}
			URL=page;
			Element content=doc.selectFirst("#mw-content-text > .mw-parser-output");
			try{
				loreOverview=content.getElementsByTag("p").first().text();
			}catch(Exception e){
				Log.log("ERROR", "overview lore retrieval failed for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element unitInfo=content.select(".ibox > tbody").first();
				int rows = unitInfo.select(":root > tr").size();
				unitName=Lib.getHeader(0, unitInfo).text();
				imgOverviewURL=Lib.getCell(1, 0, unitInfo).child(0).absUrl("src");
				parseRarities(Lib.getCell(2, 0, unitInfo).text());
				job=Lib.getCell(3, 0, unitInfo).text();
				Elements roles = Lib.getCell(4, 0, unitInfo).select("a[title]");
				role=roles.stream().map(e -> e.attr("title")).collect(Collectors.joining(", "));
				if(rows>14)chains=Lib.parseText(Lib.getCell(5, 0, unitInfo)).split("\n");
				origin=Lib.getCell(rows>14?9:8, 0, unitInfo).text();
				gender=Lib.getCell(rows>14?10:9, 0, unitInfo).text();
				race=Lib.getCell(rows>14?11:10, 0, unitInfo).text();
				String[] no=Lib.getCell(rows>14?12:11, 0, unitInfo).text().split(",");
				No=new int[no.length];
				for(int i=0;i<no.length;i++){
					try{
						No[i]=Integer.parseInt(no[i].trim());
					}catch(NumberFormatException e){
						No[i]=0;
					}
				}
				trustName=parseRaw(Lib.getCell(rows>14?13:12, 0, unitInfo));
				if(!trustName.equalsIgnoreCase("- ")){
					trustLink=Lib.getCell(rows>14?13:12, 0, unitInfo).child(0).getElementsByTag("a").first().absUrl("href");
					Document doc2=Jsoup.connect(trustLink).userAgent(Settings.UA).get();
					trustDetails=new TrustInfo(doc2.selectFirst("#mw-content-text > .mw-parser-output").children());
				}
				Element sTRaw = Lib.getCell(14, 0, unitInfo);
				if(!(sTRaw==null)){
					sTrustName=parseRaw(sTRaw);
					sTrustLink=Lib.getCell(14, 0, unitInfo).child(0).getElementsByTag("a").first().absUrl("href");
					Document doc3=Jsoup.connect(sTrustLink).userAgent(Settings.UA).get();
					sTrustDetails=new TrustInfo(doc3.selectFirst("#mw-content-text > .mw-parser-output").children());
				}
			}catch(Exception e){
				Log.log("ERROR", "Error parsing overview box for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element stats=Lib.getEleAfter(content.children(), new ElementFilter("h3","Stats [edit | edit source]")).getElementsByTag("tbody").first();
				this.stats=new unitStats(stats);
			}catch(Exception e){
				Log.log("ERROR", "error parsing stats for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element statIncreaseSec=Lib.getEleAfter(content.children(),new ElementFilter("h3","Maximum Stats Increase [edit | edit source]"));
				if(statIncreaseSec!=null){
					Element statIncrease=statIncreaseSec.getElementsByTag("tbody").first();
					this.stats.setIncreases(statIncrease);
				}
			}
			catch(Exception e){
				Log.log("ERROR", "error parsing stat increases for page:"+page);
				Log.logShortError(e, 5);
			}
			try{
				Element equipment=Lib.getEleAfter(content.children(), new ElementFilter("h3","Equipment[edit | edit source]"));
				while(!equipment.tagName().equals("table")){
					equipment=equipment.nextElementSibling();
				}
				parseWeapons(Lib.getCell(1, 0, equipment));
				parseArmours(Lib.getCell(3, 0, equipment));
			}catch(Exception e){
				Log.log("ERROR", "error parsing equipment for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element special=Lib.getEleAfter(content.children(), new ElementFilter("h3","Special[edit | edit source]"));
				if(!(special==null)){
					Special=new unitAbilities(special.getElementsByTag("tbody").first());
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing special abilities for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element magic=Lib.getEleAfter(content.children(), new ElementFilter("h3","Magic[edit | edit source]"));
				if(!(magic==null)){
					Magic=new unitAbilities(magic.getElementsByTag("tbody").first());
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing magic abilities for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element sprites=Lib.getEleAfter(content.children(), new ElementFilter("h2","Sprites[edit | edit source]")).getElementsByTag("tbody").first();
				this.sprites=new String[sprites.children().first().children().size()];
				for(int i=0;i<sprites.children().first().children().size();i++){
					this.sprites[i]=sprites.child(1).child(i).child(0).child(0).absUrl("src");
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing sprites for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element awaken=Lib.getEleAfter(content.children(), new ElementFilter("h2","Awakening Materials[edit | edit source]"));
				awakening=new AwakenInfo[maxRarity-minRarity];
				for(int i=0;i<awakening.length;i++){
					awakening[i] =  new AwakenInfo(Lib.parseText(awaken.getElementsByTag("tbody").first().getElementsByTag("tr").get(1).getElementsByTag("td").get(i)));
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing awakening mats for page:" +page);
				Log.logShortError(e, 5);
			}
			try{

				Element quote=Lib.getEleAfter(content.children(), new ElementFilter("h2","Quotes[edit | edit source]"));
				background=new unitQuotes(quote.getElementsByClass("tabbertab").get(0).select("table > tbody").first());
				fusionQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(1).select("table > tbody").first());
				awakeningQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(2).select("table > tbody").first());
				summonQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(3).select("table > tbody").first());
				TMQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(4).select("table > tbody").first());
			}catch(Exception e){
				Log.log("ERROR", "error parsing unit quotes for page:"+page);
				Log.logShortError(e, 5);
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	public static void main(String[] args) throws IOException{
		UnitInfo u = new UnitInfo("https://exvius.gamepedia.com/Refia");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(u));
	}
	public String parseRaw(Element ele){
		String s="";
		for(Node n:ele.childNodes()){
			if(n instanceof Element){
				Element e= (Element)n;
				if(e.tagName().equals("span")){
					try{
						s+= ele.getElementsByAttributeValue("class", "nomobile").text();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
				else{
					s+=e.text();
				}
				
			}
			else if(n instanceof TextNode){
				s+=n.toString();
			}
		}
		return s;
	}
	public void parseRarities(String text){
		int[] rarity=Lib.extractNumbers(text) ;
		minRarity=rarity[0];
		try{
			maxRarity=rarity[1];
		}catch(ArrayIndexOutOfBoundsException e){
			maxRarity=minRarity;
		}
	}
	public void parseWeapons(Element weapons){
		this.weapons=new String[weapons.children().size()];
		for(int i=0;i<weapons.children().size();i++){
			this.weapons[i]=weapons.child(i).attr("title");
		}	
	}
	public void parseArmours(Element armours){
		this.armours=new String[armours.children().size()];
		for(int i=0;i<armours.children().size();i++){
			this.armours[i]=armours.child(i).attr("title");
		}
	}
}