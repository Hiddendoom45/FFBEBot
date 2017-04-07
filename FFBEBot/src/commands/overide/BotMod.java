package commands.overide;

import java.util.HashMap;

import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import util.Lib;
public class BotMod implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("add")){
			for(User u:event.getMessage().getMentionedUsers()){
				if(!u.isBot()){
					mod(u.getId(),event.getGuild());
					Lib.sendMessage(event, "Added user "+u.getName()+" as a mod of this bot");
				}
			}
		}
		else if(args.containsKey("remove")){
			for(User u:event.getMessage().getMentionedUsers()){
				if(!u.isBot()){
					unMod(u.getId(),event.getGuild());
					Lib.sendMessage(event, "Removed user "+u.getName()+" as a mod of this bot");
				}
			}
		}
	}
	private void mod(String id,Guild guild){
		Settings set=SaveSystem.getGuild(guild.getId()).addModded(id);
		SaveSystem.setSetting(set);
	}
	private void unMod(String id,Guild guild){
		Settings set=SaveSystem.getGuild(guild.getId()).removeModded(id);
		SaveSystem.setSetting(set);
	}
	@Override
	public void help(MessageReceivedEvent event) {
		

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {

	}

}
