package util.unit;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Lib.ElementFilter;
import global.record.Log;
import global.record.Settings;
import util.Lib;

public class UnitInfo {
	public String URL;
	public String loreOverview;
	public String unitName;
	public String imgOverviewURL;
	public int minRarity;
	public int maxRarity;
	public String job;
	public String role;
	public String origin;
	public String gender;
	public String race;
	public int[] No;
	public String trustName;
	public String trustLink;
	public unitStats stats;
	public String[] weapons;
	public String[] armours;
	public unitAbilities Special;
	public unitAbilities Magic;
	public String[] sprites;
	public String[] lore;
	public String[] awakening;
	public UnitInfo(String page)throws IOException{
		try{
			Document doc=null;
			while(true){
				doc = Jsoup.connect(page).userAgent(Settings.UA).timeout(10000).get();
				if(!(doc==null))break;
			}
			URL=page;
			Element content=doc.getElementById("mw-content-text");
			loreOverview=content.getElementsByTag("p").first().text();
			Element unitInfo=content.getElementsByTag("tbody").first();
			unitName=Lib.getHeader(0, unitInfo).text();
			imgOverviewURL=Lib.getCell(1, 0, unitInfo).child(0).absUrl("src");
			parseRarities(Lib.getCell(2, 0, unitInfo).text());
			job=Lib.getCell(3, 0, unitInfo).text();
			role=Lib.getCell(4, 0, unitInfo).text();
			origin=Lib.getCell(5, 0, unitInfo).text();
			gender=Lib.getCell(6, 0, unitInfo).text();
			race=Lib.getCell(7, 0, unitInfo).text();
			String[] no=Lib.getCell(8, 0, unitInfo).text().split(",");
			No=new int[no.length];
			for(int i=0;i<no.length;i++){
				try{
					No[i]=Integer.parseInt(no[i].trim());
				}catch(NumberFormatException e){
					No[i]=0;
				}
			}
			trustName=Lib.getCell(9, 0, unitInfo).text();
			trustLink=Lib.getCell(9, 0, unitInfo).absUrl("href");
			Element stats=Lib.getEleAfter(content.children(), new ElementFilter("h3","Stats")).getElementsByTag("tbody").first();
			this.stats=new unitStats(stats);
			Element equipment=Lib.getEleAfter(content.children(), new ElementFilter("h3","Equipment"));
			parseWeapons(Lib.getCell(1, 0, equipment));
			parseArmours(Lib.getCell(3, 0, equipment));
			Element special=Lib.getEleAfter(content.children(), new ElementFilter("h3","Special"));
			if(!(special==null)){
				Special=new unitAbilities(special.getElementsByTag("tbody").first());
			}
			Element magic=Lib.getEleAfter(content.children(), new ElementFilter("h3","Magic"));
			if(!(magic==null)){
				Magic=new unitAbilities(magic.getElementsByTag("tbody").first());
			}
			Element sprites=Lib.getEleAfter(content.children(), new ElementFilter("h2","Sprites")).getElementsByTag("tbody").first();
			this.sprites=new String[sprites.children().first().children().size()];
			for(int i=0;i<sprites.children().first().children().size();i++){
				this.sprites[i]=sprites.child(1).child(i).child(0).child(0).absUrl("src");
			}
			Element lore=Lib.getEleAfter(content.children(), new ElementFilter("h2","Background Story")).getElementsByTag("tbody").first();
			this.lore=new String[lore.children().size()];
			for(int i=0;i<lore.children().size();i++){
				this.lore[i]=Lib.getCell(i, 0, lore).text();
			}
			Element awaken=Lib.getEleAfter(content.children(), new ElementFilter("h2","Awakening"));
			awakening=new String[maxRarity-minRarity];
			for(int i=0;i<awakening.length;i++){
				awakening[i]="";
				for(int c=0;c<Lib.getTDCount(awaken, 1);c++){
					if(c==0){
						awakening[i]+=Lib.extractNumber(Lib.getCell(1+i, c, awaken).text())+"| ";
					}
					else{
						awakening[i]+=Lib.getCell(1+i, c, awaken).text()+"| ";
					}
				}
				awakening[i]=awakening[i].substring(0, awakening[i].length()-2);
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	public void parseRarities(String text){
		int[] rarity=Lib.extractNumbers(text) ;
		minRarity=rarity[0];
		maxRarity=rarity[1];
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
	public class unitStats{
		public statSet[] stats;
		public unitStats(Element statTable){
			stats=new statSet[statTable.children().size()-1];
			for(int i=1;i<statTable.children().size();i++){
				stats[i-1]=new statSet(statTable.child(i));
			}
		}
		public class statSet{
			public String rarity;
			public String HP;
			public String MP;
			public String ATK;
			public String DEF;
			public String MAG;
			public String SPR;
			public String hits;
			public String DC;
			public String growth;
			public statSet(Element row){
				rarity=""+Lib.extractNumber(row.child(0).text());;
				HP=row.child(1).text();
				MP=row.child(2).text();
				ATK=row.child(3).text();
				DEF=row.child(4).text();
				MAG=row.child(5).text();
				SPR=row.child(6).text();
				hits=row.child(7).text();
				DC=row.child(8).text();
				growth=row.child(9).text();
			}
		}
	}
	public class unitAbilities{
		public ability[] abilities;
		public unitAbilities(Element abilityTable){
			abilities=new ability[abilityTable.children().size()-1];
			for(int i=1;i<abilityTable.children().size();i++){
				abilities[i-1]=new ability(abilityTable.child(i));
			}
		}
		public class ability{
			public String rarity;
			public String level;
			public String aIconURL;
			public String name;
			public String link;
			public String effect;
			public String MP;
			public ability(Element row){
				rarity=""+Lib.extractNumber(row.child(0).text());
				level=row.child(1).text();
				aIconURL=row.child(2).absUrl("src");
				name=row.child(3).text();
				link=row.child(3).absUrl("href");
				effect=row.child(4).text();
				MP=row.child(5).text();
			}
		}
	}
}
