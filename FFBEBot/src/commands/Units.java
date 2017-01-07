package commands;

import java.io.IOException;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Selection;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Units extends UnitSelection implements Command,Selection {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Searched for unit "+(args.length>0?args[0]:"")+" by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		return super.called(args,event);
	}
	public void sendUnitData(UnitInfo info,MessageReceivedEvent event){
		String out="";
		out+="```"+info.unitName+"\t";
		out+="Rarity:"+info.minRarity+"★ - "+info.maxRarity+"★\t";
		out+="Role:"+info.role+"\t";
		out+="TM:"+info.trustName;
		for(int i=0;i<info.stats.stats.length;i++){
			out+="\n"+addStars(info.stats.stats[i].rarity);
			out+="HP:"+Lib.pad(info.stats.stats[i].HP, 6);
			out+="MP:"+Lib.pad(info.stats.stats[i].MP, 6);
			out+="ATK:"+Lib.pad(info.stats.stats[i].ATK, 6);
			out+="DEF:"+Lib.pad(info.stats.stats[i].DEF, 6);
			out+="MAG:"+Lib.pad(info.stats.stats[i].MAG, 6);
			out+="SPR:"+Lib.pad(info.stats.stats[i].SPR, 6);
		}
		out+="```\n";
		out+=info.URL;
		out+=info.imgOverviewURL;
		Lib.sendMessage(event, out);
	}
	public String addStars(String s){
		char[] letter=s.toCharArray();
		String out="";
		for(char c:letter){
			if(Character.isDigit(c)){
				out+=c+"*";
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
				+ "\tdisplay information about specific unit";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

	@Override
	public void onePossible(UnitOverview Ounit, MessageReceivedEvent event) throws IOException {
		sendUnitData(new UnitInfo(Ounit.getData(0).unitUrl),event);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		onePossible(Ounit,event);
	}
	@Override
	public void manyPossible(UnitOverview Ounit, int selection, MessageReceivedEvent event) throws IOException {
		sendUnitData(new UnitInfo(Ounit.getData(selection).unitUrl),event);
	}
	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event) throws IOException {
		manyPossible(Ounit,selection,event);
	}

}
