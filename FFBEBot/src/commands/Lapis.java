package commands;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Lapis extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user;
		if(event.getMessage().getMentionedUsers().size()>0){
			user=SaveSystem.getUser(event.getMessage().getMentionedUsers().get(0).getId());
		}
		else{
			user=SaveSystem.getUser(event.getAuthor().getId());
		}
		Lib.sendMessageWithSpecials(event, "%userName% has "+user.lapis+" %lapis%");
	}
	
	@Override
	public void help(MessageReceivedEvent event) {
		String s="lapis [@user]\n"
				+ "\tgets the amount of %lapis% you have\n"
				+ "\t[@user] gets the %lapis% for this person instead";
		Lib.sendMessageEmoted(event, s);

	}

}
