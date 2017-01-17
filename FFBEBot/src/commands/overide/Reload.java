package commands.overide;

import java.util.HashMap;

import global.Main;
import global.record.SaveSystem;
import util.Lib;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Reload implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("r")){
			Main.jda.getAccountManager().setGame("the loading game...");
			SaveSystem.preloadReddit();
		}
		if(args.containsKey("e")){
			Main.jda.getAccountManager().setGame("the loading game...");
			SaveSystem.preloadExvicus();
		}
		if(!args.containsKey("e")&&!args.containsKey("r")){
			Main.jda.getAccountManager().setGame("the loading game...");
			SaveSystem.preloadReddit();
			SaveSystem.preloadExvicus();
		}
		SaveSystem.writeData();
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		Lib.sendMessage(event, "Data reloaded");

	}

}
