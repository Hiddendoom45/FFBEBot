package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Skill extends UnitSelection {

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"skill [unitname]\n"
				+ "\tGets the skills for unit\n"
				+ "\t[unitname] unit to get the skills for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
		
	}
	private void sendAbilities(UnitInfo info, MessageReceivedEvent event){
		String s=":pencil: Skills for:"+info.unitName;
		s+="\n";
		if(!(info.Magic==null)){
			s+="__Magic__: [**Rarity unlocked|Level unlocked**] *name* | effect MP";
			for(int i=0;i<info.Magic.abilities.length;i++){
				s+="\n"+Lib.pad("[**"+info.Magic.abilities[i].rarity+"|"+info.Magic.abilities[i].level+"**]",7)
				+" *"+info.Magic.abilities[i].name+"* | "+info.Magic.abilities[i].effect+" MP:**"+info.Magic.abilities[i].MP+"**";
			}
		}
		if(!(info.Special==null)){
			s+="\n\n__Special__: [**Rarity unlocked|Level unlocked**] *name* | effect MP";
			boolean activeTrig=true;
			boolean traitTrig=true;
			for(int i=0;i<info.Special.abilities.length;i++){
				if(activeTrig){
					if(info.Special.abilities[i].active){
						s+="\n*Active Abilities*";
						activeTrig=false;
					}
				}
				if(traitTrig){
					if(!info.Special.abilities[i].active){
						s+="\n*Traits*";
						traitTrig=false;
					}
				}
				s+="\n"+Lib.pad("[**"+info.Special.abilities[i].rarity+"|"+info.Special.abilities[i].level+"**]",7)
				+" *"+info.Special.abilities[i].name+"* | "+info.Special.abilities[i].effect+" MP:**"+info.Special.abilities[i].MP+"**";
			}
		}
		if(!(info.Special.conditionals==null)){
			s+="\n\n__Conditional__: [**Condition**] *name* | effect MP";
			for(int i=0;i<info.Special.conditionals.length;i++){
				s+="\n[**"+info.Special.conditionals[i].condition+"**] *"+info.Special.conditionals[i].name+"* | "
						+ info.Special.conditionals[i].effect+" MP:**"+info.Special.conditionals[i].MP+"**";
			}
		}
		s+="";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getExviusUnit(Ounit.getData(0).name),event);
		
	}

	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getExviusUnit(Ounit.getData(selection).name),event);
		
	}

}
