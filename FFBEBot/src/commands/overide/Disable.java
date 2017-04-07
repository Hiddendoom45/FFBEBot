package commands.overide;

import java.util.ArrayList;
import java.util.HashMap;

import global.Main;
import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;

public class Disable implements OverrideCommand,Selection{


	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Log.log("System", "Bot attempted to be shut down by "+event.getAuthor().getName()+" on "+event.getGuild());
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Select s=new Select(new ArrayList<String>(), Settings.ID, this, Settings.ID+"");
		Selector.setSelection(s, event);
	}
	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "Shuts down the bot if it's malfunctioning");
	}
	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		if(chosen.selectedText.equals(""+Settings.ID)){
			Main.quit();
		}
	}

	@Override
	public int getInputType() {
		return Selector.NullType;
	}

}
