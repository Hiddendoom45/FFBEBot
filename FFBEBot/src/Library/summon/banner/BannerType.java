package Library.summon.banner;

public enum BannerType {
	ReleaseType(true,false),//state of the summons at game release
	MoreCommons(true,false),//Vivi/Lani
	FFXIIIAjusted(true,true),//ajustment of FFXIII banner where you got equal all units/removal of common/rare rare summon units
	BaseRare(false,true);//JP adjustment where base crystals=base rarity, no troll rainbow
	public boolean common;
	public boolean even;
	BannerType(boolean common,boolean even){
		this.common=common;
		this.even=even;
	}
}
