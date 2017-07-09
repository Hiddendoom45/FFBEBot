package util.unit;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Library.ElementFilter;
import global.record.Log;
import global.record.Settings;
import util.Lib;
import util.unit.exvius.unitAbilities;
import util.unit.exvius.unitQuotes;
import util.unit.exvius.unitStatIncrease;
import util.unit.exvius.unitStats;

public class UnitInfo {
	public String URL="";
	public String loreOverview="";
	public String unitName="";
	public String imgOverviewURL="";
	public int minRarity=0;
	public int maxRarity=0;
	public String job="";
	public String role="";
	public String origin="";
	public String gender="";
	public String race;
	public int[] No=new int[]{};
	public String trustName="";
	public String trustLink="";
	public String trustDetail="";
	public unitStats stats;
	public unitStatIncrease statIncrease;
	public String[] weapons=new String[]{};
	public String[] armours=new String[]{};
	public unitAbilities Special;
	public unitAbilities Magic;
	public String[] sprites=new String[]{};
	public String[] awakening=new String[]{};
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
			Element content=doc.getElementById("mw-content-text");
			try{
				loreOverview=content.getElementsByTag("p").first().text();
			}catch(Exception e){
				Log.log("ERROR", "overview lore retrieval failed for page:" +page);
				Log.logShortError(e, 5);
			}
			try{
				Element unitInfo=content.getElementsByTag("tbody").first();
				unitName=Lib.getHeader(0, unitInfo).text();
				imgOverviewURL=Lib.getCell(1, 0, unitInfo).child(0).absUrl("src");
				parseRarities(Lib.getCell(2, 0, unitInfo).text());
				job=Lib.getCell(3, 0, unitInfo).text();
				role=Lib.getCell(4, 0, unitInfo).text();
				origin=Lib.getCell(8, 0, unitInfo).text();
				gender=Lib.getCell(9, 0, unitInfo).text();
				race=Lib.getCell(10, 0, unitInfo).text();
				String[] no=Lib.getCell(11, 0, unitInfo).text().split(",");
				No=new int[no.length];
				for(int i=0;i<no.length;i++){
					try{
						No[i]=Integer.parseInt(no[i].trim());
					}catch(NumberFormatException e){
						No[i]=0;
					}
				}
				trustName=Lib.getCell(12, 0, unitInfo).text();
				if(Lib.getCell(12, 0, unitInfo).childNodeSize()>0)
					trustLink=Lib.getCell(12, 0, unitInfo).child(0).absUrl("href");

				Document doc2=Jsoup.connect(trustLink).userAgent(Settings.UA).get();
				parseTrust(doc2.getElementById("mw-content-text").children());
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
				Element statIncrease=Lib.getEleAfter(content.children(),new ElementFilter("h3","Maximum Stats Increase [edit | edit source]")).getElementsByTag("tbody").first();
				this.statIncrease=new unitStatIncrease(statIncrease);
			}
			catch(Exception e){
				Log.log("ERROR", "error parsing stat increases for page:"+page);
				Log.logShortError(e, 5);
			}
			try{
				Element equipment=Lib.getEleAfter(content.children(), new ElementFilter("h3","Equipment[edit | edit source]"));
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
				awakening=new String[maxRarity-minRarity];
				for(int i=0;i<awakening.length;i++){
					awakening[i]=awaken.getElementsByTag("tbody").first().getElementsByTag("tr").get(1).getElementsByTag("td").get(i).text();
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing awakening mats for page:" +page);
				Log.logShortError(e, 5);
			}
			try{

				Element quote=Lib.getEleAfter(content.children(), new ElementFilter("h2","Quotes[edit | edit source]"));
				background=new unitQuotes(quote.getElementsByClass("tabbertab").get(0).getElementsByTag("table").first().getElementsByTag("tbody").first());
				fusionQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(1).getElementsByTag("table").first().getElementsByTag("tbody").first());
				awakeningQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(2).getElementsByTag("table").first().getElementsByTag("tbody").first());
				summonQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(3).getElementsByTag("table").first().getElementsByTag("tbody").first());
				TMQuotes=new unitQuotes(quote.getElementsByClass("tabbertab").get(4).getElementsByTag("table").first().getElementsByTag("tbody").first());
			}catch(Exception e){
				Log.log("ERROR", "error parsing unit quotes for page:"+page);
				Log.logShortError(e, 5);
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	public void parseTrust(Elements trustStuff){
		boolean h2Trig=false;
		for(Element e:trustStuff){
			if(h2Trig){
				if(e.tagName().equals("h2")||e.tagName().equals("h3")){
					if(trustDetail.endsWith(", "))trustDetail=trustDetail.substring(0, trustDetail.length()-2);
					return;
				}
				else{
					trustDetail+=e.text()+", ";
				}
			}
			if(e.tagName().equals("h2")&&(e.text().equals("Statistics[edit | edit source]")||e.text().equals("Statistics"))){
				h2Trig=true;
			}
		}
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