package commands;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public interface Command {
	public boolean called(String[] args,MessageReceivedEvent event);
	public void action(String[] args, MessageReceivedEvent event);
	public void help(MessageReceivedEvent event);
	public void executed(boolean sucess, MessageReceivedEvent event);
	
}
