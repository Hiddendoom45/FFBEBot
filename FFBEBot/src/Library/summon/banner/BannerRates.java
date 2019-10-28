package Library.summon.banner;

public enum BannerRates {
	//S==single D==double T==Triple Q==Quadruple, Qu=Quintuple number of said rarity units on banner
	//L= limit one or more rarities missing
	//x# = that many times more for the featured pool
	EvenSplit(new int[]{50,475,2000},new int[]{100,475,1950},new int[]{300,475,1950}),
	D3(new int[]{50,475,1000,1000}, new int[]{100,475,975,975},new int[]{300,475,975,975}),
	D4(new int[]{50,238,238,2000}, new int[]{100,238,238,1950},new int[]{300,238,238,1950}),
	D5(new int[]{25,25,475,2000}, new int[]{50,50,475,1950},new int[]{150,150,475,1950}),
	D5T4(new int[]{25,25,158,158,158,2000}, new int[]{50,50,158,158,158, 1950}, new int[]{100,100,100,158,158,158,1950}),
	D3D4(new int[]{50,238,238,1000,1000}, new int[]{100,238,238,975,975},new int[]{300,238,238,975,975}),
	D3D5(new int[]{25,25,475,1000,1000}, new int[]{50,50,475,975,975},new int[]{150,150,475,975,975}),
	D4D5(new int[]{25,25,238,238,2000}, new int[]{50,50,238,238,1950},new int[]{150,150,238,238,1950}),
	D3D4D5(new int[]{25,25,238,238,1000,1000}, new int[]{50,50,238,238,975,975},new int[]{150,150,238,238,975,975}),
	D3T4(new int[]{50,158,158,158,1000,1000},new int[]{100,158,158,158,975,975},new int[]{300,158,158,158,975,975}),
	T5(new int[]{17,17,17,475,2000},new int[]{33,33,34,475,1950},new int[]{100,100,100,475,1950}),
	T5D4(new int[]{17,17,17,238,238,2000},new int[]{33,33,34,238,238,1950},new int[]{100,100,100,238,238,1950}),
	T5T4D3(new int[]{17,17,17,158,158,158,1000,1000},new int[]{33,33,34,158,158,158,975,975},new int[]{100,100,100,238,238,238,975,975}),
	Qu5D4(new int[]{10,10,10,10,10,238,238,2000},new int[]{20,20,20,20,20,238,238,1950},new int[]{60,60,60,60,60,238,238,1950}),
	LS5T4(new int[]{50,158,158,158}, new int[]{100,158,158,158},new int[]{300,158,158,158}),
	LS5S4(new int[]{50,475}, new int[]{100,475}, new int[]{300, 475}),
	LD5(new int[]{25,25},new int[]{50,50},new int[]{150,150}),
	LD5D3(new int[]{25,25,1000,1000}, new int[]{50,50,975,975}, new int[]{150,150,975,975}),
	LT5(new int[]{17,17,17},new int[]{33,33,34},new int[]{100,100,100}),
	Rivera(new int[]{50}, new int[]{150}, new int[]{300}),//temporary name may change if more banners like this appear
	FFBEHeroes(null,new int[]{100,100,25,25,25,25,475,1950},null);
	
	private final int[] rates;
	private final int[] fiveRates;
	private final int[] featuredFive;
	BannerRates(int[] rates,int[] fiveRates,int[] featuredFive){
		this.rates=rates;
		this.fiveRates=fiveRates;
		this.featuredFive=featuredFive;
	}
	public int[] rate(){
		return rates;
	}
	public int[] fiveRate(){
		return fiveRates;
	}
	public int[] featuredFive(){
		return featuredFive;
	}
}
