package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Equipment extends UnitSelection{
	public void sendEquipment(UnitInfo info, MessageReceivedEvent event){
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Equipment for "+info.unitName);
		String s="";
		if(info.weapons==null){
			s+="\n-";
		}
		else{
			for(String w:info.weapons){
				s+="\n\t"+w;
			}
		}
		embed.addField("Weapons",s,true);
		s = "";
		if(info.armours==null){
			s+="\n-";
		}
		else{
			for(String arm:info.armours){
				s+="\n\t"+arm;
			}
		}
		embed.addField("Armor",s,true);
		Lib.sendEmbed(event, embed);
		
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		if(Ounit.getData(0).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(0).name+" has not been created yet");
		}
		else{
			sendEquipment(SaveSystem.getExviusUnit(Ounit.getData(0).name),event);
		}
	}

	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		if(Ounit.getData(selection).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(selection).name+" has not been created yet");
		}
		else{
			sendEquipment(SaveSystem.getExviusUnit(Ounit.getData(selection).name),event);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"equipment [unitname]\n"
				+ "\tgets the equipment a unit can equip\n"
				+ "\t[unitname] unit to get the equipment for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
	}

}
