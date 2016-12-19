package util.rng;

public class ChanceLib {
	public static float chance(float chance,int rarity,int ePull, int pulls){
		float calcChance=0;
		float pullChance=(float) Math.pow((1-chance),pulls);
		float epullChance=1;
		if(rarity>3){
			epullChance=(float) (Math.pow((1-chance)*5, ePull)*(Math.pow((1-chance), 10*ePull)));
		}
		else{
			epullChance=(float) (Math.pow((1-chance), 10*ePull));
		}
		calcChance=pullChance*epullChance;
		return calcChance;
	}
}
