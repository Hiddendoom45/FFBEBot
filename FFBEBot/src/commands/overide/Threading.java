package commands.overide;

import java.util.HashMap;
import util.Lib;
import global.Restarter;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Threading implements OverrideCommand{

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		boolean trigger=false;
		if(args.containsKey("kill")){
			trigger=true;
			Settings.executor.shutdownNow();
			String s="Threads killed\n"
					+ Settings.executor.toString();
			Lib.sendMessage(event, s);
		}
		if(args.containsKey("r")){
			trigger=true;
			Restarter.setup();//restarts the sleep thread
			String s="Sleeper restarted";
			Lib.sendMessage(event, s);
		}
		//if no valid arguments
		if(!trigger){
			String s=Settings.executor.toString();
			Lib.sendMessage(event, s);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
