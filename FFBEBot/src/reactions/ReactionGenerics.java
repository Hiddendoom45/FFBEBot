package reactions;

import java.util.HashSet;

import global.record.Log;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
/**
 * Generic class containing most basic stuff to make it easier and stuff and things
 * @author Allen
 *
 */
public abstract class ReactionGenerics implements Reaction{
	private HashSet<ReactionEmote> triggers=new HashSet<ReactionEmote>();
	@Override
	public boolean called(MessageReactionAddEvent event, ReactionEmote react) {
		return triggers.size()>0?triggers.contains(react):true;//if it's one of the reaction triggers, select to avoid not calling if no triggers added
	}
	@Override
	public abstract Message action(MessageReactionAddEvent event, ReactionEmote react, Message msg);

	@Override
	public void executed(MessageReactionAddEvent event) {
		Log.log("REACT", "Reaction "+event.getReaction().getEmote().getName()+" by "+event.getUser().getName()+" on Message "+event.getMessageId());
	}
	protected void addTrigger(ReactionEmote trigger){
		triggers.add(trigger);
	}
	protected HashSet<ReactionEmote> getTriggers(){
		return triggers;
	}
	public abstract Long getTimeout();
}
