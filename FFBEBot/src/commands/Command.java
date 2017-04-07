package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Generic class for all commands
 * @author Allen
 *
 */
public interface Command {
	/**
	 * Called first for a command
	 * @param args	arguments for command
	 * @param event  event which command was evoked in
	 * @return whether to do command or not
	 */
	public boolean called(String[] args,MessageReceivedEvent event);
	/**
	 * Action/execution of command
	 * @param args arguments for command
	 * @param event event which command was evoked in
	 */
	public void action(String[] args, MessageReceivedEvent event);
	/**
	 * Called when command is evoked by itself prints help statment
	 * @param event event which command was evoked in
	 */
	public void help(MessageReceivedEvent event);
	/**
	 * Called after command is executed
	 * @param sucess whether {@link #called(String[], MessageReceivedEvent)} returned true or false
	 * @param event event which command was evoked in
	 */
	public void executed(boolean sucess, MessageReceivedEvent event);
	
}
