package commands;

import commands.Duel.DuelInfo;
import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.CmdHistory;
import util.HistoryLL;
import util.HistoryLLNode;
import util.Lib;

public class DuelEnd extends CommandGenerics{

	@Override
	public void action(String[] args, MessageReceivedEvent event){
		if(Duel.duels.containsKey(event.getAuthor().getId())){
			Lib.sendEmbed(event, endDuel(Duel.duels.get(event.getAuthor().getId())));
		}
		else{
			Lib.sendMessage(event, "You are currently not in a summon duel with anyone");
		}
	}
	
	public MessageEmbed endDuel(DuelInfo duel){
		if(Duel.duels.containsValue(duel)){
			if(Main.jda.getSelfUser().getId().equals(duel.duelist.getId())){
				Summon.customMap.remove(duel.challenger.getIdLong());
			}
			HistoryLL hist = CmdHistory.getHist(duel.channel);
			HistoryLLNode info = hist.lastInstance(new Summon());
			int chScore = 0;
			int chSummons = 0;
			int duScore = 0;
			int duSummons = 0;
			while(info!=null&&info.getTimeRecorded()>duel.start){
				int points = 0;
				points += Integer.parseInt(info.getValue("5star"))*25;
				points += Integer.parseInt(info.getValue("4star"))*5;
				points += Integer.parseInt(info.getValue("3star"))*2;
				if(info.getValue("author").equals(duel.challenger.getId())){
					chScore += points;
					chSummons += Integer.parseInt(info.getValue("totalCount"));
				}
				else if(info.getValue("author").equals(duel.duelist.getId())){
					duScore += points;
					duSummons += Integer.parseInt(info.getValue("totalCount"));
				}
				info = hist.previousInstance(info);
			}
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Result of the duel between "+duel.duelist.getName()+" and "+duel.challenger.getName());
			embed.addField("Scoring", "5★ = 25 points\n4★ = 5 points\n3★ = 2 points", false);
//			if(chSummons==duSummons){
				if(duScore==chScore){
					embed.addField("Results", "A Tie", false);
				}
				else{
					embed.addField("Results", (duScore>chScore?duel.duelist.getName():duel.challenger.getName())+" Wins!", false);
				}
//			}
//			else{
//				
//			}
			embed.addField(duel.challenger.getName(), chScore+" points in "+chSummons+" Summons", true);
			embed.addField(duel.duelist.getName(), duScore+" points in "+duSummons+" Summons",true);
			
			return embed.build();
		}
		return null;
		
	}

	@Override
	public void help(MessageReceivedEvent event){
		String s = SaveSystem.getPrefix(event)+"duelend \n"
				+ "\tStarts a 5 minute summon duel with the mentioned person";
		Lib.sendMessage(event, s);
	}

}
