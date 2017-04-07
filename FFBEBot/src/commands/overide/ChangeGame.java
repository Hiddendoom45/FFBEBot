package commands.overide;

import java.util.HashMap;

import global.Main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ChangeGame extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
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

	}

}
