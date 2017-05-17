package commands;

import java.io.IOException;

import global.CommandParser.CommandContainer;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import reactions.ToggleReaction;
import util.CmdControl;
import util.Lib;
import util.ReactionController;
import util.unit.RedditOverview;
import util.unit.RedditUnit;

public class RSkill extends RedditSelection{
	
	private void sendAbilities(RedditUnit info, MessageReceivedEvent event){
		String en=getMsgString(info,true);
		String jp=getMsgString(info,false);
		String messageString=jp;//whether to use english or JP version for inital print
		CommandContainer cmd=CmdControl.parser.parse(event.getMessage().getContent(), event);
		if(cmd.args.length>1?cmd.args[1].contains("en"):false){//based on secound arg, for purpose of reddit discord channel
			messageString=en;
		}
		Message msg=Lib.sendMessage(event,messageString);
		try{
		msg.addReaction("🇺🇸").queue();
		msg.addReaction("🇯🇵").queue();
		SkillReact react=new SkillReact();
		react.addPanel("🇺🇸", en);
		react.addPanel("🇯🇵", jp);
		ReactionController.addReaction(msg, react);
		}catch(net.dv8tion.jda.core.exceptions.PermissionException e){
			Log.log("REACTERR", "Bot does not have permission to react on "+event.getChannel()+(!event.isFromType(ChannelType.PRIVATE)?" on "+event.getGuild():""));
		}
		catch(NullPointerException e1){}
	}
	private String getMsgString(RedditUnit info, boolean english){
		String s=":pencil: Skills for:"+info.title;
		s+="\n";
		String name;
		boolean NLTrigger=false;//trigger to determine writing newlines between 
		if(info.magic.length>0){
			NLTrigger=true;
			s+="__Magic__: [Rarity unlocked|Level unlocked] Name|Effect";
			for(int i=0;i<info.magic.length;i++){
				if(!info.magic[i].enName.equals("--")&&english){
					name=info.magic[i].enName;
				}
				else{
					name=info.magic[i].jpName;
				}
				s+="\n"+Lib.pad("[**"+(info.magic[i].minRare==0?info.baseR:info.magic[i].minRare)+"|"+info.magic[i].level+"**]",7)
				+" *"+name+"* | "+info.magic[i].effect+" MP:**"+info.magic[i].MP+"**";
			}
		}
		if(info.special.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Special__: [Rarity unlocked|Level unlocked] Name|Effect";
			for(int i=0;i<info.special.length;i++){
				if(!info.special[i].enName.equals("--")&&english){
					name=info.special[i].enName;
				}
				else{
					name=info.special[i].jpName;
				}
				s+="\n"+Lib.pad("[**"+(info.special[i].minRare==0?info.baseR:info.special[i].minRare)+"|"+info.special[i].level+"**]",7)
				+" *"+name+"* | "+info.special[i].effect+" MP:**"+info.special[i].MP+"**";
			}
		}
		if(info.enhance.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Enhancements__:[Cost]";
			for(int i=0;i<info.enhance.length;i++){
				if(!info.enhance[i].enName.equals("--")&&english){
					name=info.enhance[i].enName;
				}
				else{
					name=info.enhance[i].jpName;
				}
				s+="\n"+Lib.pad("[**"+info.enhance[i].gil+"**]", 15)
				+"*"+info.enhance[i].jpName+"*|"+info.enhance[i].effect+" MP:**"+info.enhance[i].MP+"**";
			}
		}
		if(info.relatedSkills.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Related Skills__:[Category]";
			for(int i=0;i<info.relatedSkills.length;i++){
				if(!info.relatedSkills[i].enName.equals("--")&&english){
					name=info.relatedSkills[i].enName;
				}
				else{
					name=info.relatedSkills[i].jpName;
				}
				s+="\n"+Lib.pad("[**"+info.relatedSkills[i].category+"**]", 12)
				+"*"+info.relatedSkills[i].jpName+"*|"+info.relatedSkills[i].effect+" MP:**"+info.relatedSkills[i].MP+"**";
			}
		}
		if(info.sparks.length>0){
			if(NLTrigger){
				s+="\n\n";
			}
			else NLTrigger=true;
			s+="__Sparks__:[%S2|%S3]";
			for(int i=0;i<info.sparks.length;i++){
				s+="\n"+Lib.pad("[**"+info.sparks[i].S2+"|"+info.sparks[i].S3+"**]",7)
				+"*"+info.sparks[i].name+"*|"+info.sparks[i].effect+"MP:**"+info.sparks[i].MP+"**";
			}
		}
		s+="";
		return s;
	}
	@Override
	public void onePossible(RedditOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendAbilities(SaveSystem.getRedditUnit(Ounit.getData(0).name),event);
		
	}
	@Override
	public void manyPossible(RedditOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendAbilities(SaveSystem.getRedditUnit(Ounit.getData(selection).name),event);
		
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"rskill [unitname]\n"
				+ "\tGets the skills for unit\n"
				+ "\t[unitname] unit to get the skills for(doesn't have to be the full name)"
				+ "\t[language] `en` if you want the bot to use the translated names for skills(if available)";
		Lib.sendMessage(event, s);
		
	}
	private class SkillReact extends ToggleReaction{
		public void addPanel(String emote, String panel){
			panels.put(emote, new MessageBuilder().append(panel).build());
		}
		@Override
		public Long getTimeout() {
			return 0L;	
		}

	}
}
