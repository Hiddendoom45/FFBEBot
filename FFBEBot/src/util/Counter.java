package util;

import global.Main;
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
	private int tries=0;
	public Counter(String message,MessageReceivedEvent event){
		this.message=message;
		this.event=event;
		messageID=Lib.sendMessage(event, message.replace("%count%", ""+i)).getId();
		CounterPool.getPool().add(this);
	}
	public Counter count() {
		synchronized(this){
			if(!end){
				try{
					event.getChannel().getMessageById(messageID).complete().editMessage(message.replace("%count%", ""+i)).submit();
				}catch(IllegalStateException e){
					if (tries<3){//another attempt at fixing the counter errors
						Main.jda.getGuildById(event.getGuild().getId()).getTextChannelById(event.getChannel().getId()).getMessageById(messageID).complete().editMessage(message.replace("%count%", ""+i)).submit();
					tries++;
					}
					else{
						throw e;
					}
				}
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
