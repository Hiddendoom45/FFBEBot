package commands;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class SacredCrystal extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0){
			if(event.getMessage().getMentionedUsers().size()>0){
				Data user=SaveSystem.getUser(event.getMessage().getMentionedUsers().get(0).getId());
				String s="%mentionName% has "+user.SacredCrystal+" %sacredCrystal%";
				Lib.sendMessageWithSpecials(event, s);
				return;
			}
			else{
					Member m=Lib.seachUser(args[0], event.getGuild());
					if(!(m==null)){
						Data user=SaveSystem.getUser(m.getUser().getId());
						String s=m.getEffectiveName()+" has "+user.SacredCrystal+" %sacredCrystal%";
						Lib.sendMessageEmoted(event, s);
					}
			}
		}
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		String s="%userName% has "+user.SacredCrystal+" %sacredCrystal%";
		Lib.sendMessageWithSpecials(event, s);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="sc [user]\n"
				+ "\tdisplays the # of %sacredCrystal% you have\n"
				+ "\t[user] either mention user or type user ID/name to get amount of sacred crystal they have";
		Lib.sendMessageEmoted(event, s);
	}

}
