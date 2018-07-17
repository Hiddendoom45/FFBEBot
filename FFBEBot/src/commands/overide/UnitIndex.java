package commands.overide;

import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitOverview;

public class UnitIndex extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("name")){
			String s ="";
			UnitOverview Ounit = new UnitOverview();
			for(int i =0;i<Ounit.getNames().size();i++){
				if(Ounit.getData(i).name.toLowerCase().contains(Lib.extract(args.get("name")).toLowerCase())){
					s+=Ounit.getData(i).name+" "+i+"\n";
				}
			}
			if(s.equals(""))s="No Units with "+Lib.extract(args.get("name"));
			Lib.sendMessage(event, s);
		}
		else{
			Lib.sendMessage(event, "name arg needed");
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "-name [unitname]");
	}

}
