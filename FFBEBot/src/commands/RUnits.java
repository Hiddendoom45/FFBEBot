package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RUnits extends RedditSelection {
	public void sendUnitData(RedditUnit info,MessageReceivedEvent event){
		String out="";
		out+="__**"+info.title+"**__\t";
		out+="Rarity:"+info.baseR+"-"+info.maxR+"\t";
		out+=info.TrustDetails+"\n";
		for(int i=0;i<info.stats.length;i++){
			out+="\n"+info.stats[i].rarity+"★";
			out+="**HP**:"+Lib.pad(info.stats[i].HP.substring(info.stats[i].HP.indexOf("/")+2), 8);
			out+="**MP**:"+Lib.pad(info.stats[i].MP.substring(info.stats[i].MP.indexOf("/")+2), 8);
			out+="**ATK**:"+Lib.pad(info.stats[i].ATK.substring(info.stats[i].ATK.indexOf("/")+2), 8);
			out+="**DEF**:"+Lib.pad(info.stats[i].DEF.substring(info.stats[i].DEF.indexOf("/")+2), 8);
			out+="**MAG**:"+Lib.pad(info.stats[i].MAG.substring(info.stats[i].MAG.indexOf("/")+2), 8);
			out+="**SPR**:"+Lib.pad(info.stats[i].SPR.substring(info.stats[i].SPR.indexOf("/")+2), 8);
		}
		out+="\n:link:";
		out+=info.URL;
		Lib.sendMessage(event, out);
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendUnitData(SaveSystem.getRedditUnit(Ounit.getData(0).name),event);

	}
	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendUnitData(SaveSystem.getRedditUnit(Ounit.getData(selection).name),event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"runits [unitname]\n"
				+ "\tdisplay general information about a unit\n"
				+ "\t[unitname] unit to get info for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
	}
}
