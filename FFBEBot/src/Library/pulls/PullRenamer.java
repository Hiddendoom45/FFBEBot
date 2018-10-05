package Library.pulls;

import java.util.HashMap;

import Library.summon.Unit;

public class PullRenamer {
	private static PullRenamer rename = new PullRenamer();
	private HashMap<String,Unit> renameMap = new HashMap<String,Unit>();
	private PullRenamer(){
		renameMap.put("Russel", Unit.Russel);
		renameMap.put("Clinque", Unit.Clinque);
		renameMap.put("Sohze", Unit.Soze);
		renameMap.put("Illias", Unit.Illus);
		renameMap.put("Vertias of the Light", Unit.LV);
		renameMap.put("Vertias of the Heavens", Unit.HV);
		renameMap.put("Vertias of the Waters", Unit.WV);
	}
	//stopgap to rename stuff from data files
	public static Unit filter(String name){
		if(rename.renameMap.containsKey(name)){
			return rename.renameMap.get(name);
		}
		return null;
	}
}
