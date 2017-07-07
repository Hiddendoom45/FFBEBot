package commands;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class SacredCrystal extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		String s="%userName% has "+user.SacredCrystal+" %sacredCrystal%";
		Lib.sendMessageWithSpecials(event, s);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
