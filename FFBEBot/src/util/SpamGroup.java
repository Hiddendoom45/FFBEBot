package util;

import java.time.Duration;

/**
 * Container class containing basic info for spam control
 * @author Allen
 *
 */
public class SpamGroup {
	/**
	 * Spam control affects user doesn't matter what channel they spam in
	 */
	public static final int GLOBAL=1;
	/**
	 * Spam control based on channel
	 */
	public static final int LOCAL=0;
	private final int[] values;
	private final String name;
	/**
	 * 
	 * @param name
	 * @param isGlobal SpamInfo.GLOBAL or SpamInfo.LOCAL. local affects only specific channel where messages are sent,
	 * global affects user in all channels 
	 * @param limitInTimeRange number of times the message must be sent within time range before it is considered spam
	 * @param timeRange time range in milliseconds
	 */
	public SpamGroup(String name,int isGlobal,int limitInTimeRange,int timeRange){
		this.name=name;
		this.values=new int[]{isGlobal,limitInTimeRange,timeRange};
	}
	/**
	 * 
	 * @param name
	 * @param isGlobal SpamInfo.GLOBAL or SpamInfo.LOCAL. local affects only specific channel where messages are sent,
	 * global affects user in all channels 
	 * @param limitInTimeRange number of times the message must be sent within time range before it is considered spam
	 * @param timeRange time range in milliseconds
	 */
	public SpamGroup(String name,int isGlobal,int limitInTimeRange,long timeRange){
		this.name=name;
		if(timeRange>Integer.MAX_VALUE){
			throw new IllegalArgumentException("time range must be less than integer max");
		}
		this.values=new int[]{isGlobal,limitInTimeRange,(int)timeRange};
	}
	/**
	 * 
	 * @param name
	 * @param isGlobal SpamInfo.GLOBAL or SpamInfo.LOCAL. local affects only specific channel where messages are sent,
	 * global affects user in all channels 
	 * @param limitInTimeRange number of times the message must be sent within time range before it is considered spam
	 * @param timeRange time range
	 */
	public SpamGroup(String name,int isGlobal,int limitInTimeRange,Duration timeRange){
		this(name,isGlobal,limitInTimeRange,timeRange.toMillis());
	}
	public String getName(){
		return name;
	}
	protected int[] getValues(){
		return values;
	}
}
