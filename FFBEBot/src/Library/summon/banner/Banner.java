package Library.summon.banner;
import Library.summon.Awakening;
import Library.summon.Unit;
import util.Lib;
public enum Banner {
	Release("Release",new Unit[]{},new int[][]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Exdeath,Unit.Bartz,Unit.Firion,Unit.Cecil,Unit.Garland,Unit.Miyuki,Unit.Vaan,Unit.CoD,Unit.Roselia,Unit.Cerius,Unit.Medius},Unit.commons1()),new Awakening[]{},BannerType.ReleaseType),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Kefka},new int[][]{{1400},{300},{1400}},Release.pool,null,BannerType.ReleaseType),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[][]{{1100},{1100},{1100}},Lib.concat(Chiz.pool,Chiz.featured),new Awakening[]{},BannerType.ReleaseType),
	Current("Current",new Unit[]{},new int[][]{},Unit.currentPool(),Awakening.values(),BannerType.FFXIIIAjusted);
	
	public String name;
	public Unit[] featured;
	public Unit[] pool;
	public int[][] percent;//percent at each rarity
	public Awakening[] include;
	public BannerType type;
	Banner(String name, Unit[] featured,int[][] percent, Unit[] pool,Awakening[] include,BannerType type){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		this.percent=percent;
		this.include=include;
		this.type=type;
		populateCommons();
	}
	Banner(String name, Unit[] featured,int[] percent, Unit[] pool,Awakening[] include,BannerType type){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		int[][] per=new int[percent.length][];
		int i=0;
		for(int[] p:per){
			p=new int[featured[i].url.length];
			p[0]=percent[i];
			i++;
		}
		this.percent=per;
		this.include=include;
		this.type=type;
		populateCommons();
	}
	private void populateCommons(){
		if(type==BannerType.ReleaseType){
			for(int i=0;i<5;i++){
				pool=Lib.concat(pool, Unit.commons1());
			}
		}
		else if(type==BannerType.MoreCommons){
			pool=Lib.concat(pool, Unit.commons2());
		}
	}
	public static Banner[] commonBanners(){
		return new Banner[]{Banner.Release,
				Banner.Chiz,
				Banner.Locke};
	}
	public static boolean commonBanner(Banner banner){
		for(Banner b:commonBanners()){
			if(b.equals(banner)){
				return true;
			}
		}
		return false;
	}
}
