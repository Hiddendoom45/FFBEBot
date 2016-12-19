package commands;

import Lib.Waifus;
import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RandomLibs;

public class Waifu implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", "Searching for a Waifu for "+event.getAuthorName()+(event.isPrivate()?" on "+event.getGuild().getName():""));;
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Object w=RandomLibs.SelectRandom(Waifus.values());
		if(w instanceof Waifus){
			Waifus select=(Waifus)w;
			Lib.sendMessageFormated(event, "%userMention% Happy Waifu Happy Laifu! Your Waifu is "+select.name+"\n"+select.url);
			Log.log("status", "Waifu found "+select.name);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "Look for a waifu");
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
