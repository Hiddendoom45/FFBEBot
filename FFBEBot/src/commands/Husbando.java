package commands;

import java.util.ArrayList;

import Library.Husbandos;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RandomLibs;

public class Husbando extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Object h=RandomLibs.SelectRandom(Husbandos.values());
		if(h instanceof Husbandos){
			Husbandos select=(Husbandos)h;
			Lib.sendMessageFormated(event, "%userMention% A Husbando? Your Husbando is "+select.name);
			MessageEmbedImpl embed=new MessageEmbedImpl();
			embed.setImage(new ImageInfo(select.url, select.url, 500, 500));
			embed.setFields(new ArrayList<Field>());
			event.getChannel().sendMessage(embed).complete();
			Log.log("status", "Husbando found "+select.name);
		}
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"husbando\n"
				+ "\tlook for a husbando";
		Lib.sendMessage(event, s);
		
	}

}
