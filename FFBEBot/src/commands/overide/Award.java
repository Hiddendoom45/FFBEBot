package commands.overide;

import java.util.HashMap;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;

public class Award extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(event.getMessage().getMentionedUsers().size()>0&&args.containsKey("lapis")&&Lib.isNumber(args.get("lapis")[0])){
			Data user=SaveSystem.getUser(event.getMessage().getMentionedUsers().get(0).getId());
			int lapis=Integer.parseInt(args.get("lapis")[0]);
			user.lapis+=lapis;
			SaveSystem.setUser(user);
			Lib.sendMessage(event, lapis+" lapis given to "+event.getMessage().getMentionedUsers().get(0).getAsMention());
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		
	}

}
