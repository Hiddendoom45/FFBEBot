package global;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.entities.impl.MessageImpl;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.guild.GuildJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import util.Overrider;
import util.Selector;

public class BotListener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		try{
			if(Overrider.parseOverride(event))return;//test for override commands
			if(Selector.parseSelection(event))return;//test for pending selections
			//test for commands
			else if(event.getMessage().getContent().startsWith(SaveSystem.getPrefix(event))&&event.getMessage().getAuthor().getId()!=event.getJDA().getSelfInfo().getId()){
				Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
			}
			//same for mod commands
			else if(event.getMessage().getContent().startsWith(SaveSystem.getModPrefix(event))&&event.getMessage().getAuthor().getId()!=event.getJDA().getSelfInfo().getId()){
				Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
			}
			//base commands that are for prefixes
			else if(event.getMessage().isMentioned(event.getJDA().getSelfInfo())&&!event.getMessage().mentionsEveryone()){
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
		Main.log("status","logged in as: "+event.getJDA().getSelfInfo().getUsername());
	}
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		if(SaveSystem.getGuild(event.getGuild().getId())==null){
			SaveSystem.setSetting(new Settings(event.getGuild().getId()));
		}
	}
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		util.Lib.sendMessageFormated(event, SaveSystem.getJoin(event));
	}
}
