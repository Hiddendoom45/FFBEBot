package commands;

import java.io.IOException;

import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
import util.Lib;

public class Awaken extends UnitSelection {
	private void sendAwakening(UnitInfo info,int rarity,MessageReceivedEvent event){
		String s=":pencil:Awakening mats for "+info.unitName+"\n";
		if(rarity==0){
			for(int i=0;i<info.awakening.length;i++){
				s+=info.awakening[i]+"\n";
			}
		}
		else{
			s+=info.awakening[rarity-info.minRarity];
		}
		Lib.sendMessage(event, s);
	}

	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAwakening(new UnitInfo(Ounit.getData(0).unitUrl),rarity,event);
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendAwakening(new UnitInfo(Ounit.getData(selection).unitUrl),rarity,event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"awaken [unit] [rarity]\n"
				+ "gets the awaken! mats for a unit\n"
				+ "if specified the mats needed to awaken to next rarity at [rarity]";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
