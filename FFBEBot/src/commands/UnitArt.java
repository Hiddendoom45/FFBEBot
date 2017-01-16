package commands;

import java.io.IOException;

import global.Main;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Selection;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class UnitArt extends UnitSelection implements Command,Selection  {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", event.getAuthorName()+" asked for unit art of "+(args.length>0?args[0]:"")+(event.isPrivate()?" on "+event.getGuild():""));
		event.getChannel().sendTyping();
		return super.called(args, event);
	}
	public static void sendImage(UnitOverview Ounits, int rarity,MessageReceivedEvent event,int index){
		if(rarity==0){
			Lib.sendMessage(event, ":art:"+Ounits.getNames().get(index)+"\n"+Ounits.getData(index).imgUrl);
		}
		else{
			UnitInfo info;
			try {
				info = new UnitInfo(Ounits.getData(index).unitUrl);
				Lib.sendMessage(event, (rarity<=info.maxRarity&&rarity>=info.minRarity?":art:"+info.unitName+"\n"+info.sprites[rarity-info.minRarity]:"Unit does not have this rarity"));
			} catch (IOException e) {
				Log.logError(e);
			}
			
		}
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String msg=SaveSystem.getPrefix(event)+"unitart [unitname] [unitrarity]\n"
				+ "\tDisplay image of unit\n"
				+ "\tIf rarity specified will display specific rarity, otherwise max rarity";
		Lib.sendMessage(event, msg);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendImage(Ounit,rarity,event,0);
		
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendImage(Ounit,rarity,event,selection);
		
	}

}
