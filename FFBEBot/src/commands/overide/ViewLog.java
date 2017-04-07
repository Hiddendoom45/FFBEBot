package commands.overide;

import java.util.HashMap;

import global.record.Log;
import util.Lib;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ViewLog implements OverrideCommand{

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		String s="";
		if(!(args.get("l")==null)&&Lib.isNumber(args.get("l")[0])){
			if(!(args.get("s")==null)&&Lib.isNumber(args.get("s")[0])){
				s=Log.getLog(Integer.parseInt(args.get("l")[0]),Integer.parseInt(args.get("s")[0]));
			}
			else{
				s=Log.getLog(Integer.parseInt(args.get("l")[0]),0);
			}
		}
		else{
			s=Log.getLog(20,0);
		}
		Lib.sendMessage(event, s);
	}
	@Override
	public void help(MessageReceivedEvent event) {
	}
	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		
	}

}
