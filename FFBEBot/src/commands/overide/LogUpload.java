package commands.overide;

import java.io.File;
import java.util.HashMap;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
public class LogUpload implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		event.getChannel().sendFile(new File("FFBEBotLog"), null);

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
