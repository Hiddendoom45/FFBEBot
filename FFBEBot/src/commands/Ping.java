package commands;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;

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
		String s=SaveSystem.getPrefix(event)+"ping\n"
				+ "\tget the bot to say PONG\n"
				+ "\tto test the bot's response speed";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		return;
	}
	

}
