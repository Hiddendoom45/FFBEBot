package commands;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import global.Main;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.SpamControl;
/**
 * Basic command that gets the amount of time it takes for bot to receive message and prepare a response, error of time to send message
 * @author Allen
 *
 */
public class Ping extends CommandGenerics implements Command{
	private ArrayList<Integer> pingValues=new ArrayList<Integer>();
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Pinged with "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild().getName()));
		event.getChannel().sendTyping();
		return SpamControl.isSpam(event, "ping");
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
		OffsetDateTime message=event.getMessage().getTime();
		OffsetDateTime now=OffsetDateTime.now();
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
		pingValues.add(response);
		System.out.println(response);
		if(Settings.useAveragePing){
			Lib.sendMessage(event, "pong - difference from average response "+(int)(response-calculateAverage(pingValues))+"ms");
		}
		else{
			Lib.sendMessage(event,"pong - response in "+response+" ms");
		}
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"ping\n"
				+ "\tget the bot to say PONG\n"
				+ "\tto test the bot's response speed";
		Lib.sendMessage(event, s);
	}
	private double calculateAverage(List <Integer> marks) {
		  Integer sum = 0;
		  if(!marks.isEmpty()) {
		    for (Integer mark : marks) {
		        sum += mark;
		    }
		    return sum.doubleValue() / marks.size();
		  }
		  return sum;
		}

}
