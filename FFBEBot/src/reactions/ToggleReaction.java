package reactions;

import java.util.HashMap;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

/**
 * Generic class to handle reactions which toggles what's displayed in the message content
 * @author Allen
 *
 */
public abstract class ToggleReaction extends ReactionGenerics implements Reaction{
	protected HashMap<String,Message> panels=new HashMap<String,Message>();//panels of message content 

	@Override
	public Message action(MessageReactionAddEvent event, ReactionEmote react, Message msg) {
		if(panels.containsKey(react.getName())){
			return msg.editMessage(panels.get(react.getName())).complete();
		}
		return msg;
	}
	@Override
	public abstract Long getTimeout();
}
