package Library.summon.banner;

public enum BannerRates {
	//S==single D==double T==Triple Q==Quadruple, number of said rarity units on banner
	//L= limit one or more rarities missing
	//x# = that many times more for the featured pool
	EvenSplit(new int[]{50,475,2000},new int[]{100,475,1950}),
	D3(new int[]{50,475,1000,1000}, new int[]{100,475,975,975}),
	D4(new int[]{50,238,238,2000}, new int[]{100,238,238,1950}),
	D5(new int[]{25,25,475,2000}, new int[]{50,50,475,1950}),
	D3D4(new int[]{50,238,238,1000,1000}, new int[]{100,238,238,975,975}),
	D3D5(new int[]{25,25,475,1000,1000}, new int[]{50,50,475,975,975}),
	D4D5(new int[]{25,25,238,238,2000}, new int[]{50,50,238,238,1950}),
	D3D4D5(new int[]{25,25,238,238,1000,1000}, new int[]{50,50,238,238,975,975}),
	D3T4(new int[]{50,158,158,158,1000,1000},new int[]{100,158,158,158,975,975}),
	LS5T4(new int[]{50,158,158,158}, new int[]{100,158,158,158}),
	FFBEHeroes(null,new int[]{100,100,25,25,25,25,475,1950});
	
	private int[] rates;
	private int[] fiveRates;
	BannerRates(int[] rates,int[] fiveRates){
		this.rates=rates;
		this.fiveRates=fiveRates;
	}
	public int[] rate(){
		return rates;
	}
	public int[] fiveRate(){
		return fiveRates;
	}
}
