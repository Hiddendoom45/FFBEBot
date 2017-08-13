package util;

import java.util.ArrayList;
import java.util.HashMap;

import global.record.Log;
import global.record.Settings;
/**
 * An attempt to execute all counters all in one thread, method of execution is based in part on the FRC
 * WPI scheduler class
 * @author Allen
 *
 */
public class CounterPool implements Runnable {
	private static HashMap<Integer,Counter> counters=new HashMap<Integer,Counter>();
	private static ArrayList<Integer> current=new ArrayList<Integer>();//current running counters
	private static ArrayList<Counter> toAdd=new ArrayList<Counter>();//counters that are added
	private static ArrayList<Integer> toRemove=new ArrayList<Integer>();//counters that are to be terminated
	private static int num=0;//id for counters, assume never will use all possible int numbers
	private static CounterPool pool;//counter pool
	public static CounterPool getPool(){
		if(pool==null){
			pool=new CounterPool();
		}
		return pool;
	}
	/**
	 * Executes using the main executor this thread, at 1 sec intervals
	 */
	public void setup(){
		Settings.executor.execute(this);
	}
	@Override
	/**
	 * iterates and executes each of the counters do not Reference this!
	 */
	public void run(){
		try{
			while(true){
				//execute current ones
				if(!counters.isEmpty()){
					for(Integer i:current){
						try{
							Counter c=counters.get(i).count();
							if(c.hasTerminated()){
								toRemove.add(i);
							}
						}
						catch(RuntimeException e1){//requester shutdown, stop counter counting
							toRemove.add(i);
						}
						catch(Exception e){
							Log.logError(e);
						}
					}
				}
				else{
					synchronized(this){
						this.wait();
					}
				}
				//add the ones in the list
				synchronized(this){
					if(!toAdd.isEmpty()){
						for(Counter c:toAdd){
							counters.put(num, c);
							current.add(num);
							increment();
						}
					}
					toAdd.clear();
				}
				//remove ones that have ended
				for(Integer i:toRemove){
					counters.remove(i);
					current.remove(i);
				}
				toRemove.clear();
				synchronized(this){
					this.wait(1000);
				}
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	private static void increment(){
		if(Integer.MAX_VALUE==num){
			num=Integer.MIN_VALUE;
		}
		else{
			num++;
		}
	}
	//added to separate list so that long execution times for each counter doesn't lock other threads
	public synchronized void add(Counter counter) {
		this.notifyAll();
		toAdd.add(counter);
	}

}
