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
	public boolean isSpam(String type){
		return isSpam(type,5,120000);
	}
	public boolean isSpam(String type,int defaultSize){
		return isSpam(type,defaultSize,120000);
	}
	public boolean isSpam(String type, int defaultSize,int timeout){
		boolean trigger=false;//if it's spam trigger to true
		if(items.containsKey(type)){//if type is recorded
			boolean recorded=false;//if current time has been recorded to a slot
			SpamItem[] spams=items.get(type);//get times it was used
			for(int i=0;i<spams.length;i++){//iterate
				if(!(spams[i]==null)){//avoid null case, nothing recorded in that spot
					if(spams[i].timeout()){//if it's timed out, change it back to null, free up spot
						if(recorded){
							spams[i]=null;//change to null if current spam item has been recorded
						}
						else{//else record it
							spams[i]=new SpamItem(timeout);
							recorded=true;
						}
					}
				}
				else{
					if(!recorded){//if not recorded, record to a null slot
						spams[i]=new SpamItem(timeout);
						recorded=true;
					}
				}
			}
			
		}
		else{
			SpamItem[] spams=new SpamItem[defaultSize];
			spams[0]=new SpamItem(timeout);
			items.put(type, spams);
		}
		
		return trigger;
	}
	
	
	public class SpamItem{
		private long time;
		private long timeout;
		public SpamItem(int timeout){
			time=System.currentTimeMillis();
			this.timeout=timeout;
		}
		public boolean timeout(){
			if(System.currentTimeMillis()>time+timeout)return false;
			return true;
		}

	}
	
	
}
