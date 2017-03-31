package util.unit.reddit;

import org.jsoup.nodes.Element;

import util.Lib;

public class UnitLB{
	public int rarity;
	public String maxLv;
	public String name;
	public String effect;
	public String element;
	public String value;
	public UnitLB(Element row){
		rarity=Lib.extractNumber(row.child(0).text());
		maxLv=row.child(1).text();
		name=row.child(2).text();
		effect=row.child(3).text();
		element=row.child(4).text();
		value=row.child(5).text();
	}
}