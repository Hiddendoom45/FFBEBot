package commands;

import Library.Husbandos;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RandomLibs;

public class Husbando extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Object h=RandomLibs.SelectRandom(Husbandos.values());
		if(h instanceof Husbandos){
			Husbandos select=(Husbandos)h;
			Lib.sendMessageFormated(event, "%userMention% A Husbando? Your Husbando is "+select.name+"\n"+select.url);
			Log.log("status", "Husbando found "+select.name);
		}
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"husbando\n"
				+ "\tlook for a husbando";
		Lib.sendMessage(event, s);
		
	}

}
