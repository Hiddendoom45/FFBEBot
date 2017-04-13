package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Equipment extends UnitSelection{
	public void sendEquipment(UnitInfo info, MessageReceivedEvent event){
		String s=":pencil: Equipment for "+info.unitName;
		s+="\nWeapons:";
		if(info.weapons==null){
			s+="\n-";
		}
		else{
			for(String w:info.weapons){
				s+="\n\t"+w;
			}
		}
		s+="\nArmours:";
		if(info.armours==null){
			s+="\n-";
		}
		else{
			for(String arm:info.armours){
				s+="\n\t"+arm;
			}
		}
		Lib.sendMessage(event, s);
		
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendEquipment(SaveSystem.getExviusUnit(Ounit.getData(0).name),event);
	}

	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendEquipment(SaveSystem.getExviusUnit(Ounit.getData(selection).name),event);
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"equipment [unitname]\n"
				+ "\tgets the equipment a unit can equip\n"
				+ "\t[unitname] unit to get the equipment for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
	}

}
