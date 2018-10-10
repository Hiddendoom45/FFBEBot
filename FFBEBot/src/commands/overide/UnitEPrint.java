package commands.overide;

import java.util.HashMap;

import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
import util.unit.UnitOverview.unitData;

public class UnitEPrint extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		UnitOverview Ounit = new UnitOverview();
		String s="";
		int upgrade = 0;
		if(args.containsKey("upgrade")){
			upgrade = Integer.parseInt(args.get("upgrade")[0]);
		}
		if(args.containsKey("i")){
			for(String ind:args.get("i")){
				try{
					unitData d = Ounit.getData(Integer.parseInt(ind));
					UnitInfo ui = SaveSystem.getExviusUnit(d.name);
					//build a line of the string
					s+=buildString(d,ui,upgrade);
				}catch(NumberFormatException e){};
			}
		}
		if(args.containsKey("start")&&args.containsKey("end")){
			try{
				for(int i =Integer.parseInt(args.get("start")[0]);i<=Integer.parseInt(args.get("end")[0]);i++){
					unitData d = Ounit.getData(i);
					//build line
					UnitInfo ui = SaveSystem.getExviusUnit(d.name);
					s+=buildString(d,ui,upgrade);
				}
			}catch(NumberFormatException | IndexOutOfBoundsException e){}
		}
		Lib.sendMessage(event, s);
	}
	private static String buildString(unitData d,UnitInfo ui,int upgrade){
		String s = "";
		s+=d.name+"(\""+d.name+"\",new String[]{";//first part with name
		for(int c =0;c<(upgrade>0?upgrade-ui.minRarity:ui.sprites.length);c++){//second part with urls
			s+="\""+ui.sprites[c].substring(Settings.ExvicusIMGURL.length())+"\"";
			if(c!=ui.sprites.length-upgrade-1){
				s+=",";
			}
		}
		
		s+="},"+ui.minRarity+")";//last part wth base rarity
		if(upgrade>0){
			s+="{\n\t@Override\n\tpublic void setup(){\n\t\t\tsetUpgrades(new String[]{";
			for(int c = upgrade-ui.minRarity;c<ui.sprites.length;c++){
				s+="\""+ui.sprites[c].substring(Settings.ExvicusIMGURL.length())+"\"";
				if(c!=ui.sprites.length-1){
					s+=",";
				}
			}
			s+="});\n\t\t}\n\t}";
		}
		s+=",\n";
		return s;
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "-start [index], -end [index], -i[index...]");

	}

}
