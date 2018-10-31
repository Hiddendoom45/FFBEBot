package util;

public class EmoteData {
	final String tag;
	final String strValue;
	final String emoteValue;
	/**
	 * Basic data to format emotes
	 * @param tag unique string that will be replaced by either strValue or emoteValue when formatted
	 * @param strValue string describing emote, used if bot does not have permissions to use emotes
	 * @param emoteValue string for emote
	 */
	public EmoteData(String tag,String strValue,String emoteValue){
		this.tag=tag;
		this.strValue=strValue;
		this.emoteValue=emoteValue;
	}
}
