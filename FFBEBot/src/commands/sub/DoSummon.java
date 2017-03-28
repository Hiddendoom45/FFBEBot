package commands.sub;

import java.util.ArrayList;
import java.util.HashMap;

import Library.summon.banner.Banner;
import global.Main;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Select;
import util.Selection;
import util.Selector;
import util.rng.summon.SummonTypes;
/**
 * utility class to simplify doing any of the three summon methods
 * @author Allen
 *
 */
public class DoSummon implements Selection{
	HashMap<Long,SummonData> save=new HashMap<Long,SummonData>();
	public DoSummon(MessageReceivedEvent event,Banner banner,SummonTypes type){
		if(type==SummonTypes.Elevenpull){
			Main.commands.get(type.name).action(new String[]{banner.toString()}, event);
			return;
		}
		Select select=new Select(new ArrayList<String>(),Settings.ID,this,new ArrayList<String>(),"How many summons do you want to do?");
		Selector.setSelection(select, event);
		save.put(select.ID,new SummonData(type,banner));
	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		SummonTypes type=save.get(chosen.ID).type;
		Main.commands.get(type.name).action(
				new String[]{chosen.selectedText,save.get(chosen.ID).banner.toString()},
				event);
		save.remove(chosen.ID);
	}

	@Override
	public int getInputType() {
		return 2;//null type, reason why I had to create this class
	}
	private class SummonData{
		SummonTypes type;
		Banner banner;
		SummonData(SummonTypes type,Banner banner){
			this.type=type;
			this.banner=banner;
		}
	}
}
