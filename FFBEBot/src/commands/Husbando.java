package commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Library.Husbandos;
import de.androidpit.colorthief.ColorThief;
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
		Husbandos select=RandomLibs.SelectRandom(Husbandos.values());
		MessageEmbedImpl embed=new MessageEmbedImpl();
		embed.setImage(new ImageInfo(select.url, select.url, 500, 500));
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.add(new Field(select.name, Lib.FormatMessage(event, "%userMention% A Husbando? Your Husbando is "+select.name), false));
		embed.setFields(fields);
		try{
			BufferedImage img = ImageIO.read(select.unit.getImageLocation(select.unit.maxRarity()));
			int[] rgb = ColorThief.getColor(img);
			embed.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		}catch(Exception e){}//suppress errors since color isn't necessary
		event.getChannel().sendMessage(embed).complete();
		Log.log("status", "Husbando found "+select.name);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"husbando\n"
				+ "\tlook for a husbando";
		Lib.sendMessage(event, s);
		
	}

}
