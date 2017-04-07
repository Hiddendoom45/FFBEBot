package commands.overide;

import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface OverrideCommand {
	public boolean called(HashMap<String,String[]> args,MessageReceivedEvent event);
	public void action(HashMap<String,String[]> args, MessageReceivedEvent event);
	public void help(MessageReceivedEvent event);
	public void executed(boolean sucess, MessageReceivedEvent event);
}
