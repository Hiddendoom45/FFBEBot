package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Maintenance implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("status", "Maintenance is occuring for "+event.getAuthor().getName()+(event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild().getName()));
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try {
			Files.copy(getClass().getResourceAsStream("/Library/GUMI.jpg"), new File("tempGumi.jpg").toPath(),StandardCopyOption.REPLACE_EXISTING);
			try {
				Settings.upload.acquire();
			} catch (InterruptedException e) {}
			Lib.sendFile(event, "null", new File("tempGumi.jpg"));
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
