package commands.overide;

import java.util.HashMap;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class ClearLog extends OverrideGenerics implements OverrideCommand{
	@Override
	public boolean called(java.util.HashMap<String,String[]> args, MessageReceivedEvent event) {
		return ownerValidate(event);
	}
	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
