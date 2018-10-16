package util.rng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import util.rng.summon.Pull;

public class RNGTester {
	private static final int maxPull = 5000;
	public static List<String> testPullRates(int pulls,Banner banner){
		HashMap<String,Integer> summonMap = new HashMap<String,Integer>();
		int p = pulls;
		while(p>0){
			if(p>maxPull){
				p-=maxPull;
				for(UnitSpecific u:Pull.pull(maxPull, banner)){
					if(summonMap.containsKey(u.name+"-"+u.rarity)){
						summonMap.put(u.name+"-"+u.rarity, summonMap.get(u.name+"-"+u.rarity)+1);
					}
					else{
						summonMap.put(u.name+"-"+u.rarity, 1);
					}
				}
			}
			else{
				for(UnitSpecific u:Pull.pull(p, banner)){
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
}
