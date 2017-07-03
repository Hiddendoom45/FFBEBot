package Library.summon.banner;

public enum BannerRates {
	//S==single D==double T==Triple Q==Quadruple, number of said rarity units on banner
	//L= limit one or more rarities missing
	EvenSplit(new int[]{50,475,2000}),
	D3(new int[]{50,475,1000,1000}),
	D4(new int[]{50,238,238,2000}),
	D5(new int[]{25,25,475,2000}),
	D3D4(new int[]{50,238,238,1000,1000}),
	D3D5(new int[]{25,25,475,1000,1000}),
	D4D5(new int[]{25,25,238,238,2000}),
	D3D4D5(new int[]{25,25,238,238,1000,1000}),
	LS5T4(new int[]{50,158,158,158});
	private int[] rates;
	BannerRates(int[] rates){
		this.rates=rates;
	}
	public int[] rate(){
		return rates;
	}
}
