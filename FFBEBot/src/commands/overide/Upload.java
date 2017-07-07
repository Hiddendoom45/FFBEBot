package commands.overide;

import java.io.File;
import java.util.HashMap;

import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
public class Upload extends OverrideGenerics implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return ownerValidate(event);
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("d")||args.containsKey("data")){
			Lib.sendFile(event, "null", new File(Settings.dataSource));
		}
		if(args.containsKey("log")||args.containsKey("l")){
			Lib.sendFile(event, "null", new File("FFBEBotLog"));
		}
		if(args.containsKey("override")||args.containsKey("overrides")){
			Lib.sendFile(event, "null", new File("overrides"));
		}
		if(args.containsKey("p")||args.containsKey("preload")){
			Lib.sendFile(event, "null", new File(Settings.preloadData));
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
