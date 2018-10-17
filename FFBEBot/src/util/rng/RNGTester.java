package util.rng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

import Library.summon.Unit;
import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import util.rng.summon.Pull;

public class RNGTester {
	private static final int maxPull = 5000;
	public static List<String> testBase5PullRates(int pulls, Banner banner){
		return testRates(pulls, i -> Pull.pull5base(i, banner));
	}
	public static List<String> test11PullRates(int pulls,Banner banner){
		return testRates(pulls, i -> Pull.pull11(i, banner));
	}
	public static List<String> testPullRates(int pulls, Banner banner){
		return testRates(pulls, i -> Pull.pull(i, banner));
	}
	public static List<String> testRates(int pulls, Function<Integer,List<UnitSpecific>> pull){
		HashMap<String,Integer> summonMap = new HashMap<String,Integer>();
		int p = pulls;
		int base5 = 0;
		int base4 = 0;
		int base3 = 0;
		while(p>0){
			if(p>maxPull){
				p-=maxPull;
				for(UnitSpecific u:pull.apply(maxPull)){
					if(u.rarity==5){
						base5++;
					}
					else if(u.rarity==4){
						base4++;
					}
					else if(u.rarity==3){
						base3++;
					}
					if(summonMap.containsKey(u.name+"-"+u.rarity)){
						summonMap.put(u.name+"-"+u.rarity, summonMap.get(u.name+"-"+u.rarity)+1);
					}
					else{
						summonMap.put(u.name+"-"+u.rarity, 1);
					}
				}
			}
			else{
				for(UnitSpecific u:pull.apply(p)){
					if(u.rarity==5){
						base5++;
					}
					else if(u.rarity==4){
						base4++;
					}
					else if(u.rarity==3){
						base3++;
					}
					if(summonMap.containsKey(u.name+"-"+u.rarity)){
						summonMap.put(u.name+"-"+u.rarity, summonMap.get(u.name+"-"+u.rarity)+1);
					}
					else{
						summonMap.put(u.name+"-"+u.rarity, 1);
					}
				}
				p=0;
			}
		}
		System.out.println("pulled");
		TreeMap<Integer,List<String>> revMap = new TreeMap<Integer,List<String>>();
		summonMap.forEach((s,i) -> {
			if(revMap.containsKey(i)){
				revMap.get(i).add(s);
			}
			else{
				ArrayList<String> sa = new ArrayList<String>();
				sa.add(s);
				revMap.put(i, sa);
			}
		});
		ArrayList<String> s = new ArrayList<String>();
		int key=revMap.lastKey();
		int index=0;
		s.add(String.format("%02.2f%% = base 5" , (float)base5/pulls*100));
		s.add(String.format("%02.2f%% = base 4" , (float)base4/pulls*100));
		s.add(String.format("%02.2f%% = base 3" , (float)base3/pulls*100));
		while(true){
			s.add(String.format("%02.2f%% = ", ((float)key/pulls)*100)+revMap.get(key).get(index));
			index++;
			if(index==revMap.get(key).size()){
				Integer a= revMap.lowerKey(key);
				if(a==null){
					break;
				}
				else{
					key=a;
				}
				index = 0;
			}
		}
		return s;
	}
	public static void currDiff(Banner banner){
		TreeSet<Unit> cur = new TreeSet<Unit>(Arrays.asList(Unit.currentPool()));
		Unit[] ban = banner.getPool();
		for(Unit u:ban){
			if(!cur.remove(u)){
				System.out.println("current doesn't have unit "+u);
			}
		}
		cur.forEach(u -> System.out.println(u));
	}
	public static void currPoolCheck(){
		TreeSet<Unit> cur = new TreeSet<Unit>(Arrays.asList(Unit.currentPool()));
		boolean flag = false;
		for(Unit u:Unit.values()){
			if(flag){
				if(u==Unit.Juggler){
					break;
				}
				if(!cur.remove(u)){
					System.out.println("current pool missing "+u);
				}
			}
			else if(u==Unit.Montana){
				flag=true;
			}
		}
		cur.forEach(u -> System.out.println(u));
	}
}
