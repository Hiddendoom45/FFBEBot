package util.rng.summon;

import java.util.ArrayList;
import java.util.Random;

import Lib.summon.Awakening;
import Lib.summon.SummonedUnit;
import Lib.summon.Unit;
import Lib.summon.banner.Banner;
import util.rng.RandomLibs;

public class Pull {
	private static int pool3=7999;
	private static int pool4=1899;
	private static int pool5=99;
	public static ArrayList<SummonedUnit> pull(int times,Banner banner){
		Random rand=new Random();
		ArrayList<SummonedUnit> units=new ArrayList<SummonedUnit>();
		for(int i=0;i<times;i++){
			int rarity=rand.nextInt(99);
			//5*
			if(rarity==0){
				Unit u=pull(banner,5,pool5,50);
				units.add(new SummonedUnit(u.getRarity(5),u.name,5));
			}
			//3*
			else if(rarity>19){
				Unit u=pull(banner,3,pool3,100);
				units.add(new SummonedUnit(u.getRarity(3),u.name,3));
			}
			//4*
			else{
				Unit u=pull(banner,4,pool4,40);
				units.add(new SummonedUnit(u.getRarity(4),u.name,4));
			}
		}
		return units;
	}
	private static Unit pull(Banner banner,int rarity,int poolSize,int baseChance){
		Unit[] pool;
		Random rand=new Random();
		int select=rand.nextInt(99);
		if(select<baseChance){
			pool=baseRarity(banner,rarity);
		}
		else{
		pool=hasRarity(banner,rarity);
		}
		int next=rand.nextInt(poolSize);
		int[] featured=new int[banner.featured.length];
		int i=0;
		for(Unit u:banner.featured){
			if(u.hasRarity(rarity)){
				featured[i]=banner.percent[i][u.getRarityIndex(rarity)];
			}
			else if(u.hasUpgrade(rarity, banner.include)){
				featured[i]=banner.percent[i][u.getUpgradeIndex(rarity, banner.include)];
			}
			else{
				featured[i]=0;
			}
			i++;
		}
		i=0;
		for(int c=0;c<featured.length;c++){
			if(next<featured[c]&&next>=i){
				return banner.featured[c];
			}
		}
		return RandomLibs.SelectRandom(pool);
	}
	private static Unit[] hasRarity(Banner banner,int rarity){
		ArrayList<Unit> units=new ArrayList<Unit>();
		for(Unit u:banner.pool){
			if(u.hasRarity(rarity)){
				units.add(u);
			}
		}
		for(Awakening a:banner.include){
			for(int i=0;i<a.units.length;i++){
				if(a.units[i].hasUpgrade(rarity, a.rarityAwakened[i])){
					units.add(a.units[i]);
				}
			}
		}
		return toArray(units);
	}
	private static Unit[] baseRarity(Banner banner,int rarity){
		ArrayList<Unit> units=new ArrayList<Unit>();
		for(Unit u:banner.pool){
			if(u.base==rarity){
				units.add(u);
			}
		}
		return toArray(units);
	}
	private static Unit[] toArray(ArrayList<Unit> units){
		Unit[] unit=new Unit[units.size()];
		for(int i=0;i<units.size();i++){
			unit[i]=units.get(i);
		}
		return unit;
	}
}
