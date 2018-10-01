package commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.androidpit.colorthief.ColorThief;
import global.record.SaveSystem;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditOverview.unitData;

public class Flair extends RedditSelection {

	public void sendFlair(MessageReceivedEvent event,unitData data){
		EmbedBuilder embed=new EmbedBuilder();
		embed.setTitle("Flair for "+data.name);
		String imgUrl = String.format("https://raw.githubusercontent.com/Hiddendoom45/FFBEBot/master/FFBEBot/src/Library/flair/unit/u%03d.png", data.unitID);
		if(Lib.exists(imgUrl)){
			embed.setImage(imgUrl);
		}
		try {
			BufferedImage flair = ImageIO.read(getClass().getResourceAsStream(String.format("/Library/flair/unit/u%03d.png", data.unitID)));
			int[] rgb = ColorThief.getColor(flair);
			embed.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		} catch (Exception e) {
			//supress exceptions from failing to set flair color stuffs
		}
		embed.addField(new Field("CSS", "```\n["+data.name+"](#I/Icons/u"+data.unitID+")\n```", true));
		Lib.sendEmbed(event, embed);
		
	}
	
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendFlair(event,Ounit.getData(0));
	}

	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendFlair(event,Ounit.getData(selection));
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s = SaveSystem.getPrefix(event)+"flair [unitname]\n"
				+"\tdisplays the flair for the unit as well as the code for it\n"
				+ "\t[unitname] the name or id of the unit";
		Lib.sendMessage(event, s);
	}

}
