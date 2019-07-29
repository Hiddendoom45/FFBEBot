package Library.summon.banner;

public enum BannerType {
	ReleaseType(true,false,new int[]{100,0,0}),//state of the summons at game release
	MoreCommons(true,false,new int[]{100,0,0}),//Vivi/Lani
	Base5(true,false,new int[]{100,40,50}),
	FFXIIIAjusted(true,true, new int[]{100,40,50}),//ajustment of FFXIII banner where you got equal all units/removal of common/rare rare summon units
	BaseRare(false,true,new int[]{100,100,100}),//JP adjustment where base crystals=base rarity, no troll rainbow
	ThreePercent(false,true,new int[]{100,100,100},new int[]{3,19,78}),//3% base rainbow rate
	ThreePercentFeatured(false,true,true,new int[]{100,100,100},new int[]{3,19,78}),
	
	FivePercent(false,true,new int[]{100,100,100},new int[]{5,19,76});//the currently few experimental 5% banners
	
	public boolean common;
	public boolean even;
	public boolean featuredOnly = false;
	public int[] baseRareChances;
	public int[] raritySplit = new int[]{1,19,80};//rate for each rarity with rarest first/highest stars
	
	BannerType(boolean common,boolean even,int[] baseRareChances){
		this.common=common;
		this.even=even;
		this.baseRareChances=baseRareChances;
	}
	
	BannerType(boolean common,boolean even,int[] baseRareChances,int[] raritySplit){
		this.common=common;
		this.even=even;
		this.baseRareChances=baseRareChances;
		this.raritySplit=raritySplit;
	}
	
	BannerType(boolean common,boolean even,boolean featuredOnly,int[] baseRareChances,int[] raritySplit){
		this.common=common;
		this.even=even;
		this.featuredOnly=featuredOnly;
		this.baseRareChances=baseRareChances;
		this.raritySplit=raritySplit;
	}
}
