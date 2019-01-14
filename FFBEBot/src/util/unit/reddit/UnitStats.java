package util.unit.reddit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

import util.Lib;

public class UnitStats{
	public int rarity;
	public StatInfo HP;
	public StatInfo MP;
	public StatInfo ATK;
	public StatInfo DEF;
	public StatInfo MAG;
	public StatInfo SPR;
	public String hits;
	public String DC;

	public UnitStats(Element row){
		rarity=Lib.extractNumber(row.child(0).text());
		HP=new StatInfo(row.child(1).text());
		MP=new StatInfo(row.child(2).text());
		ATK=new StatInfo(row.child(3).text());
		DEF=new StatInfo(row.child(4).text());
		MAG=new StatInfo(row.child(5).text());
		SPR=new StatInfo(row.child(6).text());
		hits=row.child(7).text();
		DC=row.child(8).text();
	}
	public static class StatInfo{
		private final static Pattern p = Pattern.compile("(\\d+)\\s*/\\s*(\\d+)\\s*Pots:\\s*(\\d+)\\+\\((\\d+)\\)");
		public String raw;
		public boolean parsed = false;
		public int base;
		public int max;
		public int potmax;
		public int doormax;
		public StatInfo(String raw){
			this.raw=raw;
			Matcher m = p.matcher(raw);
			if(m.matches()){
				try{
				parsed = true;
				base = Integer.parseInt(m.group(1));
				max = Integer.parseInt(m.group(2));
				potmax = Integer.parseInt(m.group(3));
				doormax = Integer.parseInt(m.group(4));
				}catch(Exception e){
					parsed = false;
				}
			}
		}
		public String toString(){
			if(parsed){
				return base+"/"+max+"\n"+potmax+"+("+doormax+")";
			}
			else{
				return raw;
			}
		}
		
	}
}