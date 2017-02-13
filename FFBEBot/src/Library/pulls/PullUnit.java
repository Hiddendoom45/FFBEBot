package Library.pulls;

import Library.summon.Unit;
import XML.Elements;

public class PullUnit {
	public Unit unit;
	public int rare=0;
	//plans to add equipment and other aspects to this
	public PullUnit(Unit unit, int rare){
		this.unit=unit;
		this.rare=rare;
	}
	
	public Elements parseToElements() {
		Elements root=new Elements("Unit");
		Elements info=new Elements("info");
		
		Elements rare=new Elements("rare",""+this.rare);
		info.add(rare);
		root.add(info);
		
		return root;
	}
}
