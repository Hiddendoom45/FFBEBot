package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RAwaken extends RedditSelection {
	private void sendAwakening(RedditUnit info,int rarity,MessageReceivedEvent event){
		String s=":pencil:Awakening mats for "+info.title+"\n";
		if(rarity==0){
			for(int i=0;i<info.awakening.length;i++){
				s+=info.awakening[i]+"\n";
			}
		}
		else{
			s+=info.awakening[rarity-info.baseR];
		}
		Lib.sendMessage(event, s);
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAwakening(SaveSystem.getRedditUnit(Ounit.getData(0).name),rarity,event);

	}

	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendAwakening(SaveSystem.getRedditUnit(Ounit.getData(selection).name),rarity,event);

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"rawaken [unitname] [rarity]\n"
				+ "\tgets the awakening mats for a unit\n"
				+ "\t[unitname] unit to get the awakening mats for(doesn't have to be the full name)\n"
				+ "\t[rarity] mats needed to awaken to from [rarity] to next rarity";
		Lib.sendMessage(event, s);

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
