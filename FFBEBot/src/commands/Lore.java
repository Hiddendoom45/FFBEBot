package commands;

import java.io.IOException;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Lore extends UnitSelection{

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"lore [unitname][rarity]\n"
				+ "\tGets lore of a unit\n"
				+ "\t[unitname] unit to get lore for(doesn't have to be the full name)\n"
				+ "\t[rarity] gets lore for specified rarity, otherwise the general lore";
		Lib.sendMessage(event, s);
	}
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Searched for lore "+(args.length>0?"of "+Lib.extract(args):"")+" by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		return super.called(args,event);
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
		sendLore(SaveSystem.getExvicusUnit(Ounit.getData(0).name),event,rarity);
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendLore(SaveSystem.getExvicusUnit(Ounit.getData(selection).name),event,rarity);
		
	}
}