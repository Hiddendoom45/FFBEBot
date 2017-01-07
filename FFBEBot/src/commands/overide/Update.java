package commands.overide;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import global.Main;
import global.record.Log;
import net.dv8tion.jda.entities.Message.Attachment;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Update implements OverrideCommand {

	@Override
	public boolean called(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(event.getMessage().getAttachments().size()>0){
			List<Attachment> atts=event.getMessage().getAttachments();
			for(Attachment a:atts){
				if(a.getFileName().endsWith(".jar")){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		List<Attachment> atts=event.getMessage().getAttachments();
		for(Attachment a:atts){
			if(a.getFileName().endsWith(".jar")){
				try {
					Files.delete(new File("FFBEBot.jar").toPath());
					a.download(new File("FFBEBot.jar"));
					Process p = Runtime.getRuntime().exec("java -jar FFBEBot.jar");
					p.waitFor();
					Main.quit();
					return;
				} catch (IOException | InterruptedException e) {
					Log.logError(e);
				}
				
			}
		}
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
