package util.unit.exvius;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

import util.Lib;

public class unitStats{
	public static Matcher mixedStat = Pattern.compile("(\\d+)(?:\\s+(\\d+)\\s+[+]\\s+\\d+\\s*|)").matcher("");
	public statSet[] stats;
	public statSet[] increases;
	public unitStats(Element statTable){
		stats=new statSet[statTable.children().size()-1];
		increases=new statSet[statTable.children().size()-1];
		for(int i=1;i<statTable.children().size();i++){
			stats[i-1]=new statSet(statTable.child(i),1);
			increases[i-1]=new statSet(statTable.child(i),2);
		}
	}
	public void setIncreases(Element statTable){
		increases=new statSet[statTable.children().size()-1];
		for(int i=1;i<statTable.children().size();i++){
			increases[i-1]=new statSet(statTable.child(i),1);
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
		public statSet(Element row, int group){
			rarity=""+Lib.extractNumber(row.child(0).text());
			mixedStat.reset(row.child(1).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)HP=mixedStat.group(group);
			mixedStat.reset(row.child(2).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)MP=mixedStat.group(group);
			mixedStat.reset(row.child(3).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)ATK=mixedStat.group(group);
			mixedStat.reset(row.child(4).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)DEF=mixedStat.group(group);
			mixedStat.reset(row.child(5).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)MAG=mixedStat.group(group);
			mixedStat.reset(row.child(6).text());
			if(mixedStat.matches()&&mixedStat.groupCount()>=group)SPR=mixedStat.group(group);
			if(row.children().size()>7){
				hits=row.child(7).text();
				DC=row.child(8).text();
				growth=row.child(9).text();
			}
		}
	}
}