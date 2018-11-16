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
		EmbedBuilder embed=new EmbedBuilder();
		embed.setAuthor(info.unitName+"    Rarity:"+info.minRarity+"★ - "+info.maxRarity+"★\t",info.URL);
		embed.setDescription("Role:"+info.role+"\n"+"[unit link]("+Lib.sUrl(info.URL)+")");
		if(!(info.trustDetails==null)){
			embed.addField(new Field("TM - "+info.trustName,"[TM link]("+Lib.sUrl(info.trustLink)+")\n"+info.trustDetails,false));
		}
		if(!(info.sTrustDetails==null)){
			embed.addField(new Field("STM - "+info.sTrustName,"[STM link]("+Lib.sUrl(info.sTrustLink)+")\n"+info.sTrustDetails,false));
		}
		embed.setColor(2584805);
		String hp = "";
		String mp = "";
		String atk = "";
		String def = "";
		String mag = "";
		String spr = "";
		for(int i =0;i<info.stats.stats.length;i++){
			hp+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].HP, 6)+Lib.pad("("+info.statIncrease.stats[i].HP+")", 6)+"\n";
			mp+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].MP, 6)+Lib.pad("("+info.statIncrease.stats[i].MP+")", 6)+"\n";
			atk+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].ATK, 6)+Lib.pad("("+info.statIncrease.stats[i].ATK+")", 6)+"\n";
			def+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].DEF, 6)+Lib.pad("("+info.statIncrease.stats[i].DEF+")", 6)+"\n";
			mag+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].MAG, 6)+Lib.pad("("+info.statIncrease.stats[i].MAG+")", 6)+"\n";
			spr+=info.stats.stats[i].rarity+"★:"+Lib.pad(info.stats.stats[i].SPR, 6)+Lib.pad("("+info.statIncrease.stats[i].SPR+")", 6)+"\n";
		}
		embed.addField(new Field("Stats","maxvalue(pot cap)",false));
		embed.addField("HP",hp,true);
		embed.addField("MP",mp,true);
		embed.addField("ATK",atk,true);
		embed.addField("DEF",def,true);
		embed.addField("MAG",mag,true);
		embed.addField("SPR",spr,true);
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
		if(Ounit.getData(0).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(0).name+" has not been created yet");
		}
		else{
			sendUnitData(SaveSystem.getExviusUnit(Ounit.getData(0).name),event);
		}
	}
	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event) throws IOException {
		if(Ounit.getData(selection).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(selection).name+" has not been created yet");
		}
		else{
			sendUnitData(SaveSystem.getExviusUnit(Ounit.getData(selection).name),event);
		}
	}
}
