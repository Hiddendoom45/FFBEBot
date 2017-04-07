package util;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * interface implemented by commands that need slection
 * @author Allen
 *
 */
public interface Selection {
	/**
	 * Called when a selection is chosen
	 * @param chosen
	 * @param event
	 */
	public void selectionChosen(Select chosen,MessageReceivedEvent event);
	/**
	 * what listing system to use for user selection 99% number based but here for versatility
	 * @param inputType 
	 * @return
	 */
	public int getInputType();
}
