package util;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
/**
 * Class used to count the progress of a command that takes a while i.e. preloading data, summoning units
 * Updates the message at a one second interval
 * @author Allen
 *
 */
public class Counter{
	private String messageID;
	private String message;
	private MessageReceivedEvent event;
	private int i;
	private boolean end=false;
	public Counter(String message,MessageReceivedEvent event){
		this.message=message;
		this.event=event;
		messageID=Lib.sendMessage(event, message.replace("%count%", ""+i)).getId();
		CounterPool.getPool().add(this);
	}
	public Counter count() {
		synchronized(this){
			if(!end){
				event.getChannel().getMessageById(messageID).complete().editMessage(message.replace("%count%", ""+i)).submit();
			}
			else{
				event.getChannel().deleteMessageById(messageID).submit();
			}
		}
		return this;
	}
	public synchronized void terminate(){
		end=true;
	}
	public synchronized boolean hasTerminated(){
		return end;
	}
	public synchronized void setMessage(String message){
		this.message=message;
	}
	public synchronized void setI(int i){
			this.i=i;
	}

}
