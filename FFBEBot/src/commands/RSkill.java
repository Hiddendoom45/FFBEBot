package commands;

import java.io.IOException;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RSkill extends RedditSelection{
	
	private void sendAbilities(RedditUnit info, MessageReceivedEvent event){
		String s=":pencil: Skills for:"+info.title;
		s+="\n```";
		if(!(info.magic==null)){
			s+="Magic: [Rarity unlocked|Level unlocked]";
			for(int i=0;i<info.magic.length;i++){
				s+="\n"+Lib.pad("["+(info.magic[i].minRare==0?info.baseR:info.magic[i].minRare)+"|"+info.magic[i].level+"]",7)
				+" "+info.magic[i].jpName+"|"+info.magic[i].effect+" MP:"+info.magic[i].MP;
			}
		}
		if(!(info.special==null)){
			s+="\n\nSpecial [Rarity unlocked|Level unlocked]";
			for(int i=0;i<info.special.length;i++){
				s+="\n"+Lib.pad("["+(info.special[i].minRare==0?info.baseR:info.special[i].minRare)+"|"+info.special[i].level+"]",7)
				+" "+info.special[i].jpName+"|"+info.special[i].effect+" MP:"+info.special[i].MP;
			}
		}
		s+="```";
		Lib.sendMessage(event, s);
		
	}
	
	@Override
	public void onePossible(RedditOverview Ounit, MessageReceivedEvent event) throws IOException {
		sendAbilities(new RedditUnit(Ounit.getData(0).unitUrl),event);
		
	}

	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		onePossible(Ounit,event);
		
	}

	@Override
	public void manyPossible(RedditOverview Ounit, int selection, MessageReceivedEvent event) throws IOException {
		sendAbilities(new RedditUnit(Ounit.getData(selection).unitUrl),event);
		
	}

	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		manyPossible(Ounit,selection,event);
		
	}
	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
