package commands.overide;

import java.util.HashMap;

import Library.summon.banner.Banner;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class DefaultBanner extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		for(Banner b:Banner.values()){
			if(args.containsKey(b.toString())){
				Settings.DefaultBanner=b;
				Lib.sendMessage(event, "Default Banner changed to "+b.name);
			}
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
