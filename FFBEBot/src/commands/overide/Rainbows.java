package commands.overide;

import java.util.HashMap;

import commands.Summon;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.summon.Pull;

public class Rainbows extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event){
		if(args.containsKey("id")){
			long id = Long.parseLong(args.get("id")[0]);
			if(args.containsKey("remove")){
				Summon.customMap.remove(id);
			}
			else{
				Summon.customMap.put(id, (num, banner)->{
					return Pull.pull5base(num, banner);
				});
			}
		}
	}

	@Override
	public void help(MessageReceivedEvent event){
		String s = "rainbows -id -remove";
		Lib.sendMessage(event, s);
	}

}
