package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Selection;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Units extends UnitSelection implements Command,Selection {
	public void sendUnitData(UnitInfo info,MessageReceivedEvent event){
		String stats="";
		for(int i=0;i<info.stats.stats.length;i++){
			stats+="\n"+addStars(info.stats.stats[i].rarity);
			stats+="**HP**:"+Lib.pad(info.stats.stats[i].HP, 6)+Lib.pad("("+info.statIncrease.stats[i].HP+")", 6);
			stats+="**MP**:"+Lib.pad(info.stats.stats[i].MP, 6)+Lib.pad("("+info.statIncrease.stats[i].MP+")", 6);
			stats+="**ATK**:"+Lib.pad(info.stats.stats[i].ATK, 6)+Lib.pad("("+info.statIncrease.stats[i].ATK+")", 6);
			stats+="**DEF**:"+Lib.pad(info.stats.stats[i].DEF, 6)+Lib.pad("("+info.statIncrease.stats[i].DEF+")", 6);
			stats+="**MAG**:"+Lib.pad(info.stats.stats[i].MAG, 6)+Lib.pad("("+info.statIncrease.stats[i].MAG+")", 6);
			stats+="**SPR**:"+Lib.pad(info.stats.stats[i].SPR, 6)+Lib.pad("("+info.statIncrease.stats[i].SPR+")", 6);
		}
		EmbedBuilder embed=new EmbedBuilder();
		embed.setAuthor(info.unitName+"    Rarity:"+info.minRarity+"★ - "+info.maxRarity+"★\t",info.URL);
		embed.setDescription("Role:"+info.role+"\n"+"[unit link]("+info.URL+")");
		if(!(info.trustLink==null)){
			embed.addField(new Field("TM - "+info.trustName,"[TM link]("+info.trustLink+")\n"+info.trustDetails,false));
		}
		if(!(info.trustLink==null)){
			embed.addField(new Field("STM - "+info.sTrustName,"[STM link]("+info.sTrustLink+")\n"+info.sTrustDetails,false));
		}
		embed.setColor(2584805);
		embed.addField(new Field("Stats",stats,false));
		embed.setImage(info.imgOverviewURL);
		Lib.sendEmbed(event, embed);
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
