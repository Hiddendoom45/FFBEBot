package Library;

import java.util.ArrayList;

import util.rng.RandomLibs;

public class SaltItems {

	public static ArrayList<String> getSalts(){
		ArrayList<String> salts=new ArrayList<String>();
		for(Salts s:Salts.values()){
			salts.add(saltyFormat(s.text));
		}
		return salts;
	}

	private static String saltyFormat(String salt){
		return salt.replace("%superSalt%", ((SuperSalts)RandomLibs.SelectRandom(SuperSalts.values())).text)
				.replace("%poopSalt%", ((PoopSalts)RandomLibs.SelectRandom(PoopSalts.values())).text)
				.replace("%mildSalt%", ((MildSalts)RandomLibs.SelectRandom(MildSalts.values())).text);
	}
	private enum Salts{
		Tilith("https://exviuswiki.com/images/f/f3/Unit-Tilith-5.png"),
		Daily("%userMention% I got a %superSalt% from daily pull today!!!"),
		Luneth("%userMention% I pulled a Luneth while waiting in line to vote!!!"),
		Halloween("%userMention% I don't care if they're not that good but I got 2 %poopSalt% 1 11 pull"),
		ElevenPull("%userMention% in one 10+1 pull I got %superSalt% and %mildSalt%");

		public String text;
		Salts(String text){
			this.text=text;
		}
	}
	private enum MildSalts{
		Tilith("Tilith"),
		Refia("Refia"),
		Arigas("Arigas"),
		Cecil("Cecil"),
		DKCecil("Dark Knight Cecil"),
		Ramza("Ramza");
		
		public String text;
		MildSalts(String text){
			this.text=text;
		}
	}
	private enum PoopSalts{
		DracuLasswell("Dracu Lasswell"),
		DemonRain("Demon Rain");
		
		public String text;
		PoopSalts(String text){
			this.text=text;
		}
		
	}
	private enum SuperSalts{
		Lightning("Lightning"),
		Luneth("Luneth"),
		Deltia("Deltia");
		
		public String text;
		SuperSalts(String text){
			this.text=text;
		}
	}
}
