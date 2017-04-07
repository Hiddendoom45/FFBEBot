package commands.mod;

import commands.Command;
import global.Restarter;
import global.record.Log;
import global.record.SaveSystem;
import util.Lib;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Sleep implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		if(event.isFromType(ChannelType.PRIVATE)){
			return false;
		}
		Log.log("status", "Bot slept by "+event.getAuthor().getName()+" on "+event.getGuild().getName());
		return true;
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Lib.sendMessage(event, "Bot sleeping for "+(args.length>0&&util.Lib.isNumber(args[0])?Integer.parseInt(args[0]):30)+" seconds");
		Restarter.sleep=args.length>0&&util.Lib.isNumber(args[0])?Integer.parseInt(args[0]):30;
		Restarter.sleep(0);
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getModPrefix(event)+"log [timeout]"
				+ "\nsleeps (disconnects) the bot for a specified period of time"
				+ "\nNo idea why you would want to do this, though it's something the bot does do normally, automatically";
		Lib.sendMessage(event, s);
	}
	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		
	}

}
