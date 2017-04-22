package commands;


import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import global.Main;
import global.record.Settings;
import net.dv8tion.jda.core.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Experimental command currently used to test embed messages
 * @author Allen
 *
 */
public class Info extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		MessageEmbedImpl embed=new MessageEmbedImpl();
		embed.setAuthor(new AuthorInfo("FFBEBot", "", Main.jda.getSelfUser().getAvatarUrl(),Main.jda.getSelfUser().getAvatarUrl()));
		List<Field> fields=new Vector<Field>();
		fields.add(new Field("Servers", "Serving on "+Main.jda.getGuilds().size()+" servers", true));
		fields.add(new Field("Channels","Serving on "+Main.jda.getTextChannels().size()+" channels",true));
		fields.add(new Field("User","Serving "+Main.jda.getUsers().size()+" users",false));
		fields.add(new Field("Last Launch",""+new SimpleDateFormat("[yyyy-MM-dd-HH:mm]").format(new Date(Settings.ID)),true));
		fields.add(new Field("Memory",(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576+"MB/"+Runtime.getRuntime().totalMemory()/1048576+"MB",true));
		embed.setFields(fields);
		embed.setColor(Color.GREEN);
		embed.setDescription("general info");
		embed.setTimestamp(OffsetDateTime.now());
		event.getChannel().sendMessage(embed).complete();
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
