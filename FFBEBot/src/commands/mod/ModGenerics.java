package commands.mod;

import commands.Command;
import commands.CommandGenerics;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * placeholder template
 * @author Allen
 *
 */
public abstract class ModGenerics extends CommandGenerics implements Command {

	@Override
	public abstract void action(String[] args, MessageReceivedEvent event);

	@Override
	public abstract void help(MessageReceivedEvent event);

}
