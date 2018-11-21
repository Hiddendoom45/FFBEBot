package util;


import java.util.HashMap;

/**
 * Item representing frequency of user action
 * Each one represents user activity in each of the areas/types
 * Executed to determine if user has been spamming specific bot commands that are spam limited
 * @author Allen
 *
 */
public class Spam {
	private HashMap<String,SpamItem[]> items=new HashMap<String,SpamItem[]>();
	
	/**
	 * Checks if something is spam
	 * @param type type used to differentiate between things like modules etc
	 * @return if there's been enough requests in 5 min for it to constitute as spam
	 */
	public SpamData isSpam(String type){
		return isSpam(type,5,120000);
	}
	public SpamData isSpam(String type,int defaultSize){
		return isSpam(type,defaultSize,120000);
	}
	public SpamData isSpam(String type, int defaultSize,int timeout){
		//Spam is determined based on a slot system, if all slots are filled the message is considered spam
		//Slots are evicted/cleared if it has timed out, the new spamItem will fill either a null slot or newly evicted slot
		
		boolean trigger=false;//if it's not spam trigger to true
		long tillDone=Long.MAX_VALUE;
		if(items.containsKey(type)){//if type is recorded
			boolean recorded=false;//if current time has been recorded to a slot
			SpamItem[] spams=items.get(type);//get times it was used
			for(int i=0;i<spams.length;i++){
				if(!(spams[i]==null)){//avoid null case, nothing recorded in that spot
					if(spams[i].timeout()){//if it's timed out, change it back to null, free up spot
						if(recorded){
							spams[i]=null;//change to null if current spam item has been recorded
							trigger=true;
						}
						else{//else record it
							spams[i]=new SpamItem(timeout);
							recorded=true;
							trigger=true;
						}
					}
					if(!(spams[i]==null)&&tillDone>spams[i].tillDone()){
						tillDone=spams[i].tillDone();
					}
				}
				else{
					trigger=true;
					if(!recorded){//if not recorded, record to a null slot
						spams[i]=new SpamItem(timeout);
						recorded=true;
					}
				}
			}
			items.put(type, spams);
		}
		else{
			SpamItem[] spams=new SpamItem[defaultSize];
			spams[0]=new SpamItem(timeout);
			items.put(type, spams);
			trigger=true;
		}
		return new SpamData(trigger,tillDone);
	}
	
	//Item to hold the basic details of a message for spam determination
	private class SpamItem{
		private long time;//time message received
		private long timeout;//time when it will not contribute to message cap
		public SpamItem(int timeout){
			time=System.currentTimeMillis();
			this.timeout=timeout;
		}
		//Time until the message will no longer contribute to message cap
		public long tillDone(){
			return (time+timeout)-System.currentTimeMillis();
		}
		//Test if message should continue to contribute to message cap
		public boolean timeout(){
			if(System.currentTimeMillis()>(time+timeout))return true;
			return false;
		}

	}
	/**
	 * Container to hold basic information from a call to isSpam 
	 * @author Allen
	 *
	 */
	public class SpamData{
		public boolean isSpam;//true if message is spam, should be rate limited
		public long tillDone;//Time in milliseconds until the user can send a message and for it to not be spam
		SpamData(boolean isSpam, long tillDone){
			this.isSpam=isSpam;
			this.tillDone=tillDone;
		}
	}
	
	
}
