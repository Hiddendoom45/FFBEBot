package commands;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Library.Husbandos;
import de.androidpit.colorthief.ColorThief;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Sieghart extends CommandGenerics{

	@Override
	public void action(String[] args, MessageReceivedEvent event){
		Husbandos select = Husbandos.Sieghart;
		EmbedBuilder embed=new EmbedBuilder();
		embed.setImage(select.url);
		embed.addField(new Field(select.name, Lib.FormatMessage(event, "%userMention% Sieghard is the most beautiful."), false));
		try{
			BufferedImage img = ImageIO.read(select.unit.getImageLocation(select.unit.maxRarity()));
			int[] rgb = ColorThief.getColor(img);
			embed.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		}catch(Exception e){}//suppress errors since color isn't necessary
		Lib.sendEmbed(event, embed);
	}

	@Override
	public void help(MessageReceivedEvent event){
		Lib.sendMessage(event, "Sieghart's beauty is eternal");
	}

}
