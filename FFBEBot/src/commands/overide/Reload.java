package commands.overide;

import java.util.HashMap;

import global.Main;
import global.Main.states;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Secrets;
import global.record.Settings;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;

public class Reload implements OverrideCommand {

	public static void main(String[] args){
		SaveSystem.preloadReddit(null);
		SaveSystem.preloadExvius(null);
		SaveSystem.preloadSummons(null);
		SaveSystem.writeData();
	}
	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Settings.executor.execute(new Runnable(){
			public void run(){
				try{
				boolean trigger=false;//if any of the arguments are triggered
				if(args.containsKey("r")){
					Counter count=new Counter("Setting up preloader...",event);
					trigger=true;
					Main.setGame(states.Loading);
					SaveSystem.preloadReddit(count);
				}
				if(args.containsKey("e")){
					Counter count=new Counter("Setting up preloader...",event);
					trigger=true;
					Main.setGame(states.Loading);
					SaveSystem.preloadExvius(count);
				}
				if(args.containsKey("img")){
					Counter count=new Counter("Setting up preloader...",event);
					trigger=true;
					Main.setGame(states.Loading);
					SaveSystem.preloadSummons(count);
				}
				if(!trigger){//reload everything if nothing specified
					Counter count=new Counter("Setting up preloader...",event);
					Main.setGame(states.Loading);
					SaveSystem.preloadReddit(count);
					count=new Counter("Setting up preloader...",event);
					SaveSystem.preloadExvius(count);
					count=new Counter("Setting up preloader...",event);
					SaveSystem.preloadSummons(count);
				}
				SaveSystem.writeData();//write data to file
				if(!Settings.token.equals(Secrets.testToken))DriveManager.update(new DriveFile(Settings.preloadData,DataEnum.PreloadData.id));
				Main.setGame(states.randomReady());//set state to ready again
				Lib.sendMessage(event, "Data reloaded");
				}
				catch(Exception e){//log error as cause it's in a separate thread it's not caught by the overall handler
					Log.log("ERROR", "error occured when reloading");
					Log.logError(e);
				}
			}
		});
			
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		

	}

}
