package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Lore extends UnitSelection{

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"lore [unit][rarity]\n"
				+ "\tGets lore of unit at rarity(if specified)";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void sendLore(UnitInfo info,MessageReceivedEvent event,int rarity){
		Lib.sendMessage(event, ":pencil:Lore of:"+info.unitName+" at "+rarity+":star:\n"+info.lore[rarity-info.minRarity]);
	}

	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendLore(new UnitInfo(Ounit.getData(0).unitUrl),event,rarity);
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendLore(new UnitInfo(Ounit.getData(selection).unitUrl),event,rarity);
		
	}
}