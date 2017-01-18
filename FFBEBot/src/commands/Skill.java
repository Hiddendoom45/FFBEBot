package commands;

import java.io.IOException;

import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class Skill extends UnitSelection {

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"skill [unit]\n"
				+ "\tGets the skills for specified unit";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		
	}
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", "Searched for skills "+(args.length>0?"for "+Lib.extract(args):"")+" by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		return super.called(args,event);
	}
	private void sendAbilities(UnitInfo info, MessageReceivedEvent event){
		String s=":pencil: Skills for:"+info.unitName;
		s+="\n";
		if(!(info.Magic==null)){
			s+="__Magic__: [Rarity unlocked|Level unlocked]";
			for(int i=0;i<info.Magic.abilities.length;i++){
				s+="\n"+Lib.pad("[**"+info.Magic.abilities[i].rarity+"|"+info.Magic.abilities[i].level+"**]",7)
				+" *"+info.Magic.abilities[i].name+"* | "+info.Magic.abilities[i].effect+" MP:**"+info.Magic.abilities[i].MP+"**";
			}
		}
		if(!(info.Special==null)){
			s+="\n\n__Special__: [Rarity unlocked|Level unlocked]";
			for(int i=0;i<info.Special.abilities.length;i++){
				s+="\n"+Lib.pad("[**"+info.Special.abilities[i].rarity+"|"+info.Special.abilities[i].level+"**]",7)
				+" *"+info.Special.abilities[i].name+"* | "+info.Special.abilities[i].effect+" MP:**"+info.Special.abilities[i].MP+"**";
			}
		}
		s+="";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getExvicusUnit(Ounit.getData(0).name),event);
		
	}

	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getExvicusUnit(Ounit.getData(selection).name),event);
		
	}

}
