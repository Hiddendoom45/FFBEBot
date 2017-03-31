package util.unit.reddit;

import org.jsoup.nodes.Element;

import util.Lib;

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