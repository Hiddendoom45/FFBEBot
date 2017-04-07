package commands.overide;

import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class SaveLog implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Log.save();
		Lib.sendMessage(event, "Log force saved");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {

	}

}
