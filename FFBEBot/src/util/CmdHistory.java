package util;

import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Class that holds command history for each channel
 * @author Allen
 *
 */
public class CmdHistory{
	private static HashMap<Long,HistoryLL> historyMap = new HashMap<Long,HistoryLL>();
	
	public static void incMsg(MessageReceivedEvent event){
		long channel = event.getChannel().getIdLong();
		if(historyMap.containsKey(channel)){
			HistoryLLNode  n = historyMap.get(channel).get();
			if(!(n==null)){
				n.msgRec();
			}
			else{
				//remove empty list
				historyMap.remove(channel);
			}
		}
	}
	
	public static HistoryLL getHist(MessageReceivedEvent event){
		long channel = event.getChannel().getIdLong();
		if(!historyMap.containsKey(channel)){
			historyMap.put(channel, new HistoryLL());
		}
		return historyMap.get(channel);
	}
}
