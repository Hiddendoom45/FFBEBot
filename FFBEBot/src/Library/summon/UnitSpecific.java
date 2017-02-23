package Library.summon;

import java.io.File;

/**
 * The data for a specific rarity of a unit, unit class is for unit info for all rarities
 * @author Allen
 *
 */
public class UnitSpecific {
	String imgUrl;
	int rarity;
	int base;
	String name;
	public UnitSpecific(Unit unit, int rarity) throws UnitsException{
		if(unit==null){
			throw new UnitsException("unit value is null");
		}
		if(rarity<1){
			throw new UnitsException("rarity value too low:"+rarity);
		}
		try{
			imgUrl=unit.getRarity(rarity);
		}catch(IndexOutOfBoundsException e){
			try{
				imgUrl=unit.getUpgrade(rarity, unit.upgradeurl.length);
			}catch(IndexOutOfBoundsException e1){
				throw new UnitsException("unit does not have the specified rarity");
			}
		}
		this.rarity=rarity;
		name=unit.name;
		base=unit.base;
	}
	/**
	 * Get file location that the preloader would've loaded the images to
	 * @return file location for the unit image if it exists, otherwise null
	 */
	public File getImageLocation(){
		if(new File("units/"+name+"/"+rarity+".png").exists())return new File("units/"+name+"/"+rarity+".png");
		return null;
	}
	/**
	 * Exception for any error in this class, related to unit
	 * @author Allen
	 *
	 */
	class UnitsException extends Exception{
		public UnitsException(String string) {
			super(string);
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = -2826538255464720836L;
		
	}
}