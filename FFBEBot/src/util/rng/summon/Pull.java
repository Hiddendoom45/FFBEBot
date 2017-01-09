package util.rng.summon;

import java.util.ArrayList;
import java.util.Random;

import Lib.summon.Banner;
import Lib.summon.Unit;

public class Pull {
	public static void pull(int times){
		Random rand=new Random();
		ArrayList<Unit> units=new ArrayList<Unit>();
		for(int i=0;i<times;i++){
			int rarity=rand.nextInt(99);
			//5*
			if(rarity==1){
				units.add(pull5(Banner.Current));
			}
			//3*
			else if(rarity>19){
				
			}
			//4*
			else{
				
			}
		}
	}
	private static Unit pull5(Banner banner){
		return null;
	}
}
