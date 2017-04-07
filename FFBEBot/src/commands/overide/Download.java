package commands.overide;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Downloads the uploaded file to the bot, file is based on the filename used
 * @author Allen
 *
 */
public class Download extends OverrideGenerics implements OverrideCommand{

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		return ownerValidate(event);
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		String attach=event.getMessage().getAttachments().get(0).getFileName();
		if(valid(attach)){
			try {
				Files.delete(new File(attach.substring(attach.indexOf(".")+1)).toPath());
			} catch (IOException e) {
				Log.logError(e);
			}
			event.getMessage().getAttachments().get(0).download(new File(attach.substring(attach.indexOf(".")+1)));
		}

	}
	private boolean valid(String attach){
		if(attach.contains("FFBEBotLog")){
			return true;
		}
		if(attach.contains(Settings.dataSource)){
			return true;
		}
		if(attach.contains("overrides")){
			return true;
		}
		if(attach.contains(Settings.preloadData)){
			return true;
		}
		return false;
	}
	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
