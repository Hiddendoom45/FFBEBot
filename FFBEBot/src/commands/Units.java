package commands;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Selection;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Units extends UnitSelection implements Command,Selection {
	public void sendUnitData(UnitInfo info,MessageReceivedEvent event){
		String out="";
		out+="__**"+info.unitName+"**__\t";
		out+="Rarity:"+info.minRarity+"★ - "+info.maxRarity+"★\t";
		out+="Role:"+info.role+"\t";
		//out+="TM:"+info.trustName+"="+info.trustDetail;
		out+="\n__Stats__";
		for(int i=0;i<info.stats.stats.length;i++){
			out+="\n"+addStars(info.stats.stats[i].rarity);
			out+="**HP**:"+Lib.pad(info.stats.stats[i].HP, 6)+Lib.pad("("+info.statIncrease.stats[i].HP+")", 6);
			out+="**MP**:"+Lib.pad(info.stats.stats[i].MP, 6)+Lib.pad("("+info.statIncrease.stats[i].MP+")", 6);
			out+="**ATK**:"+Lib.pad(info.stats.stats[i].ATK, 6)+Lib.pad("("+info.statIncrease.stats[i].ATK+")", 6);
			out+="**DEF**:"+Lib.pad(info.stats.stats[i].DEF, 6)+Lib.pad("("+info.statIncrease.stats[i].DEF+")", 6);
			out+="**MAG**:"+Lib.pad(info.stats.stats[i].MAG, 6)+Lib.pad("("+info.statIncrease.stats[i].MAG+")", 6);
			out+="**SPR**:"+Lib.pad(info.stats.stats[i].SPR, 6)+Lib.pad("("+info.statIncrease.stats[i].SPR+")", 6);
		}
		//out+="\nlink to wiki::link:";
		//out+=info.URL+"";//discord now grabs the unit image from the link
		//out+="\n:art:"+info.imgOverviewURL;
		MessageEmbedImpl embed=new MessageEmbedImpl();
		embed.setAuthor(new AuthorInfo(info.unitName, "", null,null));
		embed.setDescription("[unit link]("+info.URL+")");
		List<Field> fields=new Vector<Field>();
		fields.add(new Field("TM - "+info.trustName,"[TM link]("+info.trustLink+")\n"+info.trustDetails.toString(),false));
		embed.setFields(fields);
		embed.setUrl(info.URL);
		//embed.setDescription("link");
		embed.setImage(new ImageInfo(info.imgOverviewURL, info.imgOverviewURL, 500, 500));
		Lib.sendMessage(event, out);
		event.getChannel().sendMessage(embed).complete();
	}
	public String addStars(String s){
		char[] letter=s.toCharArray();
		String out="";
		for(char c:letter){
			if(Character.isDigit(c)){
				out+=c+"★";
			}
			else{
				out+=c;
			}
		}
		return out;
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"units [unitname]\n"
				+ "\tdisplay general information about a unit\n"
				+ "\t[unitname] unit to get info for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendUnitData(SaveSystem.getExviusUnit(Ounit.getData(0).name),event);
	}
	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event) throws IOException {
		sendUnitData(SaveSystem.getExviusUnit(Ounit.getData(selection).name),event);
	}
}
