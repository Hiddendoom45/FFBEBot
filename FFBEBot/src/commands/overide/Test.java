package commands.overide;

import java.util.HashMap;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
/**
 * to test something easily
 * @author Allen
 *
 */
public class Test extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		System.out.println(Data.parseDataToElements());

	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
