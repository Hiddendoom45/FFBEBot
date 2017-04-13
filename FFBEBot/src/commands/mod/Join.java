package commands.mod;

import java.util.concurrent.TimeUnit;

import commands.Command;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Join implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		if(event.isFromType(ChannelType.PRIVATE)){
			return true;
		}
		Log.log("status", "join message changed to "+stringArgs(args)+" by "+event.getAuthor().getName());
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try{
			if(args.length>0){
				Settings guild=SaveSystem.getGuild(event.getGuild().getId());
				guild.joinMsg=stringArgs(args);
				SaveSystem.setSetting(guild);
				TimeUnit.SECONDS.sleep(1);
				SaveSystem.loadGuilds();
				Lib.sendMessage(event, "Join message changed to:"+stringArgs(args));
			}
			else{
				Lib.sendMessage(event, "Must include message ~!join [Message]");
			}
			}catch(Exception e){
				Log.logError(e);
			}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="~!join [Message]"
				+ "\nSpecial formatting"
				+ "\n\t %userMention% writes name of user joined as mention"
				+ "\n\t %userName% writes name of user joined"
				+ "\n\t %guildName% writes name of server";
		Lib.sendMessage(event, s);
	}
	private String stringArgs(String[] args){
		String r="";
		for(String s:args){
			r+=s+" ";
		}
		return r;
	}
	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
