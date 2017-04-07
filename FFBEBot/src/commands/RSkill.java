package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RSkill extends RedditSelection{
	
	private void sendAbilities(RedditUnit info, MessageReceivedEvent event){
		String s=":pencil: Skills for:"+info.title;
		s+="\n";
		boolean NLTrigger=false;
		if(info.magic.length>0){
			NLTrigger=true;
			s+="__Magic__: [Rarity unlocked|Level unlocked] Name|Effect";
			for(int i=0;i<info.magic.length;i++){
				s+="\n"+Lib.pad("[**"+(info.magic[i].minRare==0?info.baseR:info.magic[i].minRare)+"|"+info.magic[i].level+"**]",7)
				+" *"+info.magic[i].jpName+"* | "+info.magic[i].effect+" MP:**"+info.magic[i].MP+"**";
			}
		}
		if(info.special.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Special__: [Rarity unlocked|Level unlocked] Name|Effect";
			for(int i=0;i<info.special.length;i++){
				s+="\n"+Lib.pad("[**"+(info.special[i].minRare==0?info.baseR:info.special[i].minRare)+"|"+info.special[i].level+"**]",7)
				+" *"+info.special[i].jpName+"* | "+info.special[i].effect+" MP:**"+info.special[i].MP+"**";
			}
		}
		if(info.enhance.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Enhancements__:[Cost]";
			for(int i=0;i<info.enhance.length;i++){
				s+="\n"+Lib.pad("[**"+info.enhance[i].gil+"**]", 15)
				+"*"+info.enhance[i].jpName+"*|"+info.enhance[i].effect+" MP:**"+info.enhance[i].MP+"**";
			}
		}
		if(info.sparks.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Sparks__:[%S2|%S3]";
			for(int i=0;i<info.sparks.length;i++){
				s+="\n"+Lib.pad("[**"+info.sparks[i].S2+"|"+info.sparks[i].S3+"**]",7)
				+"*"+info.sparks[i].name+"*|"+info.sparks[i].effect+"MP:**"+info.sparks[i].MP+"**";
			}
		}
		s+="";
		Lib.sendMessage(event, s);
		
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getRedditUnit(Ounit.getData(0).name),event);
		
	}
	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendAbilities(SaveSystem.getRedditUnit(Ounit.getData(selection).name),event);
		
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"rskill [unitname]\n"
				+ "\tGets the skills for unit\n"
				+ "\t[unitname] unit to get the skills for(doesn't have to be the full name)";
		Lib.sendMessage(event, s);
		
	}
}
