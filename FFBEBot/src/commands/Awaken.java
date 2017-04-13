package commands;

import java.io.IOException;

import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
import util.Lib;

public class Awaken extends UnitSelection {
	private void sendAwakening(UnitInfo info,int rarity,MessageReceivedEvent event){
		String s=":pencil:Awakening mats for "+info.unitName+"\n";
		if(rarity==0){
			for(int i=0;i<info.awakening.length;i++){
				s+=(info.minRarity+i)+":star::";
				s+=info.awakening[i]+"\n";
			}
		}
		else{
			s+=(rarity)+":star::";
			s+=info.awakening[rarity-info.minRarity];
		}
		Lib.sendMessage(event, s);
	}
	@Override 
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("Status", "Awakening mats "+(args.length>0?"for "+Lib.extract(args):"")+" by "+event.getAuthor().getName()+(event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild()));
		return super.called(args, event);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAwakening(SaveSystem.getExviusUnit(Ounit.getData(0).name),rarity,event);
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendAwakening(SaveSystem.getExviusUnit(Ounit.getData(selection).name),rarity,event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"awaken [unitname] [rarity]\n"
				+ "\tgets the awakening mats for a unit\n"
				+ "\t[unitname] unit to get the awakening mats for(doesn't have to be the full name)\n"
				+ "\t[rarity] mats needed to awaken to from [rarity] to next rarity";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
