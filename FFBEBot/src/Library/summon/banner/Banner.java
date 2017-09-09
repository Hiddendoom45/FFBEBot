package Library.summon.banner;

import Library.summon.Awakening;
import Library.summon.Unit;
import util.Lib;
public enum Banner {
	//TODO add proper awakenings to each banner, currently only 1 added
	Release("Release",new Unit[]{},new int[][]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Exdeath,Unit.Bartz,Unit.Firion,Unit.Cecil,Unit.Garland,Unit.Miyuki,Unit.Vaan,Unit.CoD,Unit.Roselia,Unit.Cerius,Unit.Medius},Unit.commons1()),new Awakening[]{},BannerType.ReleaseType),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Golbez},new int[][]{{800,375,25},{250,50},{800,375,25}},Release.pool,null,BannerType.ReleaseType),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[][]{{560,366,33},{560,366,33},{700,366,33}},Lib.concat(Chiz.pool,Chiz.featured),new Awakening[]{},BannerType.ReleaseType),
	WoL("WoL, Lenna, Tellah",new Unit[]{Unit.WoL,Unit.Lenna,Unit.Tellah},new int[][]{{250,25},{1200,150,10},{1200,150,10}},Lib.concat(Locke.pool,Locke.featured),new Awakening[]{},BannerType.ReleaseType),
	FFIX("FFIX",new Unit[]{Unit.Zidane,Unit.Kuja,Unit.Amarant,Unit.Lani,Unit.Vivi},new int[][]{{160,26,10},{160,26,10},{160,26,10},{1000,228},{1000,228}},Lib.concat(WoL.featured,WoL.pool),new Awakening[]{},BannerType.MoreCommons),
	FFVI("FFVI",new Unit[]{Unit.Leo,Unit.Terra,Unit.Celes,Unit.Locke},new int[][]{{640,114,11},{640,114,11},{640,114,11},{640,114,11}},Lib.concat(FFIX.featured,FFIX.pool),new Awakening[]{},BannerType.MoreCommons),
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
	FFV("FFV",new Unit[]{Unit.Greg,Unit.Bartz,Unit.Exdeath,Unit.Faris},new int[][]{{50},{950,160,20},{950,160,20},{950,160,20}},DD.pool,DD.include,BannerType.Base5),
	FFXIII1("FFXIII part 1",new Unit[]{Unit.Snow,Unit.Vanille,Unit.Sazh},new int[][]{{304,25},{304,25},{2800,19,10}},Lib.concat(FFV.pool, FFV.featured),FFV.include,BannerType.FFXIIIAjusted),
	FFXIII2("FFXIII part 2",new Unit[]{Unit.Lightning,Unit.Hope,Unit.Fang},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(FFXIII1.pool, FFXIII1.featured),FFXIII1.include,BannerType.FFXIIIAjusted),
	Noct("Noctis",new Unit[]{Unit.Noctis,Unit.Mercedes,Unit.Chizuru,Unit.Charlotte},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(new Unit[]{Unit.Hope,Unit.Fang},FFXIII2.pool),FFXIII2.include,BannerType.FFXIIIAjusted),
	Christmas("Christmas Exclusive",new Unit[]{Unit.WKN,Unit.SRoselia,Unit.Cerius,Unit.Medius},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(new Unit[]{Unit.Mercedes,Unit.Noctis},Noct.pool),Noct.include,BannerType.FFXIIIAjusted),
	DFina("Dark Fina",new Unit[]{Unit.DFina,Unit.Luka,Unit.Elle},new int[][]{{50},{570,3},{1000,200,5}},Christmas.pool,Christmas.include,BannerType.FFXIIIAjusted),
	FF01("FF type-0 part 1",new Unit[]{Unit.Ace,Unit.Seven,Unit.Jack,Unit.Trey},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(DFina.pool, DFina.featured),DFina.include,BannerType.FFXIIIAjusted),
	SoM("Secret of Mana",new Unit[]{Unit.Randi,Unit.Primm,Unit.Popoi},new int[][]{{50},{570,3},{1000,200,5}},Lib.concat(FF01.pool,FF01.featured),FF01.include,BannerType.FFXIIIAjusted),
	Valentine("Valentine Special",new Unit[]{Unit.Marie,Unit.CArtemois,Unit.CLuna},new int[][]{{50},{570,3},{1000,200,5}},SoM.pool,SoM.include,BannerType.FFXIIIAjusted),
	//gacha 2.0 change
	Olive("Olive Banner",new Unit[]{Unit.Olive,Unit.Shine,Unit.Shera},new int[]{50,570,1205},Lib.concat(new Unit[]{Unit.Maria},Valentine.pool),Valentine.include,BannerType.BaseRare),
	FF02("FF type-0 part 2",new Unit[]{Unit.Queen,Unit.Nine,Unit.Eight,Unit.Clinque},new int[]{50,570,1205,1205},Lib.concat(Olive.featured,Olive.pool),Olive.include,BannerType.BaseRare),
	GilgVan("Gilgamesh Vanquishers",new Unit[]{Unit.Ramza,Unit.Snow,Unit.Arigas,Unit.Exdeath,Unit.Bartz}, new int[]{50,570,570,1205,1205},Lib.concat(FF02.featured,FF02.pool),FF02.include,BannerType.BaseRare),
	PC2("Second People's Choice",new Unit[]{Unit.Noctis,Unit.Greg,Unit.Refia,Unit.Chizuru,Unit.Cecil,Unit.Zidane},new int[]{25,25,570,570,1205,1205},GilgVan.pool,GilgVan.include, BannerType.BaseRare),
	FFTP2("FF Tactics part 2",new Unit[]{Unit.Cid,Unit.Soleil,Unit.Ovelia,Unit.Lawrence},new int[]{50,570,1205,1205},PC2.pool,PC2.include,BannerType.BaseRare),
	Egg("Egg Seekers",new Unit[]{Unit.Fryevia,Unit.Xon,Unit.Aiden},new int[]{50,570,1205},Lib.concat(FFTP2.featured,FFTP2.pool),FFTP2.include,BannerType.BaseRare),
	AwkWarriors("Awakened Warriors",new Unit[]{Unit.Lightning,Unit.Mercedes,Unit.Seven,Unit.Medius},new int[]{50,570,570,1205},Lib.concat(Egg.featured,Egg.pool),Egg.include,BannerType.BaseRare),
	FFVIP2("FFVI part 2",new Unit[]{Unit.TTerra,Unit.Setzer,Unit.Gau},new int[]{50,570,1205},AwkWarriors.pool,AwkWarriors.include,BannerType.BaseRare),
	Eileen("Aileen",new Unit[]{Unit.Eileen,Unit.Soze,Unit.Heretic,Unit.Ulrica},new int[]{50,570,1205,1205},Lib.concat(FFVIP2.pool,FFVIP2.featured),FFVIP2.include,BannerType.BaseRare),
	DragonKill("Dragon Killers",new Unit[]{Unit.Reberta,Unit.Zyrus,Unit.Firion,Unit.Kain},new int[]{50,570,1205,1205},Lib.concat(Eileen.pool,Eileen.featured),Eileen.include,BannerType.BaseRare),
	FF03("FF type-0 part 3",new Unit[]{Unit.Rem,Unit.King,Unit.Sice},new int[]{50,570,1205},Lib.concat(new Unit[]{Unit.Reberta,Unit.Zyrus},DragonKill.pool),DragonKill.include,BannerType.BaseRare),
	Wilhelm("Wilhelm", new Unit[]{Unit.Wilhelm,Unit.Grace,Unit.Jean,Unit.Abel},BannerRates.D3.rate(),Lib.concat(FF03.featured,FF03.pool),FF03.include,BannerType.BaseRare),
	Gathering("The Gathering",new Unit[]{Unit.Forren,Unit.Amelia,Unit.Illus,Unit.Camille},BannerRates.D4.rate(),Lib.concat(Wilhelm.featured,Wilhelm.pool),Wilhelm.include,BannerType.BaseRare),
	BF2("Brave Frontier 2",new Unit[]{Unit.Vargas,Unit.Tilith,Unit.Karl,Unit.Seria},new int[]{25,158,158,158},Lib.concat(Gathering.pool,Gathering.featured),Gathering.include,BannerType.BaseRare),
	Summer("Summer",new Unit[]{Unit.SMF,Unit.SFina,Unit.SLid},BannerRates.EvenSplit.rate(),BF2.pool,BF2.include, BannerType.BaseRare),
	FFX("FFX 2",new Unit[]{Unit.Tidus,Unit.Rikku,Unit.Wakka},BannerRates.EvenSplit.rate(),Lib.concat(Summer.pool,Summer.featured),Summer.include, BannerType.BaseRare),
	FFXII("FFXII",new Unit[]{Unit.Zargabaath,Unit.Rasler,Unit.Ashe,Unit.Vaan},BannerRates.D4.rate(),Lib.concat(FFX.pool, FFX.featured),FFX.include,BannerType.BaseRare),
	Promise("A promise beyond time",new Unit[]{Unit.Lunera,Unit.Bran,Unit.Helena,Unit.Ruggles},BannerRates.D4.rate(),Lib.concat(FFXII.pool, FFXII.featured),FFXII.include,BannerType.BaseRare),
	AwkWarriorsP2("Awakened Warriors Part 2",new Unit[]{Unit.Luneth,Unit.DKC,Unit.Refia,Unit.Rosa,Unit.Cecil,Unit.Exdeath},BannerRates.D3D4D5.rate(),Lib.concat(Promise.pool, Promise.featured),Promise.include,BannerType.BaseRare),
	FFTP3("FF Tactics part 3",new Unit[]{Unit.MercRamza,Unit.KDeltia,Unit.Orran,Unit.Meliadoul,Unit.Alma},BannerRates.D4D5.rate(),AwkWarriorsP2.pool,AwkWarriors.include,BannerType.BaseRare),
	Revenge("Time for Revenge(DV)",new Unit[]{Unit.DV,Unit.FV,Unit.EV,Unit.Victoria,Unit.Tim},BannerRates.D4D5.rate(),Lib.concat(FFTP3.pool,new Unit[]{Unit.MercRamza,Unit.KDeltia,Unit.Orran,Unit.Meliadoul}),FFTP3.include,BannerType.BaseRare),
	Nier("Nier",new Unit[]{Unit.N2B,Unit.A2,Unit.Eve,Unit.N9S,Unit.N21O},BannerRates.D4D5.rate(),Lib.concat(Revenge.pool, Revenge.featured),Revenge.include,BannerType.BaseRare),
	Malboro("Malboro Killers",new Unit[]{Unit.TTerra,Unit.WoL,Unit.Soleil,Unit.Kefka,Unit.CoD},BannerRates.D3D4.rate(),Nier.pool,Nier.include,BannerType.BaseRare),
	Memories("Memories from the Battlefield",new Unit[]{Unit.LV,Unit.HV,Unit.WV,Unit.Hayate,Unit.Charlotte},BannerRates.D3D4.rate(),Malboro.pool,Malboro.include,BannerType.BaseRare),
	FFIII("FFIII",new Unit[]{Unit.OK,Unit.Desch,Unit.Aria,Unit.Sara},BannerRates.D4.rate(),Lib.concat(Memories.pool, new Unit[]{Unit.LV,Unit.HV,Unit.WV}),Memories.include,BannerType.BaseRare),
	DevChoice("Developer's Choice",new Unit[]{Unit.Greg,Unit.Chizuru,Unit.Abel},BannerRates.EvenSplit.rate(),Lib.concat(FFIII.pool, FFIII.featured),FFIII.include,BannerType.BaseRare),
	Current("Current",new Unit[]{},new int[][]{},Unit.currentPool(),Awakening.values(),BannerType.BaseRare);
	public String name;
	public Unit[] featured;
	private Unit[] pool;
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
	}
	Banner(String name, Unit[] featured,int[] percent, Unit[] pool,Awakening[] include,BannerType type){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		int[][] per=new int[percent.length][];
		for(int i=0;i<per.length;i++){
			per[i]=new int[featured[i].url.length];
			per[i][0]=percent[i];
		}
		this.percent=per;
		this.include=include;
		this.type=type;
	}
	public Unit[] getPool(){
		Unit[] pool=this.pool;
		if(type==BannerType.ReleaseType){
			for(int i=1;i*Unit.commons1().length<(pool.length-(i*Unit.commons1().length))*4;i++){
				pool=Lib.concat(pool, Unit.commons1());
			}
		}
		else if(type==BannerType.MoreCommons){
			for(int i=1;i*Unit.commons2().length<(pool.length-(i*Unit.commons2().length))*4;i++){
				pool=Lib.concat(pool, Unit.commons2());
			}
		}
		return pool;
	}
	public static Banner[] LEBanners(){
		return new Banner[]{Banner.Halloween,
				Banner.BF,
				Banner.Christmas,
				Banner.SoM,
				Banner.Valentine,
				Banner.BF2
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
