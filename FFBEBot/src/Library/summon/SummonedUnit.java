package Library.summon;

import java.io.File;

import Library.summon.UnitSpecific.UnitsException;
import global.record.Log;
/**
 * contains basic data for unit that is summoned, url, rarity, name are variables for convenience only
 * @author Allen
 *
 */
public class SummonedUnit {
	public String url;
	public String name;
	public int rarity;
	public Unit unit;
	public SummonedUnit(String url, String name,int rarity,Unit unit){
		this.url=url;
		this.name=name;
		this.rarity=rarity;
		this.unit=unit;
	}
	/**
	 * Get file location that the preloader would've loaded the images to
	 * @return
	 */
	public File getImageLocation(){
		return new File("units/"+name+"/"+rarity+".png");
	}
	public UnitSpecific toSpecific(){
		try {
			return new UnitSpecific(unit,rarity);
		} catch (UnitsException e) {
			Log.logShortError(e, 5);
			return null;
		}
	}
}
