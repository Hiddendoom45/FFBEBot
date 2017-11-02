package commands.overide;

import java.util.HashMap;

import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Blacklist extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("r")){
			Settings.blacklist.remove(args.get("u")[0]);
			Lib.sendMessageEmoted(event, "Removed "+args.get("u")[0]+" to blacklist");
		}
		else if(args.containsKey("a")){
			Settings.blacklist.add(args.get("u")[0]);
			Lib.sendMessage(event, "Added "+args.get("u")[0]+" to blacklist");
		}
		else{
			help(event);
		}

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="r a u are args";
		Lib.sendMessageEmoted(event, s);

	}

}
