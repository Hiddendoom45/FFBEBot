package commands.overide;

import java.util.HashMap;

import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
/**
 * Generic class for override commands, made so that ajustments are easier
 * @author Allen
 *
 */
public abstract class OverrideGenerics implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Log.log("status", "User has executed override command "+this.getClass());
		return true;
	}
	public boolean ownerValidate(MessageReceivedEvent event){
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
	 * do nothing as this part has almost no use at the moment
	 */
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
