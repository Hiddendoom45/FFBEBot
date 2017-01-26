package commands.overide;

import java.util.HashMap;

import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
