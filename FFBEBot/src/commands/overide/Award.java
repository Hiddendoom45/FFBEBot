package commands.overide;

import java.util.HashMap;
import java.util.Vector;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Award extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("lapis")&&Lib.isNumber(args.get("lapis")[0])){
			if(event.getMessage().getMentionedUsers().size()>0){
				Data user=SaveSystem.getUser(event.getMessage().getMentionedUsers().get(0).getId());
				int lapis=Integer.parseInt(args.get("lapis")[0].trim());
				user.lapis+=lapis;
				SaveSystem.setUser(user);
				Lib.sendMessage(event, lapis+" lapis given to "+event.getMessage().getMentionedUsers().get(0).getName());
			}
			else if(args.containsKey("all")){
				Vector<Data> users=SaveSystem.getRegisteredUsers();
				int lapis=Integer.parseInt(args.get("lapis")[0].trim());
				for(int i=0;i<users.size();i++){
					users.get(i).lapis+=lapis;
					SaveSystem.setUser(users.get(i));
				}
				Lib.sendMessage(event, lapis+" lapis given to *everyone*");
			}
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		
	}

}
