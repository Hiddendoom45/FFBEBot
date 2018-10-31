package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmoteData {
	public final String tag;
	public final String strValue;
	public final String emoteValue;
	private static Pattern valPattern = Pattern.compile("<:(.*?):(\\d{18})>");
	//matcher for the emoteValue
	private transient Matcher m;
	/**
	 * Basic data to format emotes
	 * @param tag unique string that will be replaced by either strValue or emoteValue when formatted
	 * @param strValue string describing emote, used if bot does not have permissions to use emotes
	 * @param emoteValue string for emote
	 */
	public EmoteData(String tag,String strValue,String emoteValue){
		this.tag=tag;
		this.strValue=strValue;
		m = valPattern.matcher(emoteValue);
		if(!m.matches()){
			throw new IllegalArgumentException("Emote value is not in the proper format for a custom emote <:[name]:[id]>");
		}
		this.emoteValue=emoteValue;
	}
	//parses ID from the emote value
	public String getID(){
		if(m==null){
			m = valPattern.matcher(emoteValue);
			m.matches();
		}
		return m.group(2);
	}
	public String getEmoteName(){
		if(m==null){
			m = valPattern.matcher(emoteValue);
			m.matches();
		}
		return m.group(1);
	}
}
