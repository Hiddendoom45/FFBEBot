package Library.pulls;

import Library.summon.Unit;
import Library.summon.UnitSpecific;
import XML.Attribute;
import XML.Elements;
import global.record.Log;
import util.Lib;
/**
 * Unit used by data class, has saving methods over other classes, 
 * @author Allen
 *
 */
public class PullUnit extends UnitSpecific{
	//plans to add equipment and other aspects to this
	public PullUnit(Unit unit, int rare) throws UnitsException{
		super(unit,rare);
	}
	public PullUnit(Elements root){
		String name=root.getAttribute("name").getValue();
		for(Unit u:Unit.values()){
			if(u.name.equals(name)){
				unit=u;
				break;
			}
		}
		rarity=Lib.getNumber(root, "rare");
		try {
			super.construct(unit, rarity);
		} catch (UnitsException e) {
			Log.logError(e);
		}
		
	}
	public Elements parseToElements() {
		Elements root=new Elements("Unit");
		root.getAttributes().add(new Attribute("name",unit.name));
		Elements info=new Elements("info");
		
		Elements rare=new Elements("rare",""+this.rarity);
		info.add(rare);
		root.add(info);
		
		return root;
	}
	public UnitSpecific toSpecific(){
		try {
			return new UnitSpecific(unit,rarity);
		} catch (UnitsException e) {
			Log.logError(e);
			return null;
		}
	}
}
