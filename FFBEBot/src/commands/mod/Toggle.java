package commands.mod;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import commands.Command;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Select;
import util.Selection;
import util.Selector;
import util.Lib;

public class Toggle implements Command, Selection {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Select sel=new Select(getNames(event.getGuild().getId()), 0, this, getNames(event.getGuild().getId()),"Options to Toggle:[Current setting]:");
		Selector.setSelection(sel, event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"toggle"
				+ "\n\tgenerates a list of server options you can toggle";
		Lib.sendMessage(event, s);
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		Settings guild=Settings.guilds.get(event.getGuild().getId());
		switch(chosen.selected){
		case 0:
			guild.tJoinMsg=!guild.tJoinMsg;
			Lib.sendMessage(event, "Join message enabled set to "+guild.tJoinMsg);
			break;
		case 1:
			guild.tJoinPM=!guild.tJoinPM;
			Lib.sendMessage(event, "Join PM enabled set to "+guild.tJoinPM);
			break;
		default:break;
		}
		SaveSystem.setSetting(guild);
		try {
			TimeUnit.SECONDS.sleep(1);
			SaveSystem.loadGuilds();
		} catch (InterruptedException e) {Log.logError(e);}
	}
	private ArrayList<String> getNames(String id){
		ArrayList<String> names=new ArrayList<String>();
		names.add("join message enabled?["+SaveSystem.getGuild(id).tJoinMsg+"]");
		names.add("join personal message enabled?["+SaveSystem.getGuild(id).tJoinPM+"]");
		return names;
	}
	@Override
	public int getInputType() {
		return 0;
	}

}
