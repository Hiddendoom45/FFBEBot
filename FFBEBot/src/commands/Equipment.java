package commands;

import java.io.IOException;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
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
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Searched for equipment "+(args.length>0?"for "+Lib.extract(args):"")+" by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		return super.called(args,event);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendEquipment(SaveSystem.getExvicusUnit(Ounit.getData(0).name),event);
	}

	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendEquipment(SaveSystem.getExvicusUnit(Ounit.getData(selection).name),event);
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"equipment [unit]\n"
				+ "\tGet what equipment a unit can equip";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
