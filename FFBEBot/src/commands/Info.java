package commands;


import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

import global.Main;
import global.record.Settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * Experimental command currently used to test embed messages
 * @author Allen
 *
 */
public class Info extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		EmbedBuilder embed=new EmbedBuilder();
		embed.setAuthor("FFBEBot",null,Main.jda.getSelfUser().getEffectiveAvatarUrl());
		embed.addField(new Field("Servers", "Serving on "+Main.jda.getGuilds().size()+" servers", true));
		embed.addField(new Field("Channels","Serving on "+Main.jda.getTextChannels().size()+" channels",true));
		embed.addField(new Field("User","Serving "+Main.jda.getUsers().size()+" users",false));
		embed.addField(new Field("Last Launch",""+new SimpleDateFormat("[yyyy-MM-dd-HH:mm]").format(new Date(Settings.ID)),true));
		embed.addField(new Field("Memory",(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576+"MB/"+Runtime.getRuntime().totalMemory()/1048576+"MB",true));
		embed.addField(new Field("Credits","All data on the game taken from [the exvius wiki](https://exvius.gamepedia.com/) and [the reddit wiki](https://reddit.com/r/FFBraveExvius/)",true));
		embed.setColor(Color.GREEN);
		embed.setDescription("general info");
		embed.setTimestamp(OffsetDateTime.now());
		Lib.sendEmbed(event, embed);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
