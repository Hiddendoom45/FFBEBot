package global;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import Library.ModuleEnum;
import commands.*;
import commands.mod.*;
import commands.overide.*;
import global.record.Log;
import global.record.PostgresDB;
import global.record.SaveSystem;
import global.record.Secrets;
import global.record.Settings;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import util.CmdControl;
import util.CounterPool;
import util.Overrider;
import util.SpamControl;
import util.SpamGroup;
import util.rng.RandomLibs;
import util.unit.RedditUnit;
import util.unit.UnitAlias;

public class Main {
	public static JDA jda;//JDA of bot 

	public static void main(String[] args){
		try{
			Runtime.getRuntime().addShutdownHook(new Thread() {//bot shutdown, push and upload data
				@Override
				public void run() {
					Settings.loaded=false;//avoid old version from responding when shutting down
					System.out.println("shutting down");
					if(Settings.token.contentEquals(Secrets.token)){
						SaveSystem.pushUserData();
						Log.save();//last as this may take a long time as is not as high of a priority to complete
						DriveManager.update(new DriveFile(Log.LogSource,DataEnum.LogSource.id));
					}
				}   
			}); 
			
			Main.startup();
			Main.setup();
		}catch(Exception e){
			Log.logError(e);
			Log.save();
		}
	}
	public static void startup() throws LoginException, IllegalArgumentException, InterruptedException{
		try{
		jda = new JDABuilder(AccountType.BOT).addEventListener(new BotListener()).setToken(Settings.token).build();
		jda.awaitReady();
		}catch(LoginException e){
			Log.log("System", "error on login, retrying in 5 minutes");
			TimeUnit.MINUTES.sleep(5);
			startup();
		}
		jda.setAutoReconnect(true);
		setGame(states.randomReady());
	}
	public static void shutdown(){
		//Do nothing, no option to shutdown temporarily
		//jda.shutdown(false);
		//Log.log("status", "bot shutdown");
	}
	public static void quit(){
		jda.shutdown();
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
		String Module=ModuleEnum.Core.toString();
		CmdControl.addCommand("ping", new Ping(),Module);
		CmdControl.addCommand("info", new Info(),Module);
		CmdControl.addCommand("servers", new Servers(), Module);
		CmdControl.addCommand("invite", new Invite(), Module);
		CmdControl.addCommand(new String[]{"bugreport","bug"}, new BugReport(), Module);
		CmdControl.addCommand(new String[]{"emo","emote"}, new Emote(), Module);
		
		Module=ModuleEnum.Exvius.toString();
		CmdControl.addCommand("units", new Units(), Module);
		CmdControl.addCommand("unit", new Units(), Module);
		CmdControl.addCommand("unitart", new UnitArt(), Module);
		CmdControl.addCommand("unitskill", new Skill(), Module);
		CmdControl.addCommand("skill", new Skill(), Module);
		CmdControl.addCommand("lore", new Lore(), Module);
		CmdControl.addCommand("equipment", new Equipment(), Module);
		CmdControl.addCommand("awaken", new Awaken(), Module);
		
		Module=ModuleEnum.Reddit.toString();
		CmdControl.addCommand("runit", new RUnits(), Module);
		CmdControl.addCommand("runit", new RUnits(), Module);
		CmdControl.addCommand("runitskill", new RSkill(), Module);
		CmdControl.addCommand("rskill", new RSkill(), Module);
		CmdControl.addCommand("requipment", new REquipment(), Module);
		CmdControl.addCommand("rawaken", new RAwaken(), Module);
		CmdControl.addCommand(new String[]{"flair","flairs"}, new Flair(), Module);
		
		Module=ModuleEnum.Salt.toString();
		CmdControl.addCommand("summon", new Summon(), Module);
		//reference https://old.reddit.com/r/FFBraveExvius/comments/7x8lgx/5star_guaranteed_cash_summon/
		CmdControl.addCommand(new String[]{"gsummon","cashsummon"}, new GuaranteeSummon(), Module);
		CmdControl.addCommand("salty", new Salty(), Module);
		CmdControl.addCommand("waifu", new Waifu(), Module);
		CmdControl.addCommand("husbando", new Husbando(), Module);
		CmdControl.addCommand(new String[]{"sieghard","sieghart","virilehusbando","$ieghard","ieghard","ieghart","$ieghart"}, new Sieghart(), Module);
		CmdControl.addCommand("banner", new Banners(), Module);
		CmdControl.addCommand("banners", new Banners(), Module);
		CmdControl.addCommand("bannerlist", new Banners(), Module);
		CmdControl.addCommand("maintenance", new Maintenance(), Module);
		CmdControl.addCommand("nier", new Nier(), Module);
		CmdControl.addCommand("gumi.sg", new Hax(), Module);
		CmdControl.addCommand(new String[]{"workhard","workharder","workhardest"}, new WorkHard(), Module);
		
		Module=ModuleEnum.Simulation.toString();
		CmdControl.addCommand("dailies", new Dailies(), Module);
		CmdControl.addCommand("daily", new Dailies(), Module);
		CmdControl.addCommand("sc",new SacredCrystal(), Module);
		CmdControl.addCommand("sacredcrystal", new SacredCrystal(), Module);
		CmdControl.addCommand("lapis", new Lapis(), Module);
		//CmdControl.addCommand("give", new Give(), Module);
		CmdControl.addCommand("pull", new commands.Pull(),Module);
		//CmdControl.addCommand("gpull", new GuaranteePull(), Module);
		CmdControl.addCommand("unitinventory", new UnitInventory(), Module);
		CmdControl.addCommand("dailypull", new DailyPull(), Module);
		CmdControl.addCommand("11pull", new ElevenPull(), Module);
		CmdControl.addCommand("unitsell", new UnitSell(), Module);
		CmdControl.addCommand("unitawaken", new UnitAwaken(), Module);
		CmdControl.addCommand("duel", new Duel(), Module);
		CmdControl.addCommand(new String[]{"duelend","endduel","enduel"}, new DuelEnd(), Module);
		
		//ditto with mod commands(separate maps due to special checks)
		CmdControl.addModCommand("prefix", new Prefix());
		CmdControl.addModCommand("modprefix", new ModPrefix());
		CmdControl.addModCommand("join", new Join());
		CmdControl.addModCommand("sleep", new Sleep());
		CmdControl.addModCommand("toggle", new Toggle());
		CmdControl.addModCommand("glmodules", new DisableGlobal());
		CmdControl.addModCommand("modules", new commands.mod.Disable());
		
		//ditto with override commands
		Overrider.addOverrideCommand("disable", new commands.overide.Disable());
		Overrider.addOverrideCommand("log", new ViewLog());
		Overrider.addOverrideCommand("logsave", new SaveLog());
		Overrider.addOverrideCommand("botmod", new BotMod());
		Overrider.addOverrideCommand("reload", new Reload());
		Overrider.addOverrideCommand("thread", new Threading());
		Overrider.addOverrideCommand("upload", new Upload());
		Overrider.addOverrideCommand("download", new Download());
		Overrider.addOverrideCommand("logclear", new ClearLog());
		Overrider.addOverrideCommand("gamechange", new ChangeGame());
		Overrider.addOverrideCommand("changegame", new ChangeGame());
		Overrider.addOverrideCommand("award", new Award());
		Overrider.addOverrideCommand("update", new Update());
		Overrider.addOverrideCommand("push", new DrivePush());
		Overrider.addOverrideCommand("pull", new commands.overide.Pull());
		Overrider.addOverrideCommand("dbanner", new DefaultBanner());
		Overrider.addOverrideCommand("blacklist", new Blacklist());
		Overrider.addOverrideCommand("unitindex", new UnitIndex());
		Overrider.addOverrideCommand("uniteprint", new UnitEPrint());
		Overrider.addOverrideCommand("ratetest", new RateTest());
		Overrider.addOverrideCommand("addalias", new AddAlias());
		Overrider.addOverrideCommand("analytics", new DumpAnalytics());
		Overrider.addOverrideCommand("rainbows", new Rainbows());
		if(Settings.token==Secrets.testToken){//only active on the test token, override command only used for testing purposes
			Overrider.addOverrideCommand("test", new Test());
		}
		
		//setup/build various things
		SpamControl.addGroup(new SpamGroup("waifuhusbando",SpamGroup.LOCAL,6,TimeUnit.MINUTES.toMillis(1)));
		SpamControl.addGroup(new SpamGroup("summon",SpamGroup.GLOBAL,3,TimeUnit.MINUTES.toMillis(1)));
		SpamControl.addGroup(new SpamGroup("units",SpamGroup.LOCAL,4,TimeUnit.MINUTES.toMillis(1)));
		
		DriveManager.setup();//loads drive files and setup the service
		Log.setup();//start log thread that saves it every 6 hours
		Restarter.setup();//starts the threads that queue the bot restarting
		CounterPool.getPool().setup();//starts the thread for the counter pool
		RedditUnit.buildRefImg();//builds hashmap for image icons
		UnitAlias.deserialize();//load all the aliases for various units from file
		SaveSystem.setup();//loads all the data
		PostgresDB.setup();//connects to database server
		Settings.loaded=true;//mark everything as loaded, botListener will now start actually parsing stuff
		setGame(states.randomReady());//sets the game for a random state
		
	}
	public static void setGame(states state){
		String game="";
		switch(state){
		case Loading:game=" the Loading Game...";
		break;
		case Maintenance:game=" undergoing maintenance";
		break;
		case Dead:game=" dead...";
		break;
		case Update:game=" updating data...";
		break;
		case Ready1:game=" with RNG|@FFBEBot help";
		break;
		case Ready2:game="*praying to RNGesus|@FFBEBot help";
		break;
		case Ready3:game=" a dead game|@FFBEBot help";
		break;
		case Ready4:game=" FFBE |@FFBEBot help";
		break;
		case Ready5:game=" with a salty summoner|@FFBEBot help";
		break;
		case Ready6:game=" in the salt mines|@FFBEBot help";
		break;
		case Ready7:game=" in gacha hell|@FFBEBot help";
		break;
		case Ready8:game=" in Lapis|@FFBEBot help";
		break;
		case Ready9:game=" in Paladia|@FFBEBot help";
		break;
		}
		jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT,game));
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
		Maintenance,
		Dead,
		Update,
		Ready1,
		Ready2,
		Ready3,
		Ready4,
		Ready5,
		Ready6,
		Ready7,
		Ready8,
		Ready9;
		public static int rand;
		public static states randomReady(){
			states s=RandomLibs.SelectRandom(new states[]{Ready1,Ready2,Ready3,Ready4,Ready5,Ready6,Ready7,Ready8,Ready9});
			return s;
		}
	}
}
