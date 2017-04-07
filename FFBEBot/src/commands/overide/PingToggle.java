package commands.overide;

import java.util.HashMap;

import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class PingToggle implements OverrideCommand{

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Settings.useAveragePing=!Settings.useAveragePing;
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
