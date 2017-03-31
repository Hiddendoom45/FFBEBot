package util.unit.reddit;

import org.jsoup.nodes.Element;

import util.Lib;

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
		effect=row.child(4).text();
		jpDecscription=row.child(5).text();
		MP=Lib.isNumber(row.child(3).text())?Integer.parseInt(row.child(3).text()):0;
		level=Integer.parseInt(row.child(6).text());
		minRare=Integer.parseInt(row.child(7).text());
	}
}