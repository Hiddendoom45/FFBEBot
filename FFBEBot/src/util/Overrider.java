package util;

import java.util.HashMap;

import commands.overide.OverrideCommand;
import global.ArgumentParser;
import global.ArgumentParser.ArgContainer;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Class to handle override commands
 * @author Allen
 *
 */
public class Overrider {
	private static final HashMap<Integer,Long> overrides=new HashMap<Integer,Long>();//map of all the current override keysbeing used
	private static final HashMap<String,OverrideCommand> commands=new HashMap<String,OverrideCommand>();
	/**
	 * Attempts to parse command for an override command
	 * @param event message event
	 * @return if event is an override command event
	 */
	public static boolean parseOverride(MessageReceivedEvent event){
		//check if message is one that activates override
		if(event.getMessage().isMentioned(event.getJDA().getSelfUser())&&!(isOverride(event.getMessage().getContentRaw())==null)){
			overrides.put(key(event), System.currentTimeMillis());//put it in list
			SaveSystem.removeOverride(isOverride(event.getMessage().getContentRaw()));//remove the key so it can't be used again
			Lib.sendMessage(event, "Override command activated for "+event.getAuthor().getName()+" 5 minutes");//send message
			return true;
		}
		//if message is sent and override is activated
		else if(overrides.containsKey(key(event))){
			//checks if override is still active
			if((overrides.get(key(event)))<System.currentTimeMillis()-300000||event.getMessage().getContentRaw().equals("exit")){
				overrides.remove(key(event));//if not remove key
				return false;
			}
			else{//otherwise return based on if what's entered is an override command
				return handleOverride(ArgumentParser.handleArguments(event.getMessage().getContentRaw()),event);
			}
		}//if sender is bot owner cancel other commands only if override command
		else if(event.getAuthor().getId().equals(Settings.ownerID)){
			return handleOverride(ArgumentParser.handleArguments(event.getMessage().getContentRaw()),event);
		}
		return false;
	}
	public static void addOverrideCommand(String name, OverrideCommand command){
		commands.put(name.toLowerCase(), command);
	}
	private static boolean handleOverride(ArgContainer args,MessageReceivedEvent event){
		if(commands.containsKey(args.command.toLowerCase())){
			if(args.args.containsKey("help")){
				commands.get(args.command).help(event);
				return true;
			}
			boolean safe=commands.get(args.command).called(args.args, event);
			if(safe){
				commands.get(args.command).action(args.args, event);
			}
			commands.get(args.command).executed(safe, event);
			return true;
		}
		else if(args.command.equalsIgnoreCase("help")){
			if(args.args.containsKey("")&&commands.containsKey(args.args.get("")[0])){
				commands.get(args.args.get("")[0]).help(event);
			}
		}
		System.out.println(args.args);
		return false;
	}
	
	/**
	 * gets the key to track who and where has override
	 * @param e event to get the key for
	 * @return int hash representing the key for the channel and user
	 */
	private static int key(MessageReceivedEvent e){
		return (""+e.getAuthor()+e.getChannel()).hashCode();
	}
	/**
	 * checks if string is a possible override string
	 * @param o String to check
	 * @return override string if true, otherwise null
	 */
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
