package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;

public class Maintenance implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", "Maintenance is occuring for "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild().getName()));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try {
			Files.copy(getClass().getResourceAsStream("/Lib/GUMI.jpg"), new File("tempGumi.jpg").toPath(),StandardCopyOption.REPLACE_EXISTING);
			try {
				Settings.upload.acquire();
			} catch (InterruptedException e) {}
			event.getChannel().sendFile(new File("tempGumi.jpg"),null);
			Settings.upload.release();
			Files.delete(new File("tempGumi.jpg").toPath());
		} catch (IOException e) {Log.logError(e);}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"maintenance\n"
				+ "\tclassic gumi maintenances";
		Lib.sendMessage(event, s);

	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
