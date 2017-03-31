package util.unit.reddit;

import org.jsoup.nodes.Element;

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
