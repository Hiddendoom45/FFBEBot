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
	private static HashMap<String,Spam> spammers=new HashMap<String,Spam>();
	private static HashMap<String,int[]> typeData=new HashMap<String,int[]>();//hashmap with array containing data on timeout and number of spaces for spam control
	private static HashMap<MessageReceivedEvent, Long> spamData=new HashMap<MessageReceivedEvent,Long>();//times and when spam messages have been sent
	private static final int spamPersistance=20;
	public static boolean isSpam(MessageReceivedEvent event,String type){
		String key=getTypeData(type)[0]==0?key1(event):key2(event);//determine if it's global or channel locked spam controlling
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
	public static void setSpams(){
		//type(0==local,1==global),limit, timeout
		typeData.put("summon", new int[]{1,2,60000});
		typeData.put("units", new int[]{0,4,60000});
	}
	private static void sendSpamMessage(MessageReceivedEvent event,String type,SpamData spam){
		if(!spamData.containsKey(event)){
			spamData.put(event, System.currentTimeMillis());
			Lib.sendTempMessage(event, "**%userName%** too many messages, please wait **"+(spam.tillDone/1000)+"** seconds", spamPersistance);
		}
	}
	private static String key1(MessageReceivedEvent event){
		return event.getAuthor().getId()+event.getChannel().getId();//based off of person and channel, as spam is going to be set in part on an event basis
	}
	private static String key2(MessageReceivedEvent event){
		return event.getAuthor().getId();//based off of person a universal spam control, for certain commands(aka summon simulator)
	}
	private static int[] getTypeData(String type){
		return typeData.containsKey(type)?typeData.get(type):new int[]{1,5,120000};//default uses global control, limit of 5 every 2 min
	}
	
	
}
