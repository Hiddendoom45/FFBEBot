package global;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.CounterPool;
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
	public static JDA jda;//JDA of bot
	public static final CommandParser parser=new CommandParser();//parse most commands
	//hashmaps to easily search for each of the commands
	public static final HashMap<String,Command> commands=new HashMap<String,Command>();
	public static final HashMap<String,Command> modCommands=new HashMap<String,Command>();
	public static final HashMap<String,OverrideCommand> overrides=new HashMap<String,OverrideCommand>();
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
		setGame(states.Ready);
	}
	public static void shutdown(){
		jda.shutdown(false);
		Log.log("status", "bot shutdown");
	}
	public static void quit(){
		jda.shutdown(true);
		Log.log("status", "Bot Quit");
		Log.save();
		System.exit(1);
	}
	/**
	 * everything that needs to be done when the JVM starts up
	 */
	public static void setup(){
		setGame(states.Loading);
		//put commands in map
		commands.put("ping", new Ping());
		commands.put("units", new Units());
		commands.put("unit", new Units());
		commands.put("runits", new RUnits());
		commands.put("runit", new RUnits());
		commands.put("unitart", new UnitArt());
		//commands.put("salt", new Salt());//removed due to it not really being good, to be improved
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
		modCommands.put("prefix", new Prefix());
		modCommands.put("modprefix", new ModPrefix());
		modCommands.put("join", new Join());
		modCommands.put("sleep", new Sleep());
		modCommands.put("toggle", new Toggle());
		//ditto with override commands
		overrides.put("disable", new Disable());
		overrides.put("log", new ViewLog());
		overrides.put("logsave", new SaveLog());
		overrides.put("botmod", new BotMod());
		overrides.put("update", new Update());
		overrides.put("reload", new Reload());
		overrides.put("thread", new Threading());
		overrides.put("logupload", new LogUpload());
		overrides.put("pingtoggle", new PingToggle());
		//setup/build various things
		Log.setup();
		SaveSystem.setup();
		Restarter.setup();
		CounterPool.getPool().setup();
		RedditUnit.buildRefImg();
		setGame(states.Ready);
	}
	public static void setGame(states state){
		switch(state){
		case Loading:jda.getAccountManager().setGame("the Loading Game...");
		break;
		case Ready:jda.getAccountManager().setGame("Offline...| -!help");
		break;
		}
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
	public static boolean handleOverride(ArgContainer args,MessageReceivedEvent event){
		if(overrides.containsKey(args.command)){
			if(args.args.containsKey("help")){
				overrides.get(args.command).help(event);
				return true;
			}
			boolean safe=overrides.get(args.command).called(args.args, event);
			if(safe){
				overrides.get(args.command).action(args.args, event);
			}
			overrides.get(args.command).executed(safe, event);
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
	/**
	 * enum for states of the bot, displayed by game it's playing
	 * @author Allen
	 *
	 */
	public static enum states{
		Loading,
		Ready;
	}
}
