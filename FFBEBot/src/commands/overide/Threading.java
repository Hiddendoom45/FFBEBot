package commands.overide;

import java.util.HashMap;

import util.CounterPool;
import util.Lib;
import global.Restarter;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Threading extends OverrideGenerics implements OverrideCommand{
	
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
		if(args.containsKey("cp")){//counterpool
			trigger=true;
			CounterPool.getPool().setup();
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

}
