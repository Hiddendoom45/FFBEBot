package commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Library.pulls.PullUnit;
import Library.summon.UnitSpecific;
import global.record.Data;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;

public class UnitSell extends UnitPaging implements Command {

	public UnitSell() {
		super(UnitPaging.headDefault);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="unitsell\n"
				+ "\tSells the units selected\n"
				+ "\tbase rarity determines # of sacred crystals given 3★=2,4★=5,5★=25\n"
				+ "\tsacred crystals are used to awaken units, e from 3>4, 12 from 4>5, 20 from 5>6";
		Lib.sendMessage(event, s);
	}

	@Override
	public void unitsSelected(MessageReceivedEvent event, List<Integer> selectedIndexes) {
		Collections.sort(selectedIndexes);
		Collections.reverse(selectedIndexes);
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		String confirmation="Are you sure you want to sell the following units for %sc% sacred crystals?(y/n)";
		int SC=0;
		for(int i=0;i<selectedIndexes.size();i++){
			PullUnit u=user.units.get(selectedIndexes.get(i));
			SC+=SCAmount(u.base);
			confirmation+="\n"+u.name+"("+u.rarity+"★)";
		}
		confirmation=confirmation.replace("%sc%",""+SC);
		//for yes/no
		Selection confirm=new Selection(){
			@Override
			public void selectionChosen(Select chosen, MessageReceivedEvent event) {
				Data user=SaveSystem.getUser(event.getAuthor().getId());
				if(chosen.selected==0){
					int SCBefore=user.SacredCrystal;
					for(int i=0;i<selectedIndexes.size();i++){
						int c=selectedIndexes.get(i);
						UnitSpecific u=user.units.get(c);
						user.SacredCrystal+=SCAmount(u.base);
						Log.log("Unit", "Removed Unit "+u.name+" "+u.rarity+" from "+event.getAuthor()+"'s inventory at index "+c);
						user.units.remove(c);
					}
					Lib.sendMessageEmoted(event, "Sold "+selectedIndexes.size()+" units for "+(user.SacredCrystal-SCBefore)+" %sacredCrystal%");
					SaveSystem.setUser(user);
				}
			}
			
			@Override
			public int getInputType() {
				return Selector.YNType;
			}
		};
		
		Select select=new Select(new ArrayList<String>(), System.currentTimeMillis(), confirm, confirmation);
		Selector.setSelection(select, event);
	}
	private int SCAmount(int rarity){
		if(rarity==3){
			return 2;
		}
		else if(rarity==4){
			return 5;
		}
		else if(rarity ==5){
			return 25;
		}
		else if(rarity==6){
			return 50;
		}
		return 0;
	}

}
