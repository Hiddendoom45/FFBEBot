package Library.pulls;

import Library.summon.Unit;
import XML.Attribute;
import XML.Elements;
import util.Lib;

public class PullUnit {
	public Unit unit;
	public int rare=0;
	//plans to add equipment and other aspects to this
	public PullUnit(Unit unit, int rare){
		this.unit=unit;
		this.rare=rare;
	}
	public PullUnit(Elements root){
		String name=root.getAttribute("name").getValue();
		for(Unit u:Unit.values()){
			if(u.name==name){
				unit=u;
				break;
			}
		}
		rare=Lib.getNumber(root, "rare");
	}
	public Elements parseToElements() {
		Elements root=new Elements("Unit");
		root.getAttributes().add(new Attribute("name",unit.name));
		Elements info=new Elements("info");
		
		Elements rare=new Elements("rare",""+this.rare);
		info.add(rare);
		root.add(info);
		
		return root;
	}
}
