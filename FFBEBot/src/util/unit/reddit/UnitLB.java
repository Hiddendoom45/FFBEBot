package util.unit.reddit;

import org.jsoup.nodes.Element;

import util.Lib;

public class UnitLB{
	public int rarity;
	public String maxLv;
	public String enName;
	public String jpName;
	public String effect;
	public String value;
	public int cost;
	public UnitLB(Element row){
		rarity=Lib.extractNumber(row.child(0).text());
		maxLv=row.child(1).text();
		jpName=row.child(2).text();
		enName=row.child(3).text();
		effect=row.child(4).text();
		value=row.child(5).text();
		cost=Lib.extractNumber(row.child(6).text());
	}
}