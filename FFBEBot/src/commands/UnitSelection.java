package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
/**
 * Command used for the selection of a specific unit to find information about the unit
 * @author Allen
 *
 */
public abstract class UnitSelection extends CommandGenerics implements Command, Selection {
	//used to keep track of current selections going on
	private HashMap<Long,UnitOverview> saved=new HashMap<Long,UnitOverview>();
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length==0){//default message returns the help message
			help(event);
		}
		else{
			int rarity=0;
			String name="";
			//extract name, including spaces, numbers=rarity
			for(int i=0;i<args.length;i++){
				if(i==(args.length-1)&&Lib.isNumber(args[i])){
					rarity=Integer.parseInt(args[i]);
				}
				else{
					if(!name.equals("")){
						name+=" ";
					}
					name+=args[i];
				}
			}
			try{
				UnitOverview Ounits=new UnitOverview(name);//get all units matching name
				ArrayList<String> possible=Ounits.getNames();//get the array of those uits
				if(possible.size()>1){//if more than 1 possible unit
					long ID=System.currentTimeMillis();//assuming that no 2 commands will occur simultaneously
					saved.put(ID, Ounits);//keep track of the Ounits used to find all the units(essentially the options)
					Select select=null;
					try{
					select=new Select(possible, ID, this, possible);//fin
					}catch(IllegalStateException e){
						Lib.sendMessage(event, "Too many units found, please be more specific with the unit name");
						Log.log("CMDERROR", "msg over 2k");
						return;
					}
					select.additionalData=new String[]{(rarity!=0?""+rarity:"null")};
					Selector.setSelection(select, event);
				}
				else if(possible.size()>0){//if only 1 possible unit, send data, as appropriate
					if(rarity==0){
						onePossible(Ounits,0,event);
					}
					else{
						UnitInfo info=new UnitInfo(Ounits.getData(0).unitUrl);
						int rare=rarity;
						if(rare<=info.maxRarity&&rare>=info.minRarity){
							onePossible(Ounits,rare,event);
						}
						else{
							onePossible(Ounits,0,event);
						}
					}
				}
				else{
					Lib.sendMessage(event, "Unit not found");
				}
			}
			catch(Exception e){
				Log.logError(e);
				Lib.sendMessage(event, "ERROR occured, likely future unit selected");
			};

		}
	}
	public abstract void onePossible(UnitOverview Ounit, int rarity,MessageReceivedEvent event) throws IOException;
	public abstract void manyPossible(UnitOverview Ounit, int selection, int rarity,MessageReceivedEvent event) throws IOException;
	@Override
	public abstract void help(MessageReceivedEvent event);
	
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		try{
			if(chosen.additionalData[0].equals("null")){
				manyPossible(saved.get(chosen.ID),chosen.selected,0,event);
			}
			else{
				UnitInfo info=new UnitInfo(saved.get(chosen.ID).getData(chosen.selected).unitUrl);
				int rare=Integer.parseInt(chosen.additionalData[0]);
				if(rare<=info.maxRarity&&rare>=info.minRarity){
					manyPossible(saved.get(chosen.ID),chosen.selected,rare,event);
				}
				else{
					manyPossible(saved.get(chosen.ID),chosen.selected,0,event);
				}
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
