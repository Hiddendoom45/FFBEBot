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
	public UnitLB(Element row, Element head){
		for(int i=0;i<head.children().size();i++){
			switch(head.child(i).text().trim()){
			case "Rarity":rarity=Lib.extractNumber(row.child(i).text());
			break;
			case "Max Lv":maxLv=row.child(i).text();
			break;
			case "Name":jpName=row.child(i).text();
			break;
			case "Name EN":enName=row.child(i).text();
			break;
			case "Effect":effect=row.child(i).text();
			break;
			case "Value":value=row.child(i).text();
			break;
			case "                           Value                           ":value=row.child(i).text();
			break;
			case "Cost":cost=Lib.extractNumber(row.child(i).text());
			break;
			case "Element":effect=row.child(i).text();
			break;
			default:
			break;
			}
		}
	}
}