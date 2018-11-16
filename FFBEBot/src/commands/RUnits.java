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
import util.unit.RedditUnit;

public class RUnits extends RedditSelection {
	public void sendUnitData(RedditUnit info,unitData data,MessageReceivedEvent event){
		EmbedBuilder embed=new EmbedBuilder();
		embed.setAuthor(""+data.name+" ["+data.JPname+"]"+"\t"+"Rarity:"+info.baseR+"-"+info.maxR+"\n",info.URL);
		embed.addField(new Field("TM", info.TrustDetails.substring("Trust Reward = ".length()), false));
		if(!info.STrustDetails.equals("")){
			embed.addField(new Field("STM",info.STrustDetails.substring("Super Trust Reward = ".length()),false));
		}
		String hp = "";
		String mp = "";
		String atk = "";
		String def = "";
		String mag = "";
		String spr = "";
		for(int i = 0; i < info.stats.length; i++){
			hp+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].HP, 10)+"\n";
			mp+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].MP, 10)+"\n";
			atk+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].ATK, 10)+"\n";
			def+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].DEF, 10)+"\n";
			mag+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].MAG, 10)+"\n";
			spr+=info.stats[i].rarity+"★:"+Lib.pad(info.stats[i].SPR, 10)+"\n";
		}
		embed.addField(new Field("Stats","basevalue/maxvalue(pot cap)[doorpot cap]",false));
		embed.addField("HP",hp,true);
		embed.addField("MP",mp,true);
		embed.addField("ATK",atk,true);
		embed.addField("DEF",def,true);
		embed.addField("MAG",mag,true);
		embed.addField("SPR",spr,true);
		
		if(!(data.unitUrl==null&&data.dbUrl==null&&data.famitsuUrl==null)){
			embed.addField(new Field("Links",
					(data.unitUrl==null?"":"[reddit link]("+Lib.sUrl(data.unitUrl)+")\n")
					+ (data.dbUrl==null?"":"[exviusdb link]("+Lib.sUrl(data.dbUrl)+")\n")
					+ (data.famitsuUrl==null?"":"[famitsu link]("+Lib.sUrl(data.famitsuUrl)+")"),
					false));
		}
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
		Lib.sendEmbed(event, embed);
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		if(SaveSystem.getRedditUnit(Ounit.getData(0).name)==null){
			Lib.sendMessage(event, "The reddit wiki does not have a page for " + Ounit.getData(0).name);
			return;
		}
		sendUnitData(SaveSystem.getRedditUnit(Ounit.getData(0).name),Ounit.getData(0),event);

	}
	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		if(SaveSystem.getRedditUnit(Ounit.getData(selection).name)==null){
			Lib.sendMessage(event, "The reddit wiki does not have a page for " + Ounit.getData(selection).name);
			return;
		}
		sendUnitData(SaveSystem.getRedditUnit(Ounit.getData(selection).name),Ounit.getData(selection),event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"runits [unitname]\n"
				+ "\tdisplay general information about a unit\n"
				+ "\t[unitname] unit to get info for(doesn't have to be the full name) or the id";
		Lib.sendMessage(event, s);
	}
}
