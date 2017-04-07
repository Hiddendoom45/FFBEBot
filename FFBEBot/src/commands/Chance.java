package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Chance extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Lib.sendMessage(event, event.getAuthor().getAsMention()+" chance of tilith is "+calculateChance(Integer.parseInt(args[0]),Integer.parseInt(args[1]))+"%");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public String calculateChance(int pulls,int pull){
		double base= (1-(Math.pow(0.6412242102,pulls))*(Math.pow(0.9666666,pull)))*100;
		return ""+base;
	}

}
