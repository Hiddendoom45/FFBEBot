package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class REquipment extends RedditSelection {
	public void sendEquipment(RedditUnit info, MessageReceivedEvent event){
		String s=":pencil: Equipment for "+info.title;
		if(info.equipment==null){
			s+="\n-";
		}
		else{
			for(String e:info.equipment.split(",")){
				s+="\n\t"+e;
			}
		}
		Lib.sendMessage(event, s);
		
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		if(SaveSystem.getRedditUnit(Ounit.getData(0).name)==null){
			Lib.sendMessage(event, "The reddit wiki does not have a page for " + Ounit.getData(0).name);
			return;
		}
		sendEquipment(SaveSystem.getRedditUnit(Ounit.getData(0).name),event);
	}

	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		if(SaveSystem.getRedditUnit(Ounit.getData(selection).name)==null){
			Lib.sendMessage(event, "The reddit wiki does not have a page for " + Ounit.getData(selection).name);
			return;
		}
		sendEquipment(SaveSystem.getRedditUnit(Ounit.getData(selection).name),event);

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"requipment [unitname]\n"
				+ "\tgets the equipment a unit can equip\n"
				+ "\t[unitname] unit to get the equipment for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);

	}


}
