package commands;

import java.time.OffsetDateTime;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * Basic command that gets the amount of time it takes for bot to receive message and prepare a response, error of time to send message
 * @author Allen
 *
 */
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
		int messageTime=event.getMessage().getTime().getNano()/1000000;
		int currentTime=OffsetDateTime.now().getNano()/1000000;
		int messageS=event.getMessage().getTime().getSecond();
		int currentS=OffsetDateTime.now().getSecond();
		int responseS=currentS-messageS;
		int response;
		System.out.println(currentS+" "+messageS+" "+messageTime+" "+currentTime);
		if(responseS==0){
			response=currentTime-messageTime;
		}
		else{
			response=currentTime+1000-messageTime+(1000*(responseS-1));
		}
		event.getChannel().sendMessage("pong - response in "+response+" ms");
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
