package commands;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Invite extends CommandGenerics implements Command {


	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String s="To add me to your server use :\n"
				+ "https://discordapp.com/oauth2/authorize?&client_id=244615809559822338&scope=bot&permissions=null&response_type=code\n"
				+ "-This bot does not need any permissions to work\n"
				+ "-If you enable message management permission for the bot will delete messages sent by users to select an options, when given prompt\n"
				+ "-This bot is inspired by @RainBot created by Sam Greenpuck on summoner's central";
		Lib.sendMessage(event, s);

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"invite\n"
				+ "get the link to invite the bot to your own server, and some basic info on the bot";
		Lib.sendMessage(event, s);

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
