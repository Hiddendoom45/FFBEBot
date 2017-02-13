package commands.overide;

import java.io.File;
import java.util.HashMap;

import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
public class Upload extends OverrideGenerics implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return ownerValidate(event);
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("d")||args.containsKey("data")){
			event.getChannel().sendFile(new File(Settings.dataSource), null);
		}
		if(args.containsKey("log")||args.containsKey("l")){
			event.getChannel().sendFile(new File("FFBEBotLog"), null);
		}
		if(args.containsKey("override")||args.containsKey("overrides")){
			event.getChannel().sendFile(new File("overrides"),null);
		}
		if(args.containsKey("p")||args.containsKey("preload")){
			event.getChannel().sendFile(new File(Settings.preloadData), null);
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
