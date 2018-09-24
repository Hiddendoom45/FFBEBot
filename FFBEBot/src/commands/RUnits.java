package commands;

import java.io.IOException;
import java.util.ArrayList;

import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RUnits extends RedditSelection {
	public void sendUnitData(RedditUnit info,MessageReceivedEvent event){
		MessageEmbedImpl embed=new MessageEmbedImpl();
		embed.setTitle("__**"+info.title+"**__\t"+"Rarity:"+info.baseR+"-"+info.maxR+"\n");
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.add(new Field("TM", info.TrustDetails.substring("Trust Reward = ".length()), false));
		if(!info.STrustDetails.equals("")){
			fields.add(new Field("STM",info.STrustDetails.substring("Super Trust Reward = ".length()),false));
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
		fields.add(new Field("Stats",stat,false));
		fields.add(new Field("Link","[unit link]("+info.URL+")",false));
		embed.setFields(fields);
		event.getChannel().sendMessage(embed).complete();
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
