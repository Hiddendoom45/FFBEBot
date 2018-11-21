package util;

import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Spam.SpamData;

/**
 * Class used to control spam, calls to isSpam will determine if user has been spaming 'type'too much
 * @author Allen
 *
 */
public class SpamControl {
	private static HashMap<String,Spam> spammers=new HashMap<String,Spam>();//map of all users and their individual Spam object holding the last few messages
	private static HashMap<String,int[]> typeData=new HashMap<String,int[]>();//hashmap with array containing data on timeout and number of spaces for spam control
	private static HashMap<MessageReceivedEvent, Long> spamData=new HashMap<MessageReceivedEvent,Long>();//times and when spam messages have been sent
	private static final int spamPersistance=20;//Time in seconds for the rate limit message to remain for
	/**
	 * Whether message should be rate limited
	 * @param event
	 * @param type Type/group for the message (All messages within the same type contribute to the same message cap)
	 * @return true if message exceeds message cap and is considered spam
	 */
	public static boolean isSpam(MessageReceivedEvent event,String type){
		String key=getTypeData(type)[0]==0?localKey(event):globalKey(event);//determine if it's global or channel locked spam controlling
		int defaultSize=getTypeData(type)[1];//size of max entries before trigger
		int timeout=getTypeData(type)[2];//timeout for each entry to exit controller
		if(spammers.containsKey(key)){//if tracked in the current instance
			SpamData spam= spammers.get(key).isSpam(type,defaultSize,timeout);
			if(!spam.isSpam){
				sendSpamMessage(event,type,spam);
			}
			return spam.isSpam;
		}
		else{
			Spam spammer=new Spam();
			SpamData spam=spammer.isSpam(type,defaultSize,timeout);
			spammers.put(key, spammer);
			if(!spam.isSpam){
				sendSpamMessage(event,type,spam);
			}
			return spam.isSpam;
		}
	}
	/**
	 * Whether message should be rate limited
	 * @param event
	 * @param group type/group for the message (All messages within the same type contribute to the same message cap)
	 * @return
	 */
	public static boolean isSpam(MessageReceivedEvent event,SpamGroup group){
		typeData.put(group.getName(), group.getValues());
		return isSpam(event,group.getName());
	}
	/**
	 * Adds a new group for message rate limiting, with basic details on timeout etc. 
	 * @param group
	 */
	public static void addGroup(SpamGroup group){
		typeData.put(group.getName(), group.getValues());
	}
	/**
	 * Internal caller for the rate limited message
	 */
	private static void sendSpamMessage(MessageReceivedEvent event,String type,SpamData spam){
		if(!spamData.containsKey(event)){
			spamData.put(event, System.currentTimeMillis());
			Lib.sendTempMessage(event, "**%userName%** too many messages, please wait **"+(spam.tillDone/1000)+"** seconds", spamPersistance);
		}
	}
	//generate a key local to the channel for each user
	private static String localKey(MessageReceivedEvent event){
		return event.getAuthor().getId()+event.getChannel().getId();//based off of person and channel, as spam is going to be set in part on an event basis
	}
	//generate key for each user aka their ID
	private static String globalKey(MessageReceivedEvent event){
		return event.getAuthor().getId();//based off of person a universal spam control, for certain commands(aka summon simulator)
	}
	//get basic info on spamControl, global/local, message cap, timeout(ms)
	private static int[] getTypeData(String type){
		return typeData.containsKey(type)?typeData.get(type):new int[]{1,5,120000};//default uses global control, limit of 5 every 2 min
	}
	
	
}
