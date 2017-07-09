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

public class UnitAwaken extends UnitPaging{

	public UnitAwaken() {
		super(UnitPaging.headDefault);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="unitawaken\n"
				+ "awakens the units selected to the next rarity if you have enough %sacredCrystals%"
				+ "\t%sacredCrystals% are used to awaken units, 3 from 3>4, 12 from 4>5, 20 from 5>6";
		Lib.sendMessageEmoted(event, s);
		
	}

	@Override
	public void unitsSelected(MessageReceivedEvent event, List<Integer> selectedIndexes) {
		Collections.sort(selectedIndexes);
		Collections.reverse(selectedIndexes);
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		String confirmation="Are you sure you want to awaken the following units using %sc% sacred crystals?(y/n)";
		int SC=0;
		for(int i=0;i<selectedIndexes.size();i++){
			PullUnit u=user.units.get(selectedIndexes.get(i));
			SC+=SCAmount(u.rarity);
			confirmation+="\n"+u.name+"("+u.rarity+"★)";
			if(!u.unit.hasRarity(u.rarity+1)){//quickfix for over awakening a unit
				String s="One or more of the units you've selected is already at max rarity, cancelling";
				Lib.sendMessage(event, s);
				return;
			}
		}
		if(user.SacredCrystal>=SC){
		confirmation=confirmation.replace("%sc%", ""+SC);
		
		//for yes/no
				Selection confirm=new Selection(){
					@Override
					public void selectionChosen(Select chosen, MessageReceivedEvent event) {
						if(chosen.selected==0){
							int SCBefore=user.SacredCrystal;
							for(int i=0;i<selectedIndexes.size();i++){
								UnitSpecific u=user.units.get(selectedIndexes.get(i));
								user.SacredCrystal-=SCAmount(u.base);
								user.units.get(selectedIndexes.get(i)).rarity++;
								Log.log("Unit", "Awakened Unit "+u.name+" "+u.rarity+" from "+event.getAuthor()+"'s inventory at index "+selectedIndexes.get(i));
							}
							Lib.sendMessageEmoted(event, "Awakened "+selectedIndexes.size()+" units for "+(SCBefore-user.SacredCrystal)+" %sacredCrystal%");
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
		else{
			Lib.sendMessageEmoted(event, "You do not have enough %sacredCrystal% to awaken those units");
		}
		
	}
	private int SCAmount(int rarity){
		if(rarity==3){
			return 3;
		}
		else if(rarity==4){
			return 12;
		}
		else if(rarity ==5){
			return 20;
		}
		return 0;
	}

}
