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
		String stat = "";
		for(int i=0;i<info.stats.length;i++){
			stat+="\n"+info.stats[i].rarity+"★";
			stat+="**HP**:"+Lib.pad(info.stats[i].HP.substring(info.stats[i].HP.indexOf("/")+2), 8);
			stat+="**MP**:"+Lib.pad(info.stats[i].MP.substring(info.stats[i].MP.indexOf("/")+2), 8);
			stat+="**ATK**:"+Lib.pad(info.stats[i].ATK.substring(info.stats[i].ATK.indexOf("/")+2), 8);
			stat+="**DEF**:"+Lib.pad(info.stats[i].DEF.substring(info.stats[i].DEF.indexOf("/")+2), 8);
			stat+="**MAG**:"+Lib.pad(info.stats[i].MAG.substring(info.stats[i].MAG.indexOf("/")+2), 8);
			stat+="**SPR**:"+Lib.pad(info.stats[i].SPR.substring(info.stats[i].SPR.indexOf("/")+2), 8);
		}
		embed.addField(new Field("Stats",stat,false));
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
