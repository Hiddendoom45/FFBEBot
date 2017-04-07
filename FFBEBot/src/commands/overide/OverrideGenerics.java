package commands.overide;

import java.util.HashMap;

import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Generic class for override commands, made so that adjustments are easier
 * @author Allen
 *
 */
public abstract class OverrideGenerics implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Log.log("status", event.getAuthor().getName()+" has executed override command "+this.getClass()+getGuildName(event) );
		return true;
	}
	public static String getGuildName(MessageReceivedEvent event){
		return (event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild());
	}
	public static boolean ownerValidate(MessageReceivedEvent event){
		if(event.getAuthor().getId().equals(Settings.ownerID)){//command only owner can access for various reasons
			return true;
		}
		return false;
	}
	@Override
	public abstract void action(HashMap<String, String[]> args, MessageReceivedEvent event);

	@Override
	public abstract void help(MessageReceivedEvent event);

	@Override
	/**
	 * record if override fails for some reason
	 */
	public void executed(boolean sucess, MessageReceivedEvent event) {
		if(!sucess){
			Log.log("OVRERROR", this.getClass()+" failed when called by "+event.getAuthor().getName()+getGuildName(event));
		}
	}

}
