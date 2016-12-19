package commands;

import global.Main;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Ping implements Command{
	public Ping(){
		
	}
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Pinged with "+event.getAuthorName()+" on "+event.getGuild().getName());
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		event.getChannel().sendMessage("pong");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		event.getChannel().sendMessage("get bot to say pong");
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		return;
	}
	

}
