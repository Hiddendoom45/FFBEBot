package util.multi;

import java.time.OffsetDateTime;
import java.util.Formatter;
import java.util.List;

import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.IMentionable;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.MessageType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

/**
 * Message wrapper for something that spans multiple messages. Follows the
 * following basic properties
 * <ul>
 * <li>If it is a singleton value that cannot be joined it will return the value
 * of the last message sent</li>
 * <li>If it is something that can be joined it will join the values from the
 * first to the last message</li>
 * <li>Clear/reset actions will generally affect all messages</li>
 * <li>Set actions will only affect last message unless it is something that
 * should encompass the entire message i.e. pins</li>
 * </ul>
 * 
 * @author Allen
 *
 */
public class MultiMessage implements Message{
	private Message[] messages;

	public MultiMessage(Message... messages){
		this.messages = messages;
	}

	@Override
	public long getIdLong(){
		return messages[messages.length-1].getIdLong();
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision){
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getMentionedUsers(){
		List<User> users = null;
		for(Message m:messages){
			if(users==null){
				users = m.getMentionedUsers();
			}
			else{
				users.addAll(m.getMentionedUsers());
			}
		}
		return users;
	}

	@Override
	public List<TextChannel> getMentionedChannels(){
		List<TextChannel> channels = null;
		for(Message m:messages){
			if(channels==null){
				channels = m.getMentionedChannels();
			}
			else{
				channels.addAll(m.getMentionedChannels());
			}
		}
		return channels;
	}

	@Override
	public List<Role> getMentionedRoles(){
		List<Role> roles = null;
		for(Message m:messages){
			if(roles==null){
				roles = m.getMentionedRoles();
			}
			else{
				roles.addAll(m.getMentionedRoles());
			}
		}
		return roles;
	}

	@Override
	public List<Member> getMentionedMembers(Guild guild){
		List<Member> members = null;
		for(Message m:messages){
			if(members==null){
				members = m.getMentionedMembers(guild);
			}
			else{
				members.addAll(m.getMentionedMembers(guild));
			}
		}
		return members;
	}

	@Override
	public List<Member> getMentionedMembers(){
		List<Member> members = null;
		for(Message m:messages){
			if(members==null){
				members = m.getMentionedMembers();
			}
			else{
				members.addAll(m.getMentionedMembers());
			}
		}
		return members;
	}

	@Override
	public List<IMentionable> getMentions(MentionType... types){
		List<IMentionable> mentions = null;
		for(Message m:messages){
			if(mentions==null){
				mentions = m.getMentions(types);
			}
			else{
				mentions.addAll(m.getMentions(types));
			}
		}
		return mentions;
	}

	@Override
	public boolean isMentioned(IMentionable mentionable, MentionType... types){
		boolean isMentioned = false;
		for(Message m:messages){
			if(m.isMentioned(mentionable, types)){
				isMentioned = true;
				break;
			}
		}
		return isMentioned;
	}

	@Override
	public boolean mentionsEveryone(){
		boolean mentionsEveryone = false;
		for(Message m:messages){
			if(m.mentionsEveryone()){
				mentionsEveryone = true;
				break;
			}
		}
		return mentionsEveryone;
	}

	@Override
	public boolean isEdited(){
		return messages[messages.length-1].isEdited();
	}

	@Override
	public OffsetDateTime getEditedTime(){
		return messages[messages.length-1].getEditedTime();
	}

	@Override
	public User getAuthor(){
		return messages[messages.length-1].getAuthor();
	}

	@Override
	public Member getMember(){
		return messages[messages.length-1].getMember();
	}

	@Override
	public String getJumpUrl(){
		return messages[0].getJumpUrl();
	}

	@Override
	public String getContentDisplay(){
		String s = "";
		for(Message m:messages){
			s += m.getContentDisplay()+"\n";
		}
		return s;
	}

	@Override
	public String getContentRaw(){
		String s = "";
		for(Message m:messages){
			s += m.getContentRaw()+"\n";
		}
		return s;
	}

	@Override
	public String getContentStripped(){
		String s = "";
		for(Message m:messages){
			s += m.getContentStripped()+"\n";
		}
		return s;
	}

	@Override
	public List<String> getInvites(){
		List<String> invites = null;
		for(Message m:messages){
			if(invites==null){
				invites = m.getInvites();
			}
			else{
				invites.addAll(m.getInvites());
			}
		}
		return invites;
	}

	@Override
	public String getNonce(){
		return messages[messages.length-1].getNonce();
	}

	@Override
	public boolean isFromType(ChannelType type){
		return messages[messages.length-1].isFromType(type);
	}

	@Override
	public ChannelType getChannelType(){
		return messages[messages.length-1].getChannelType();
	}

	@Override
	public boolean isWebhookMessage(){
		return messages[messages.length-1].isWebhookMessage();
	}

	@Override
	public MessageChannel getChannel(){
		return messages[messages.length-1].getChannel();
	}

	@Override
	public PrivateChannel getPrivateChannel(){
		return messages[messages.length-1].getPrivateChannel();
	}

	@Override
	public Group getGroup(){
		return messages[messages.length-1].getGroup();
	}

	@Override
	public TextChannel getTextChannel(){
		return messages[messages.length-1].getTextChannel();
	}

	@Override
	public Category getCategory(){
		return messages[messages.length-1].getCategory();
	}

	@Override
	public Guild getGuild(){
		return messages[messages.length-1].getGuild();
	}

	@Override
	public List<Attachment> getAttachments(){
		List<Attachment> attachments = null;
		for(Message m:messages){
			if(attachments==null){
				attachments = m.getAttachments();
			}
			else{
				attachments.addAll(m.getAttachments());
			}
		}
		return attachments;
	}

	@Override
	public List<MessageEmbed> getEmbeds(){
		List<MessageEmbed> embeds = null;
		for(Message m:messages){
			if(embeds==null){
				embeds = m.getEmbeds();
			}
			else{
				embeds.addAll(m.getEmbeds());
			}
		}
		return embeds;
	}

	@Override
	public List<Emote> getEmotes(){
		List<Emote> emotes = null;
		for(Message m:messages){
			if(emotes==null){
				emotes = m.getEmotes();
			}
			else{
				emotes.addAll(m.getEmotes());
			}
		}
		return emotes;
	}

	@Override
	public List<MessageReaction> getReactions(){
		List<MessageReaction> reactions = null;
		for(Message m:messages){
			if(reactions==null){
				reactions = m.getReactions();
			}
			else{
				reactions.addAll(m.getReactions());
			}
		}
		return reactions;
	}

	@Override
	public boolean isTTS(){
		return messages[messages.length-1].isTTS();
	}

	@Override
	public MessageAction editMessage(CharSequence newContent){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageAction editMessage(MessageEmbed newContent){
		throw new UnsupportedOperationException("Funciton is not implemented");
	}

	@Override
	public MessageAction editMessageFormat(String format, Object... args){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageAction editMessage(Message newContent){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditableRestAction<Void> delete(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JDA getJDA(){
		return messages[messages.length-1].getJDA();
	}

	@Override
	public boolean isPinned(){
		return messages[messages.length-1].isPinned();
	}

	@Override
	public RestAction<Void> pin(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestAction<Void> unpin(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestAction<Void> addReaction(Emote emote){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestAction<Void> addReaction(String unicode){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestAction<Void> clearReactions(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageType getType(){
		return messages[messages.length-1].getType();
	}

}
