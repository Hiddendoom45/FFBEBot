package Library.pulls;

import java.util.HashMap;

import Library.summon.Unit;

public class PullRenamer {
	private static PullRenamer rename = new PullRenamer();
	private HashMap<String,Unit> renameMap = new HashMap<String,Unit>();
	//old names for units when I wasn't able to read correctly and named many of them incorrectly internally
	private PullRenamer(){
		renameMap.put("Russel", Unit.Russel);
		renameMap.put("Clinque", Unit.Clinque);
		renameMap.put("Sohze", Unit.Soze);
		renameMap.put("Illias", Unit.Illus);
		renameMap.put("Vertias of the Light", Unit.LV);
		renameMap.put("Vertias of the Heavens", Unit.HV);
		renameMap.put("Vertias of the Waters", Unit.WV);
		renameMap.put("Arigas", Unit.Arigas);
		renameMap.put("Knight Deltia", Unit.KDeltia);
		renameMap.put("Thunder God", Unit.Cid);
		renameMap.put("Deltia", Unit.Deltia);
	}
	//stopgap to rename stuff from data files, called for every unit parsed when reading data
	public static Unit filter(String name){
		if(rename.renameMap.containsKey(name)){
			return rename.renameMap.get(name);
		}
		return null;
	}
}
