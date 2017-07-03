package commands;

import global.Main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Servers extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String s=" currently on "+Main.jda.getGuilds().size()+" servers";
		Lib.sendMessage(event, s);

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="servers\n"
				+ "\tdisplays the number of servers the bot is on";
		Lib.sendMessage(event, s);

	}

}
