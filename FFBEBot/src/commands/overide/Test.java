package commands.overide;

import java.util.HashMap;
import java.util.List;

import global.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * to test something easily
 * @author Allen
 *
 */
public class Test extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		List<Permission>  permissions=event.getGuild().getMemberById(Main.jda.getSelfUser().getId()).getPermissions((Channel)event.getChannel());
		System.out.println(permissions);
		String s=""+permissions.get(Integer.parseInt(args.get("n")[0])).getOffset();
		Lib.sendMessage(event, s);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
