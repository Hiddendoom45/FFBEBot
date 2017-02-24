package util.unit;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Library.ElementFilter;
import global.record.Log;
import global.record.Settings;
import util.Lib;

public class RedditUnit {
	private static HashMap<String,String> refImg=new HashMap<String,String>();
	public String URL="";
	public String title="";
	public String JobTribe="";
	public String TrustDetails="";
	public String[] growth=new String[]{};
	public int baseR=0;
	public int maxR=0;
	public String[] awakening=new String[]{};
	public String[] slots=new String[]{};
	public UnitStats[] stats=new UnitStats[]{};
	public String equipment="";
	public UnitLB[] LB=new UnitLB[]{};
	public UnitAbility[] magic=new UnitAbility[]{};
	public UnitAbility[] special=new UnitAbility[]{};
	public UnitEnhancements[] enhance=new UnitEnhancements[]{};
	public UnitSpark[] sparks=new UnitSpark[]{};
	public RedditUnit(String page){
		try{
			Document doc=null;
			for(int i=0;i<10;i++){
				try{
					doc = Jsoup.connect(page).userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(Exception e){Log.logError(e);}
			}
			URL=page;
			Element content=doc.getElementsByClass("wiki-page-content").first().getElementsByClass("wiki").first();
			try{
				title=content.getElementsByTag("h2").first().text();
				JobTribe=content.getElementsByTag("ul").get(2).child(0).text();
				TrustDetails=content.getElementsByTag("ul").get(2).child(1).text();
			}catch(Exception e){
				Log.log("ERROR", "error parsing basic info for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element growths=Lib.getEleAfter(content.children(), new ElementFilter("h3","Growth Pattern"));
				growth=new String[growths.children().size()];
				for(int i=0;i<growths.children().size();i++){
					growth[i]=growths.child(i).text();
					if(i==0){
						baseR=Integer.parseInt(growths.child(i).text().substring(1, 2));
					}
					else if(i==growths.children().size()-1){
						maxR=Integer.parseInt(growths.child(i).text().substring(1, 2));;
					}
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing exp growths/rarities for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element awakenings=Lib.getEleAfter(content.children(), new ElementFilter("h3","Evolution"));
				awakening=new String[awakenings.children().size()];
				for(int i=0;i<awakenings.children().size();i++){
					String awk=awakenings.child(i).text();
					Elements imgs=awakenings.child(i).getElementsByTag("a");
					int refIndex=0;
					for(Element e:imgs){
						refIndex=awk.indexOf("|", refIndex+1);
						awk=new StringBuilder(awk).insert(refIndex+1, " "+refImg(e.attr("href"))+":").toString();
						refIndex=refIndex+refImg(e.attr("href")).length()+2;
					}
					awakening[i]=awk;
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing awakening mats for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element stats=Lib.getEleAfter(content.children(), new ElementFilter("h3","Stats")).getElementsByTag("tbody").first();
				this.stats=new UnitStats[stats.children().size()];
				for(int i=0;i<stats.children().size();i++){
					this.stats[i]=new UnitStats(stats.child(i));
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing stats for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element equip=Lib.getEleAfter(content.children(), new ElementFilter("h3","Equipments"));
				equipment="";
				for(Element ele:equip.children()){
					equipment+=refImg(ele.attr("href"))+",";
				}
				equipment=equipment.substring(0, equipment.length()-1);
			}catch(Exception e){
				Log.log("ERROR", "error parsing equipments for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element slot=Lib.getEleAfter(content.children(), new ElementFilter("h3","Materia Slots"));
				slots=new String[slot.children().size()];
				for(int i=0;i<slot.children().size();i++){
					slots[i]=slot.child(i).text();
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing materia slots for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element LBs=Lib.getEleAfter(content.children(), new ElementFilter("h3","Limit Burst")).getElementsByTag("tbody").first();
				LB=new UnitLB[LBs.children().size()];
				for(int i=0;i<LBs.children().size();i++){
					LB[i]=new UnitLB(LBs.child(i));
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing LBs for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element magic=Lib.getEleAfter(content.children(), new ElementFilter("h3","Magic Spells")).getElementsByTag("tbody").first();
				this.magic=new UnitAbility[magic.children().size()];
				for(int i=0;i<magic.children().size();i++){
					this.magic[i]=new UnitAbility(magic.child(i));
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing magic abilities for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element specials=Lib.getEleAfter(content.children(), new ElementFilter("h3","Abilities")).getElementsByTag("tbody").first();
				special=new UnitAbility[specials.children().size()];
				for(int i=0;i<specials.children().size();i++){
					special[i]=new UnitAbility(specials.child(i));
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing special abilites for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Elements en=Lib.getEleAfter(content.children(), new ElementFilter("h3","Enhancements")).getElementsByTag("tbody");

				if(!(en==null)){
					Element enhancements=en.first();
					enhance=new UnitEnhancements[enhancements.children().size()];
					for(int i=0;i<enhancements.children().size();i++){
						enhance[i]=new UnitEnhancements(enhancements.child(i));
					}
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing enhancements for page "+page);
				Log.logShortError(e, 5);
			}
			try{
				Element sparks=Lib.getEleAfter(content.children(), new ElementFilter("h3","閃き"));
				if(!(sparks==null)){
					Elements sp=sparks.getElementsByTag("tbody");
					sparks=sp.first();
					this.sparks=new UnitSpark[sparks.children().size()];
					for(int i=0;i<sparks.children().size();i++){
						this.sparks[i]=new UnitSpark(sparks.child(i));
					}
				}
			}catch(Exception e){
				Log.log("ERROR", "error parsing sparks for page "+page);
				Log.logShortError(e, 5);
			}

		}catch(Exception e){
			Log.logError(e);
		}
	}
	public static String refImg(String ref){
		if(refImg.containsKey(ref)){
			return refImg.get(ref);
		}
		else{
			return "itemNotFound";
		}
	}
	public static void buildRefImg(){
		refImg.put("#Z/Icons/e1/", "Dagger");
		refImg.put("#Z/Icons/e2/", "Sword");
		refImg.put("#Z/Icons/e3/", "Great Sword");
		refImg.put("#Z/Icons/e4/", "Katana");
		refImg.put("#Z/Icons/e5/", "Staff");
		refImg.put("#Z/Icons/e6/", "Rod");
		refImg.put("#Z/Icons/e7/", "Bow");
		refImg.put("#Z/Icons/e8/", "Axe");
		refImg.put("#Z/Icons/e10/", "Spear");
		refImg.put("#Z/Icons/e13/", "Throwing");
		refImg.put("#Z/Icons/e15/", "Mace");
		refImg.put("#Z/Icons/e16/", "Fist");
		refImg.put("#Z/Icons/e30/", "Light Shield");
		refImg.put("#Z/Icons/e31/", "Heavy Shield");
		refImg.put("#Z/Icons/e40/", "Hat");
		refImg.put("#Z/Icons/e41/", "Helm");
		refImg.put("#Z/Icons/e50/", "Clothes");
		refImg.put("#Z/Icons/e51/", "Light Armour");
		refImg.put("#Z/Icons/e52/", "Heavy Armour");
		refImg.put("#Z/Icons/e53/", "Robe");
		refImg.put("#Z/Icons/e60/", "Accessory");
		refImg.put("#B/Icons/290010000/", "Beast Meat");
		refImg.put("#B/Icons/290010100/", "Pearl of Wisdom");
		refImg.put("#B/Icons/290010200/", "Allure Powder");
		refImg.put("#B/Icons/290020000/", "Litrock");
		refImg.put("#B/Icons/290020100/", "Seed of Life");
		refImg.put("#B/Icons/290020200/", "Crimson Tear");
		refImg.put("#B/Icons/290020300/", "Mystic Ore");
		refImg.put("#B/Icons/290020400/", "Aqua Pearl");
		refImg.put("#B/Icons/290020500/", "Luminous Horn");
		refImg.put("#B/Icons/290020600/", "Quality Parts");
		refImg.put("#B/Icons/290020700/", "Rainbow Needle");
		refImg.put("#B/Icons/290020800/", "Golden Egg");
		refImg.put("#B/Icons/290020900/", "Book of Ruin");
		refImg.put("#B/Icons/290030000/", "Earth's Core");
		refImg.put("#B/Icons/290030100/", "Heaven's Ash");
		refImg.put("#B/Icons/290030200/", "Deepsea Bloom");
		refImg.put("#B/Icons/290030300/", "Scripture of Time");
		refImg.put("#B/Icons/290030400/", "Farplane Dew");
		refImg.put("#B/Icons/290040000/", "Spirit Sand");
		refImg.put("#B/Icons/290040100/", "God's Reliquary");
		refImg.put("#B/Icons/290040200/", "Dragon Heart");
		refImg.put("#B/Icons/290040300/", "Esper's tear");
		refImg.put("#B/Icons/290040400/", "Talmonite of Life");
		refImg.put("#B/Icons/290050100/", "Esper Shard");
		refImg.put("#B/Icons/290050200/", "Esper Cryst");
		refImg.put("#B/Icons/290050300/", "Sacred Crystal");
		refImg.put("#B/Icons/290050400/", "Holy Crystal");
		refImg.put("#B/Icons/290050500/", "Divine Crystal");
		refImg.put("#B/Icons/290060000/", "Fairy's writ");
		refImg.put("#B/Icons/290060100/", "Rainbow Bloom");
		refImg.put("#B/Icons/290060200/", "Calamity Gem");
		refImg.put("#B/Icons/290060300/", "Prismaric Horn");
		refImg.put("#B/Icons/290060400/", "Calamity Writ");
	}
	public class UnitStats{
		public int rarity;
		public String HP;
		public String MP;
		public String ATK;
		public String DEF;
		public String MAG;
		public String SPR;
		public String hits;
		public String DC;

		public UnitStats(Element row){
			rarity=Lib.extractNumber(row.child(0).text());
			HP=row.child(1).text();
			MP=row.child(2).text();
			ATK=row.child(3).text();
			DEF=row.child(4).text();
			MAG=row.child(5).text();
			SPR=row.child(6).text();
			hits=row.child(7).text();
			DC=row.child(8).text();
		}
	}
	public class UnitLB{
		public int rarity;
		public String maxLv;
		public String name;
		public String effect;
		public String element;
		public int value;
		public UnitLB(Element row){
			rarity=Lib.extractNumber(row.child(0).text());
			maxLv=row.child(1).text();
			name=row.child(2).text();
			effect=row.child(3).text();
			element=row.child(4).text();
			//value=Integer.parseInt(row.child(5).text());
		}
	}
	public class UnitAbility{
		public String jpName;
		public String enName;
		public String effect;
		public String jpDecscription;
		public int MP;
		public int level;
		public int minRare;
		public UnitAbility(Element row){
			jpName=row.child(1).text();
			enName=row.child(2).text();
			effect=row.child(3).text();
			jpDecscription=row.child(4).text();
			MP=Lib.isNumber(row.child(5).text())?Integer.parseInt(row.child(5).text()):0;
			level=Integer.parseInt(row.child(6).text());
			minRare=Integer.parseInt(row.child(7).text());
		}
	}
	public class UnitEnhancements{
		public String jpName;
		public String enName;
		public String effect;
		public String MP;
		public String gil;
		public String Mats;
		public UnitEnhancements(Element row){
			jpName=row.child(1).text();
			enName=row.child(2).text();
			effect=row.child(3).text();
			MP=row.child(4).text();
			gil=row.child(5).text();
			Mats=row.child(6).text();
		}
	}
	public class UnitSpark{
		public String name;
		public String S2;
		public String S3;
		public String effect;
		public String MP;
		public UnitSpark(Element row){
			name=row.child(0).text();
			S2=row.child(1).text();
			S3=row.child(2).text();
			effect=row.child(3).text();
			MP=row.child(4).text();
		}
	}
}
