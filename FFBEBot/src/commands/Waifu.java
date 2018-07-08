package commands;

import java.util.ArrayList;

import Library.Waifus;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RandomLibs;

public class Waifu extends CommandGenerics implements Command{
	
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Waifus select=RandomLibs.SelectRandom(Waifus.values());
		MessageEmbedImpl embed=new MessageEmbedImpl();
		embed.setImage(new ImageInfo(select.url, select.url, 500, 500));
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.add(new Field(select.name, Lib.FormatMessage(event, "%userMention% Happy Waifu Happy Laifu! Your Waifu is "+select.name), false));
		embed.setFields(fields);
		event.getChannel().sendMessage(embed).complete();
		Log.log("status", "Waifu found "+select.name);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"waifu\n"
				+ "\tlook for a waifu";
		Lib.sendMessage(event, s);
	}
}
