package commands;

import java.util.ArrayList;
import java.util.HashMap;

import global.Main;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class UnitArt implements Command,Selection {
	private HashMap<Long,UnitOverview> saved=new HashMap<Long,UnitOverview>();
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Main.log("status", event.getAuthorName()+" asked for unit art of "+(args.length>0?args[0]:"")+(event.isPrivate()?" on "+event.getGuild():""));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length==0){//default message returns the help message
			help(event);
		}
		else{
			try{
				UnitOverview Ounits=new UnitOverview(args[0]);//get all units matching name
				ArrayList<String> possible=Ounits.getNames();//get the array of those uits
				if(possible.size()>1){
					long ID=System.currentTimeMillis();//assuming that no 2 commands will occur simultaneously
					saved.put(ID, Ounits);//keep track of the Ounits used to find all the units(essentially the options)
					Select select=new Select(possible, ID, this, possible);//fin
					select.additionalData=new String[]{(args.length>1?args[1]:"null")};
					Selector.setSelection(select, event);
				}
				else if(possible.size()>0){
					if(args.length==1||!Lib.isNumber(args[1])){
						Lib.sendMessage(event, ":art:"+Ounits.getNames().get(0)+"\n"+Ounits.getData(0).imgUrl);
					}
					else{
						UnitInfo info=new UnitInfo(Ounits.getData(0).unitUrl);
						int rare=Integer.parseInt(args[1]);
						System.out.println(info.sprites[0]);
						Lib.sendMessage(event, (rare<=info.maxRarity&&rare>=info.minRarity?":art:"+info.unitName+"\n"+info.sprites[rare-info.minRarity]:"Unit does not have this rarity"));
					}
				}
				else{
					Lib.sendMessage(event, "Unit not found");
				}
			}
			catch(Exception e){
				Log.logError(e);
			};

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
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		try{
		if(chosen.additionalData[0].equals("null")){
			Lib.sendMessage(event, ":art:"+chosen.names.get(chosen.selected)+"\n"+saved.get(chosen.ID).getData(chosen.selected).imgUrl);
		}
		else{
			UnitInfo info=new UnitInfo(saved.get(chosen.ID).getData(chosen.selected).unitUrl);
			int rare=Integer.parseInt(chosen.additionalData[0]);
			Lib.sendMessage(event, (rare<=info.maxRarity&&rare>=info.minRarity?":art:"+info.unitName+"\n"+info.sprites[rare-info.minRarity]:"Unit does not have this rarity"));
		}
		saved.remove(chosen.ID);
		}
		catch(Exception e){
			Log.logError(e);
			Lib.sendMessage(event, "Error occured, likely cause url link is incorrect");
		}
	}

	@Override
	public int getInputType() {
		return 0;
	}

}
