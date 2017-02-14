package Library.summon.banner;

public enum BannerType {
	ReleaseType(true,false,new int[]{100,0,0}),//state of the summons at game release
	MoreCommons(true,false,new int[]{100,0,0}),//Vivi/Lani
	Base5(true,false,new int[]{100,40,50}),
	FFXIIIAjusted(true,true, new int[]{100,40,50}),//ajustment of FFXIII banner where you got equal all units/removal of common/rare rare summon units
	BaseRare(false,true,new int[]{100,100,100});//JP adjustment where base crystals=base rarity, no troll rainbow
	public boolean common;
	public boolean even;
	public int[] baseRareChances;
	BannerType(boolean common,boolean even,int[] baseRareChances){
		this.common=common;
		this.even=even;
		this.baseRareChances=baseRareChances;
	}
}
