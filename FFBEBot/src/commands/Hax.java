package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Hax extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try {
			Files.copy(getClass().getResourceAsStream("/Library/hax.jpg"), new File("tempHax.jpg").toPath(),StandardCopyOption.REPLACE_EXISTING);
			try {
				Settings.upload.acquire();
			} catch (InterruptedException e) {}
			Lib.sendFile(event, "gumi.sg", new File("tempHax.jpg"));
			Settings.upload.release();
			Files.delete(new File("tempHax.jpg").toPath());
		} catch (IOException e) {Log.logError(e);}

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"gumi.sg\n"
				+ "A random screenshot of gumi.sg";
		Lib.sendMessage(event, s);
	}

}
