package util;

import java.util.HashMap;

import global.ArgumentParser;
import global.Main;
import global.record.SaveSystem;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Overrider {
	private static final HashMap<Integer,Long> overrides=new HashMap<Integer,Long>();
	public static boolean parseOverride(MessageReceivedEvent event){
		if(event.getMessage().isMentioned(event.getJDA().getSelfInfo())&&!(isOverride(event.getMessage().getContent())==null)){
			overrides.put(key(event), System.currentTimeMillis());
			SaveSystem.removeOverride(isOverride(event.getMessage().getContent()));
			Lib.sendMessage(event, "Override command activated for "+event.getAuthorName()+" 5 minutes");
			return true;
		}
		else if(overrides.containsKey(key(event))){
			System.out.println("override trigger");
			if((overrides.get(key(event)))<System.currentTimeMillis()-300000||event.getMessage().getContent().equals("exit")){
				overrides.remove(key(event));
			}
			else{
				return Main.handleOverride(ArgumentParser.handleArguments(event.getMessage().getContent()),event);
			}
			return true;
		}
		else if(event.getAuthor().getId().equals("206193542693912578")){
			return Main.handleOverride(ArgumentParser.handleArguments(event.getMessage().getContent()),event);
		}
		return false;
	}
	private static int key(MessageReceivedEvent e){
		return (""+e.getAuthor()+e.getChannel()).hashCode();
	}
	private static String isOverride(String o){
		String[] overrides=SaveSystem.getOverrides();
		for(String s:overrides){
			if(o.contains("override "+s)){
				return s;
			}
		}
		return null;
	}
}
