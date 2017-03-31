package util.unit.reddit;

import org.jsoup.nodes.Element;

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