package reactions;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

/**
 * Interface to deal with reaction responses must be implemented for a message to respond to reactions
 * @author Allen
 *
 */
public interface Reaction {
	/**
	 * Called to determine if it's to be executed 
	 */
	public boolean called(MessageReactionAddEvent event, ReactionEmote react);
	/**
	 * main bulk of reaction, what to do in response
	 * @return new updated message if it's updated, else the message parameter passed
	 */
	public Message action(MessageReactionAddEvent event, ReactionEmote react,Message msg);
	/**
	 * Supposed to be for cleanup but instead typically used to log stuffs
	 */
	public void executed(MessageReactionAddEvent event);
	/**
	 * time before reactions for the message will no longer be registered
	 * @return in milliseconds
	 */
	public Long getTimeout();//timeout for reactions 0 denotes that it's persistent as long as bot is up
}
