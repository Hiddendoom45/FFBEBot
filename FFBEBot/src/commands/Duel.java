package commands;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Library.summon.UnitSpecific;
import global.Main;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.CmdControl;
import util.Lib;
import util.rng.summon.Pull;

public class Duel extends CommandGenerics{
	public static HashMap<String,DuelInfo> duels = new HashMap<String,DuelInfo>();
	@Override
	public void action(String[] args, MessageReceivedEvent event){
		if(event.getMessage().getMentionedUsers().size()==1){
			if(duels.containsKey(event.getMessage().getAuthor().getId())){
				Lib.sendMessage(event, "You are already in a duel with someone, end the duel with "
						+SaveSystem.getPrefix(event)+"duelend before starting a new one");
				return;
			}
			else if(duels.containsKey(event.getMessage().getMentionedUsers().get(0).getId())){
				Lib.sendMessage(event, "The person you are challenging is already in a duel with someone else");
			}
			DuelInfo duel = new DuelInfo(event.getAuthor(),
					event.getMessage().getMentionedUsers().get(0),
					System.currentTimeMillis(),
					event.getChannel().getIdLong());
			duels.put(duel.challenger.getId(), duel);
			duels.put(duel.duelist.getId(), duel);
			Lib.sendMessageFormated(event, "**%userName%** has started a summon duel with **%mentionName%**");
			if(Main.jda.getSelfUser().getId().equals(duel.duelist.getId())){
				Summon.customMap.put(event.getAuthor().getIdLong(), (num, banner)->{
					List<UnitSpecific> units;
					if(num==11){
						units = Pull.pull11(banner);
					}
					else{
						units = Pull.pull(num,banner);
					}
					((Summon)CmdControl.getInstance("summon")).botSummon(event, num, banner);
					return units;
				});
			}
			Settings.executor.schedule(()->{
				MessageEmbed embed = ((DuelEnd)CmdControl.getInstance("duelend")).endDuel(duel);
				if(embed!=null)Lib.sendEmbed(event, embed);
			}, 5, TimeUnit.MINUTES);
		}
		else if(event.getMessage().getMentionedUsers().size()==0){
			help(event);
		}
		else{
			Lib.sendMessage(event, "You can only duel one person at a time");
		}
	}

	@Override
	public void help(MessageReceivedEvent event){
		String s = SaveSystem.getPrefix(event)+"duel [@person to duel]\n"
				+ "\tStarts a 5 minute summon duel with the mentioned person";
		Lib.sendMessage(event, s);
		
	}
	public static class DuelInfo{
		User challenger;
		User duelist;
		long start;
		long channel;
		public DuelInfo(User challenger, User duelist, long start, long channel){
			this.challenger = challenger;
			this.duelist = duelist;
			this.start = start;
			this.channel = channel;
		}
		
		
	}
}
