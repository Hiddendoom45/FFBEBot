package util.unit.reddit;

import org.jsoup.nodes.Element;

public class UnitRelated {
	public String category;
	public String jpName;
	public String enName;
	public String MP;
	public String effect;
	public String description;
	public UnitRelated(Element row){
		category=row.child(1).text();
		jpName=row.child(2).text();
		enName=row.child(3).text();
		MP=row.child(4).text();
		effect=row.child(5).text();
		description=row.child(6).text();
	}

}
