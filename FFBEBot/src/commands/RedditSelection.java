package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.unit.RedditUnit;
import util.unit.UnitInfo;
import util.unit.RedditOverview;

public abstract class RedditSelection implements Command, Selection {

	private HashMap<Long,RedditOverview> saved=new HashMap<Long,RedditOverview>();
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
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
				RedditOverview Ounits=new RedditOverview(args[0]);//get all units matching name
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
						onePossible(Ounits,event);
					}
					else{
						
						UnitInfo info=new UnitInfo(Ounits.getData(0).unitUrl);
						int rare=Integer.parseInt(args[1]);
						if(rare<=info.maxRarity&&rare>=info.minRarity){
							onePossible(Ounits,rare,event);
						}
						else{
							onePossible(Ounits,event);
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
	public abstract void onePossible(RedditOverview Ounit,MessageReceivedEvent event) throws IOException;
	public abstract void onePossible(RedditOverview Ounit, int rarity,MessageReceivedEvent event) throws IOException;
	public abstract void manyPossible(RedditOverview Ounit, int selection,MessageReceivedEvent event) throws IOException;
	public abstract void manyPossible(RedditOverview Ounit, int selection, int rarity,MessageReceivedEvent event) throws IOException;
	@Override
	public abstract void help(MessageReceivedEvent event);

	@Override
	public abstract void executed(boolean sucess, MessageReceivedEvent event);

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		try{
			if(chosen.additionalData[0].equals("null")){
				manyPossible(saved.get(chosen.ID),chosen.selected,event);
			}
			else{
				RedditUnit info=new RedditUnit(saved.get(chosen.ID).getData(chosen.selected).unitUrl);
				int rare=Integer.parseInt(chosen.additionalData[0]);
				if(rare<=info.maxR&&rare>=info.baseR){
					manyPossible(saved.get(chosen.ID),chosen.selected,rare,event);
				}
				else{
					manyPossible(saved.get(chosen.ID),chosen.selected,event);
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
