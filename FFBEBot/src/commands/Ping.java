package commands;

import java.time.OffsetDateTime;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.SpamControl;
/**
 * Basic command that gets the amount of time it takes for bot to receive message and prepare a response, error of time to send message
 * @author Allen
 *
 */
public class Ping extends CommandGenerics implements Command{
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Pinged with "+event.getAuthor().getName()+(event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild().getName()));
		event.getChannel().sendTyping();
		return SpamControl.isSpam(event, "ping");
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Message pingMsg=Lib.sendMessage(event, "pong...");//send precursor message
		//calculate time it took for response message to be sent and recieved
		OffsetDateTime message=event.getMessage().getCreationTime();
		OffsetDateTime now=pingMsg.getCreationTime();
		int messageTime=message.getNano()/1000000;//mili message
		int currentTime=now.getNano()/1000000;//mili current
		long messageS=message.toEpochSecond();//sec message
		long currentS=now.toEpochSecond();//sec current
		int responseS;//delta sec
		responseS=(int) Math.abs(currentS-messageS);//diff in sec
		if(OffsetDateTime.timeLineOrder().compare(message, now)==1){//invert signage based on which one's less
			responseS=responseS*-1;
		}
		int response;
		//System.out.println(nH+" "+mH+" "+currentS+" "+messageS+" "+currentTime+" "+messageTime);
		if(responseS==0){
			response=currentTime-messageTime;
		}
		else{
			response=currentTime-messageTime+(1000*(responseS));
		}
		Lib.editMessage(pingMsg, "pong! - response in "+response+"ms");
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"ping\n"
				+ "\tget the bot to say PONG\n"
				+ "\tto test the bot's response speed";
		Lib.sendMessage(event, s);
	}
}
