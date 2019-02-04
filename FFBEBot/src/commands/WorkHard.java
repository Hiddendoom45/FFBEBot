package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class WorkHard extends CommandGenerics{

	@Override
	public void action(String[] args, MessageReceivedEvent event){
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("We work hard for our rainbows", "https://old.reddit.com/r/FFBraveExvius/comments/aldcab/gumis_message_explaining_the_lack_of_prisms_in/");
		embed.setDescription("```We have received questions from some players whether the limited time units’ prisms will be available. Unfortunately, the prisms will not be available as part of exchange items for the release of Ver 3.4.0.\n\n"
					+ "There are 2 reasons for this. The first reason is that we would like to consider the players who have worked hard to obtain the limited time units. Another reason is that as you can see in the current SOA event or the next Lunar\n\n"
					+ "New Year event, the chances of limited time units re-appearing in the game are specifically planned for these events.\n\n"		
					+ "For these reasons, we have included a 5 star Guaranteed Ex Summon ticket, since we would like to broaden the opportunity for obtaining units by exchanging Trust Coins.\n\n"
					+ "However, we have plans to update the Trust Coins Item Exchange list in the future. In regards to the limited time units’ prisms, we plan to implement them whilst considering what the best timing is.```");
		Lib.sendEmbed(event, embed);
	}

	@Override
	public void help(MessageReceivedEvent event){
		Lib.sendMessage(event, "Why we work hard for our rainbows");
	}

}
