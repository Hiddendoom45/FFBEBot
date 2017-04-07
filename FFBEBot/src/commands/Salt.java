package commands;

import Library.SaltItems;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RandomLibs;

public class Salt extends CommandGenerics implements Command {
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0){
			if(args[0].toLowerCase().equals("lighting")){
				Lib.sendMessage(event, "https://cdn.discordapp.com/attachments/201228937668460545/231208702621777920/Screen_Shot_2016-09-29_at_8.19.52_PM.png");
				return;
			}
			else if(args[0].toLowerCase().equals("dkcecil")){
				Lib.sendMessage(event, "https://cdn.discordapp.com/attachments/244580643349069824/246325669334614027/Screenshot_20161110-122832.png");
				return;
			}
			else if(args[0].toLowerCase().equals("ramza")){
				Lib.sendMessage(event, "https://cdn.discordapp.com/attachments/201228937668460545/233863461673631744/Screenshot_20161007-010434.png");
			}
		}
		Lib.sendMessageFormated(event,RandomLibs.SelectRandom(SaltItems.getSalts()));
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "Embrace the saltiness\n"
				+ Settings.prefix+"salt"
				+ "\t random salt invoking message"
				+ Settings.prefix+"salt [unit]"
				+ "\tshow everyone you're salty about that unit");

	}
}
