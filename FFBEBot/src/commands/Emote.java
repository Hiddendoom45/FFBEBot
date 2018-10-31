package commands;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.EmoteData;
import util.Lib;

public class Emote extends CommandGenerics {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0){
			if(args[0].equals("list")){
				String s = Lib.emotes.stream().map(e -> e.getEmoteName()+"["+e.getID()+"]").collect(Collectors.joining(", "));
				Lib.sendMessage(event, s);
			}
			else{
				Predicate<EmoteData> f;
				if(Lib.isNumber(args[0])){
					f = e -> e.getID().equals(args[0]);
				}
				else{
					f = e -> e.getEmoteName().equalsIgnoreCase(args[0]);
				}
				Optional<EmoteData> e = Lib.emotes.stream().filter(f).findFirst();
				if(e.isPresent()){
					Lib.sendMessageEmoted(event, e.get().tag);
				}
				else{
					Lib.sendMessage(event, "emote not found");
				}
			}
		}
		else{
			help(event);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s = SaveSystem.getPrefix(event)+"emo [emote|id] or "+SaveSystem.getPrefix(event)+"emo list\n"
				+ "\tPost emote to channel\n"
				+ "\t[emote|id] the name of the emote or the id"
				+ "\tlist lists all emotes and their IDs";
		Lib.sendMessage(event, s);
	}

}
