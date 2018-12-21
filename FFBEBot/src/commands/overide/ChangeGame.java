package commands.overide;

import java.util.HashMap;

import global.Main;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class ChangeGame extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("override")){//shoehorned in override to set the game to whatever
			Main.jda.getPresence().setGame(Game.of(GameType.DEFAULT, Lib.extract(args.get("override"))));
			return;
		}
		for(Main.states s:Main.states.values()){//if one of the states is in the arguments, change it to that
			if(args.containsKey(s.toString())){
				Main.setGame(s);
				System.out.println("state set to"+s);
				return;
			}
		}
		Main.setGame(Main.states.randomReady());
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "-[state] | -override [game]");
	}

}
