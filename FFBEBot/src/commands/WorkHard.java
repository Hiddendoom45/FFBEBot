package commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.CmdHistory;
import util.HistoryLLNode;
import util.Lib;
import util.rng.RandomLibs;

public class WorkHard extends CommandGenerics{

	@Override
	public void action(String[] args, MessageReceivedEvent event){
		int since = CmdHistory.getHist(event).msgSince(new Summon());
		System.out.println(since);
		if(since>10||since==-1){
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("We work hard for our rainbows", "https://old.reddit.com/r/FFBraveExvius/comments/aldcab/gumis_message_explaining_the_lack_of_prisms_in/");
			embed.setDescription("```We have received questions from some players whether the limited time units’ prisms will be available. Unfortunately, the prisms will not be available as part of exchange items for the release of Ver 3.4.0.\n\n"
					+ "There are 2 reasons for this. The first reason is that we would like to consider the players who have worked hard to obtain the limited time units. Another reason is that as you can see in the current SOA event or the next Lunar\n\n"
					+ "New Year event, the chances of limited time units re-appearing in the game are specifically planned for these events.\n\n"		
					+ "For these reasons, we have included a 5 star Guaranteed Ex Summon ticket, since we would like to broaden the opportunity for obtaining units by exchanging Trust Coins.\n\n"
					+ "However, we have plans to update the Trust Coins Item Exchange list in the future. In regards to the limited time units’ prisms, we plan to implement them whilst considering what the best timing is.```");
			Lib.sendEmbed(event, embed);
		}
		else{
			HistoryLLNode hist = CmdHistory.getHist(event).lastInstance(new Summon());
			int num5 = Integer.parseInt(hist.getValue("5star"));
			int count = Integer.parseInt(hist.getValue("totalCount"));
			String auth = hist.getValue("author");
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("You work hard for your rainbows");
			embed.setColor(new Color(0, 0, 0x80));
			String desc = "<@"+auth+">";
			if(num5>0&&num5*100/count>0){
				if(count>100){
					desc += RandomLibs.SelectRandom(new String[]{
							" you've worked hard paying off you whaling debt for "+num5+" rainbow"+(num5>1?"s":""),
							" you've worked hard grinding your F2P lapis",
							" you've worked hard buying lapis for "+num5+" rainbow"+(num5>1?"s":""),
							" you've worked hard for your "+num5+" rainbow"+(num5>1?"s":""),
							" you've worked hard for your "+num5+" rainbow"+(num5>1?"s":""),
							" you've worked hard praying to RNGesus for "+num5+" rainbow"+(num5>1?"s":""),
							" you've worked hard hoarding your lapis"
					});
				}
				else{
					desc += " you've worked hard for your "+num5+" rainbow"+(num5>1?"s":"");
				}
			}
			else{
				desc += " you need to work harder if you want more rainbows";
			}
			embed.setDescription(desc);
			Lib.sendEmbed(event, embed);
		}
	}

	@Override
	public void help(MessageReceivedEvent event){
		Lib.sendMessage(event, "Why we work hard for our rainbows");
	}

}
