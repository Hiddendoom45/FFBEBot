package global;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.RedditUnit;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import commands.*;
import commands.mod.*;
import commands.overide.*;
import global.ArgumentParser.ArgContainer;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;

public class Main {
	public static JDA jda;
	public static final CommandParser parser=new CommandParser();
	public static HashMap<String,Command> commands=new HashMap<String,Command>();
	public static HashMap<String,Command> modCommands=new HashMap<String,Command>();
	public static HashMap<String,OverrideCommand> overrides=new HashMap<String,OverrideCommand>();
	public static void main(String[] args){
		try{
			Main.startup();
			Main.setup();
		}catch(Exception e){
			Log.logError(e);
			Log.save();
		}
	}
	public static void startup() throws LoginException, IllegalArgumentException, InterruptedException{
		try{
		jda = new JDABuilder().addListener(new BotListener()).setBotToken(Settings.token).buildBlocking();
		}catch(LoginException e){
			TimeUnit.MINUTES.sleep(5);
			Log.log("System", "error on login, retrying in 5 minutes");
			startup();
		}
		jda.setAutoReconnect(true);
		jda.getAccountManager().setGame("Offline...| -!help");
	}
	public static void shutdown(){
		jda.shutdown(false);
		Log.log("status", "bot shutdown");
	}
	/**
	 * everything that needs to be done when the JVM starts up
	 */
	public static void setup(){
		//put commands in map
		commands.put("ping", new Ping());
		commands.put("units", new Units());
		commands.put("unit", new Units());
		commands.put("runits", new RUnits());
		commands.put("runit", new RUnits());
		commands.put("unitart", new UnitArt());
		commands.put("salt", new Salt());
		commands.put("chancetilith", new Chance());
		commands.put("summon", new Summon());
		commands.put("salty", new Salty());
		commands.put("waifu", new Waifu());
		commands.put("unitskill", new Skill());
		commands.put("skill", new Skill());
		commands.put("runitskill", new RSkill());
		commands.put("rskill", new RSkill());
		commands.put("lore", new Lore());
		commands.put("equipment", new Equipment());
		commands.put("requipment", new REquipment());
		commands.put("awaken", new Awaken());
		commands.put("rawaken", new RAwaken());
		commands.put("maintenance", new Maintenance());
		
		//ditto with mod commands(separate maps due to special checks)
		modCommands.put("disable", new Disable());
		modCommands.put("prefix", new Prefix());
		modCommands.put("modprefix", new ModPrefix());
		modCommands.put("join", new Join());
		modCommands.put("sleep", new Sleep());
		modCommands.put("toggle", new Toggle());
		//ditto with override commands
		overrides.put("log", new ViewLog());
		overrides.put("logsave", new SaveLog());
		overrides.put("botmod", new BotMod());
		//setup/build various things
		Log.setup();
		SaveSystem.setup();
		Restarter.setup();
		RedditUnit.buildRefImg();
	}
	public static void handleCommand(CommandParser.CommandContainer cmd){
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
		}
		else if(modCommands.containsKey(cmd.invoke)&&cmd.isModCmd){
			if(!isMod(cmd.e)){
				Lib.sendMessage(cmd.e, ":no_entry_sign: You are not authorized to use mod commands here");
				return;
			}
			boolean safe=modCommands.get(cmd.invoke).called(cmd.args, cmd.e);
			if(safe){
				modCommands.get(cmd.invoke).action(cmd.args, cmd.e);
				modCommands.get(cmd.invoke).executed(safe, cmd.e);
			}
			else{
				modCommands.get(cmd.invoke).executed(safe, cmd.e);
			}
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
		}
	}
	public static void handleOverride(ArgContainer args,MessageReceivedEvent event){
		if(overrides.containsKey(args.command)){
			if(args.args.containsKey("help")){
				overrides.get(args.command).help(event);
				return;
			}
			boolean safe=overrides.get(args.command).called(args.args, event);
			if(safe){
				overrides.get(args.command).action(args.args, event);
			}
			overrides.get(args.command).executed(safe, event);
		}
	}
	private static boolean isMod(MessageReceivedEvent e){
		List<Role> roles=e.getGuild().getRolesForUser(e.getAuthor());
		for(Role r:roles){
			if(r.hasPermission(Permission.ADMINISTRATOR)){
				return true;
			}
		}
		for(String s:SaveSystem.getGuild(e.getGuild().getId()).modded){
			if(e.getAuthor().getId().equals(s)){
				return true;
			}
		}
		return false;
	}
	public static void log(String type,String msg){
		Log.log(type, msg);
	}
}
