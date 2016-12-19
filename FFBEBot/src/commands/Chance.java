package commands;

import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;

public class Chance implements Command{
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", "gave chance of "+(args.length>0?args[0]:"")+" by "+event.getAuthorName()+(event.isPrivate()?"on "+event.getGuild():""));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Lib.sendMessage(event, event.getAuthor().getAsMention()+" chance of tilith is "+calculateChance(Integer.parseInt(args[0]),Integer.parseInt(args[1]))+"%");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public String calculateChance(int pulls,int pull){
		double base= (1-(Math.pow(0.6412242102,pulls))*(Math.pow(0.9666666,pull)))*100;
		return ""+base;
	}

}
