package commands;

import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
/**
 * Generic class for commands contains some things to make calling commands easier
 * @author Allen
 *
 */
public abstract class CommandGenerics implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", this.getClass()+" called by "+event.getAuthorName()+getGuildName(event));
		return true;
	}
	public static String getGuildName(MessageReceivedEvent event){
		return (event.isPrivate()?"":" on "+event.getGuild());
	}
	@Override
	public abstract void action(String[] args, MessageReceivedEvent event);

	@Override
	public abstract void help(MessageReceivedEvent event);

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		if(!sucess){
			Log.log("CMDERROR", this.getClass()+" failed when called by "+event.getAuthorName()+getGuildName(event));
		}

	}

}
