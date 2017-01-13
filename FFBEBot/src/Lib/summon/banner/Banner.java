package Lib.summon.banner;
import Lib.summon.Awakening;
import Lib.summon.Unit;
import util.Lib;
public enum Banner {
	Release("Release Rare Summon",new Unit[]{},new int[][]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Celes,Unit.Leo},Unit.commons()),new Awakening[]{}),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Kefka},new int[][]{{1400},{300},{1400}},Release.pool,null),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[][]{{1100},{1100},{1100}},Lib.concat(Chiz.pool,Chiz.featured),new Awakening[]{}),
	Current("Current",new Unit[]{},new int[][]{},Unit.values(),new Awakening[]{});
	
	public String name;
	public Unit[] featured;
	public Unit[] pool;
	public int[][] percent;//percent at each rarity
	public Awakening[] include;
	Banner(String name, Unit[] featured,int[][] percent, Unit[] pool,Awakening[] include){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		this.percent=percent;
		this.include=include;
	}
	Banner(String name, Unit[] featured,int[] percent, Unit[] pool,Awakening[] include){
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
	}
	public static Banner[] commonBanners(){
		return new Banner[]{Banner.Release,
				Banner.Chiz,
				Banner.Locke};
	}
	public static boolean commonBanner(Banner banner){
		for(Banner b:commonBanners()){
			if(b.equals(banner)){
				return true;
			}
		}
		return false;
	}
}
