package util;

import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * Class used to count the progress of a command that takes a while i.e. preloading data, summoning units
 * Updates the message at a one second interval
 * @author Allen
 *
 */
public class Counter implements Runnable{
	private String messageID;
	private String message;
	private MessageReceivedEvent event;
	private int i;
	private boolean end=false;
	public Counter(String message,MessageReceivedEvent event){
		this.message=message;
		this.event=event;
		messageID=Lib.sendMessage(event, message.replace("%count%", ""+i)).getId();
		Settings.executor.execute(this);
	}
	@Override
	public void run() {
		synchronized(this){
			while(!end){
				event.getTextChannel().getMessageById(messageID).updateMessage(message.replace("%count%", ""+i));
			try {
				wait(1000);
			} catch (InterruptedException e) {Log.logError(e);}
			}
			event.getChannel().deleteMessageById(messageID);
		}
	}
	public synchronized void terminate(){
		end=true;
	}
	public synchronized void setMessage(String message){
		this.message=message;
	}
	public synchronized void setI(int i){
			this.i=i;
	}

}
