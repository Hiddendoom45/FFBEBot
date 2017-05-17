package util;

import java.util.HashMap;
import java.util.List;

import commands.Command;
import global.CommandParser;
import global.Main;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Use to hold and handle commands
 * @author Allen
 *
 */
public class CmdControl {
	public static final CommandParser parser=new CommandParser();//parse most commands
	private static HashMap<String,Command> commands=new HashMap<String,Command>();
	private static HashMap<String,Command> modCommands=new HashMap<String,Command>();
	private static HashMap<String,String> modules=new HashMap<String,String>();//used to get which module the command is from
	public static boolean parseCommands(MessageReceivedEvent event){
		if(event.getAuthor().getId().equals(Main.jda.getSelfUser().getId()))return false;
		String content=event.getMessage().getContent();
		if(content.startsWith(SaveSystem.getPrefix(event))){
			CommandParser.CommandContainer cmd=parser.parse(content, event);
			if(CommandEnabled(event,cmd.invoke)){
				return handleCommand(parser.parse(content, event));
			}
		}
		if(content.startsWith(SaveSystem.getModPrefix(event))){
			return handleCommand(parser.parse(content, event));
		}
		return false;
	}
	public static void commandAction(MessageReceivedEvent event, String command, String[] args){
		if(CommandEnabled(event,command)){
			commands.get(command).action(args, event);
		}
	}
	public static void addCommand(String commandName, Command command, String Module){
		commands.put(commandName, command);
		modules.put(commandName, Module);
	}
	public static void addModCommand(String commandName,Command command){
		modCommands.put(commandName, command);
	}
	private static boolean handleCommand(CommandParser.CommandContainer cmd){
		System.out.println(cmd.invoke);
		if(commands.containsKey(cmd.invoke)&&!cmd.isModCmd){
			boolean safe=commands.get(cmd.invoke).called(cmd.args, cmd.e);
			if(safe){
				commands.get(cmd.invoke).action(cmd.args, cmd.e);
				commands.get(cmd.invoke).executed(safe, cmd.e);
			}
			else{
				commands.get(cmd.invoke).executed(safe, cmd.e);
			}
			return true;
		}
		else if(modCommands.containsKey(cmd.invoke)&&cmd.isModCmd){
			if(!isMod(cmd.e)){
				Lib.sendMessage(cmd.e, ":no_entry_sign: You are not authorized to use mod commands here");
			}
			boolean safe=modCommands.get(cmd.invoke).called(cmd.args, cmd.e);
			if(safe){
				modCommands.get(cmd.invoke).action(cmd.args, cmd.e);
				modCommands.get(cmd.invoke).executed(safe, cmd.e);
			}
			else{
				modCommands.get(cmd.invoke).executed(safe, cmd.e);
			}
			return true;
		}
		else if(cmd.invoke.equals("help")){
			if(cmd.isModCmd){
				if(cmd.args.length>0&&modCommands.containsKey(cmd.args[0])){
					modCommands.get(cmd.args[0]).help(cmd.e);
				}
				else{
					Lib.printModHelp(cmd.e);
				}
			}
			else{
				if(cmd.args.length>0&&commands.containsKey(cmd.args[0])){
					commands.get(cmd.args[0]).help(cmd.e);
				}
				else{
					Lib.printHelp(cmd.e);
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * checks if user is mod and has the admin privilege, or has been set to bot mod through overrides
	 * @param e message received from user
	 * @return whether or not user is a mod or not
	 */
	private static boolean isMod(MessageReceivedEvent e){
		try{
			List<Role> roles=e.getMember().getRoles();
			for(Role r:roles){
				if(r.hasPermission(Permission.ADMINISTRATOR)||r.hasPermission(Permission.MANAGE_SERVER)||r.hasPermission(Permission.MANAGE_CHANNEL)||r.hasPermission(Permission.MESSAGE_MANAGE)){
					return true;
				}
			}
			for(String s:SaveSystem.getGuild(e.getGuild().getId()).modded){
				if(e.getAuthor().getId().equals(s)){
					return true;
				}
			}
		}
		catch(Exception e1){
			Log.logError(e1);
		}
		
		if(e.getAuthor().getId().equals(Settings.ownerID))return true;
		return false;
	}
	private static boolean CommandEnabled(MessageReceivedEvent event, String command){
		if(event.getChannelType()==ChannelType.PRIVATE)return true;
		Settings guild=SaveSystem.getGuild(event.getGuild().getId());
		ModuleController ctrl=guild.disabled.get(modules.get(command));
		if(ctrl==null)return true;
		return ctrl.enabled(event.getChannel().getId());
	}
}
