package commands.overide;

import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * basic stuff for resetting the log stuffs
 * @author Allen
 *
 */
public class ClearLog extends OverrideGenerics implements OverrideCommand{
	@Override
	public boolean called(java.util.HashMap<String,String[]> args, MessageReceivedEvent event) {
		return ownerValidate(event);
	}
	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		//archive creates copy of log w/ date archived and deletes it
		if(args.containsKey("a")||args.containsKey("archive")){
			System.out.println("archiving log");
			Log.archive();
			Lib.sendMessage(event, "Log archived");
		}
		//just deletes the log file, clears everything
		else if(args.containsKey("c")||args.containsKey("clear")){
			System.out.println("Clearing log");
			Log.clear();
			Lib.sendMessage(event, "Log cleared");
		}
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		
		
	}

}
