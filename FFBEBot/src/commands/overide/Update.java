package commands.overide;

import java.util.HashMap;

import global.Main;
import global.Main.states;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;

public class Update extends OverrideGenerics implements OverrideCommand {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		Settings.executor.execute(new Runnable(){
			public void run(){
				try{
					boolean trigger=false;
					boolean updated=false;//if anything was updated
					if(args.containsKey("r")){
						Counter count=new Counter("Setting up Update...",event);
						trigger=true;
						Main.setGame(states.Update);
						updated=SaveSystem.updateReddit(count)==true?true:updated;
					}
					if(args.containsKey("e")){
						Counter count=new Counter("Setting up Update...",event);
						trigger=true;
						Main.setGame(states.Update);
						updated=SaveSystem.updateExvius(count)==true?true:updated;
					}
					if(args.containsKey("img")){
						trigger=true;
						Main.setGame(states.Update);
						updated=SaveSystem.updateSummons()==true?true:updated;
					}
					if(!trigger){
						Main.setGame(states.Update);
						Counter count=new Counter("Setting up Update...",event);
						updated=SaveSystem.updateReddit(count)==true?true:updated;
						count=new Counter("Setting up Update...",event);
						updated=SaveSystem.updateExvius(count)==true?true:updated;
						count.setMessage("Updating Summons...");
						updated=SaveSystem.updateSummons()==true?true:updated;
						count.terminate();
					}
					
					Main.setGame(states.randomReady());
					if(updated){
						SaveSystem.writeData();
						Lib.sendMessage(event, "Data Updated");
					}
					else{
						Lib.sendMessage(event, "Everything Already up to Date");
					}
					}catch(Exception e){
					Log.logError(e);
				}
			}
		});
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
