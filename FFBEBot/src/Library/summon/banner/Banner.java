package Library.summon.banner;
import Library.summon.Awakening;
import Library.summon.Unit;
import util.Lib;
public enum Banner {
	Release("Release",new Unit[]{},new int[][]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Exdeath,Unit.Bartz,Unit.Firion,Unit.Cecil,Unit.Garland,Unit.Miyuki,Unit.Vaan,Unit.CoD,Unit.Roselia,Unit.Cerius,Unit.Medius},Unit.commons1()),new Awakening[]{},BannerType.ReleaseType),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Golbez},new int[][]{{800,375,25},{250,50},{800,375,25}},Release.pool,null,BannerType.ReleaseType),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[][]{{560,366,33},{560,366,33},{700,366,33}},Lib.concat(Chiz.pool,Chiz.featured),new Awakening[]{},BannerType.ReleaseType),
	WoL("WoL, Lenna, Tellah",new Unit[]{Unit.WoL,Unit.Lenna,Unit.Tellah},new int[][]{{250,25},{1200,150,10},{1200,150,10}},Lib.concat(Locke.pool,Locke.featured),new Awakening[]{},BannerType.ReleaseType),
	FFX("FFX",new Unit[]{Unit.Zidane,Unit.Kuja,Unit.Amarant,Unit.Lani,Unit.Vivi},new int[][]{{160,26,10},{160,26,10},{160,26,10},{1000,228},{1000,228}},Lib.concat(WoL.featured,WoL.pool),new Awakening[]{},BannerType.MoreCommons),
	FFVI("FFVI",new Unit[]{Unit.Leo,Unit.Terra,Unit.Celes,Unit.Locke},new int[][]{{640,114,11},{640,114,11},{640,114,11},{640,114,11}},Lib.concat(FFX.featured,FFX.pool),new Awakening[]{},BannerType.MoreCommons),
	CD("Crystal Defenders",new Unit[]{Unit.Fencer,Unit.Thief,Unit.Juggler},new int[][]{{228,24},{228,24},{228,24}},Lib.concat(FFVI.pool,new Unit[]{Unit.Leo,Unit.Celes,Unit.Terra}),new Awakening[]{},BannerType.MoreCommons),
	PChoice("Player's choice",new Unit[]{Unit.Chizuru,Unit.WoL,Unit.CoD,Unit.Kefka,Unit.Lenna},new int[][]{{200,12},{200,12},{760,38,3},{760,38,3},{760,38,3}},CD.pool,new Awakening[]{},BannerType.MoreCommons),
	Lightning("Lightning",new Unit[]{Unit.Lightning,Unit.Charlotte,Unit.Ludmille},new int[][]{{50},{1040,285,15},{1040,285,15}},CD.pool,new Awakening[]{},BannerType.MoreCommons),
	FFT1("FFT part 1",new Unit[]{Unit.Deltia,Unit.Gaffgarion,Unit.Alma},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(Lightning.pool,Lightning.featured),new Awakening[]{},BannerType.Base5),
	FFT2("FFT part 2",new Unit[]{Unit.Ramza,Unit.Arigas,Unit.Mustadio},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(FFT1.pool,FFT1.featured),new Awakening[]{},BannerType.Base5),
	FF4("FFIV",new Unit[]{Unit.DKC,Unit.Rosa,Unit.Edge,Unit.Cecil},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(FFT2.pool,FFT2.featured),new Awakening[]{Awakening.FFIV},BannerType.Base5),
	Halloween("Halloween",new Unit[]{Unit.DRain,Unit.DLasswell,Unit.WWF,Unit.BCLid},new int[][]{{25},{25},{570,3},{2000,200,5}},Lib.concat(FF4.pool,new Unit[]{Unit.DKC,Unit.Rosa,Unit.Edge}),FF4.include,BannerType.Base5),
	FF3("FF3",new Unit[]{Unit.Luneth,Unit.Refia,Unit.Ingus,Unit.Arc},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Halloween.pool,Halloween.include,BannerType.Base5),
	BF("Brave Frontier",new Unit[]{Unit.Tilith,Unit.Seria,Unit.Karl},new int[][]{{330,3},{330,3},{330,3}},Lib.concat(FF3.pool,FF3.featured),FF3.include,BannerType.Base5),
	DD("Dark Destroyers",new Unit[]{Unit.DKC,Unit.Gaffgarion,Unit.CoD,Unit.Garland,Unit.Duane},new int[][]{{50},{570,3},{640,68,5},{640,68,5},{640,68,5}},BF.pool,BF.include,BannerType.Base5),
	Current("Current",new Unit[]{},new int[][]{},Unit.currentPool(),Awakening.values(),BannerType.BaseRare);
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
	public static Banner[] LEBanners(){
		return new Banner[]{Banner.Halloween,
				Banner.BF
				};
	}
	public static boolean LEBanner(Banner banner){
		for(Banner b:LEBanners()){
			if(b.equals(banner)){
				return true;
			}
		}
		return false;
	}
	
}
