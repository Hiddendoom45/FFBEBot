package Library.summon;

public enum Awakening {
	FFIV(new Unit[]{Unit.Kain,Unit.Rydia},new int[]{1,1});
	public Unit[] units;
	public int[] rarityAwakened;
	Awakening(Unit[] units,int[] rarityAwakened){
		this.units=units;
		this.rarityAwakened=rarityAwakened;
	}
}
