package commands.mod;

import java.util.concurrent.TimeUnit;

import commands.Command;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class ModPrefix implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		if(event.isFromType(ChannelType.PRIVATE)){
			return false;
		}
		Log.log("status", "Mod Prefix changed to "+(args.length>0?args[0]:"")+" by "+event.getAuthor().getName()+" on "+event.getGuild());
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try{
			if(args.length>0){
				Settings guild=SaveSystem.getGuild(event.getGuild().getId());
				guild.guildModPrefix=args[0];
				SaveSystem.setSetting(guild);
				TimeUnit.SECONDS.sleep(1);
				SaveSystem.loadGuilds();
				Lib.sendMessage(event, "Mod Prefix changed to:"+args[0]);
			}
			else{
				Lib.sendMessage(event, "Must include modprefix ~!modprefix [modprefix]");
			}
			}catch(Exception e){
				Log.logError(e);
			}

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getModPrefix(event)+"modprefix [newModPrefix]"
				+ "Sets prefix of mod commands to the prefix specified";
		Lib.sendMessage(event, s);

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
