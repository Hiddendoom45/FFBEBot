package global;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.CmdControl;
import util.Overrider;
import util.ReactionController;
import util.Selector;
/**
 * Handles all events
 * @author Allen
 *
 */
public class BotListener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		try{
			if(Overrider.parseOverride(event))return;//test for override commands
			if(Selector.parseSelection(event))return;//test for pending selections
			if(CmdControl.parseCommands(event))return;//test for commands
			//base commands that are for prefixes
			if(event.getMessage().isMentioned(event.getJDA().getSelfUser())&&!event.getMessage().mentionsEveryone()){
				if(event.getMessage().getContent().toLowerCase().contains("modprefix")){
					util.Lib.sendMessage(event, "mod prefix for server:"+SaveSystem.getModPrefix(event));
				}
				else if(event.getMessage().getContent().toLowerCase().contains("prefix")){
					util.Lib.sendMessage(event, "prefix for server:"+SaveSystem.getPrefix(event));
				}
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	@Override
	public void onReady(ReadyEvent event){
		Main.log("status","logged in as: "+event.getJDA().getSelfUser().getName());//get name of bot, for testing and other related purposes
	}
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		if(SaveSystem.getGuild(event.getGuild().getId())==null){
			SaveSystem.setSetting(new Settings(event.getGuild().getId()));
		}
	}
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		if(SaveSystem.getGuild(event.getGuild().getId()).tJoinMsg){
			util.Lib.sendMessageFormated(event, SaveSystem.getJoin(event));
		}
	}
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		if(ReactionController.parseReaction(event))return;
	}
}
