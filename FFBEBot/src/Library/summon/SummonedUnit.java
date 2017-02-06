package Library.summon;

import java.io.File;

public class SummonedUnit {
	public String url;
	public String name;
	public int rarity;
	public SummonedUnit(String url, String name,int rarity){
		this.url=url;
		this.name=name;
		this.rarity=rarity;
	}
	public File getImageLocation(){
		return new File("units/"+name+"/"+rarity+".png");
	}
}
