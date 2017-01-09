package Lib.summon;
import util.Lib;
public enum Banner {
	Release("Release Rare Summon",new Unit[]{},new int[]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Celes,Unit.Leo},Unit.commons()),new Awakening[]{}),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Kefka},new int[]{1400,300,1400},Release.pool,null),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[]{1100,1100,1100},Lib.concat(Chiz.pool,Chiz.featured),null),
	Current("Current",null,null,Release.pool,null);
	
	public String name;
	public Unit[] featured;
	public Unit[] pool;
	public int[] percent;
	public Awakening[] include;
	Banner(String name, Unit[] featured,int[] percent, Unit[] pool,Awakening[] include){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		this.percent=percent;
		this.include=include;
	}
	
}
