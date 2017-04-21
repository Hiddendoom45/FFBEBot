package commands.overide;

import java.util.HashMap;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Pull extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		DriveManager.download(new DriveFile(Settings.dataSource,DataEnum.FFBEData.id));
		DriveManager.download(new DriveFile(Settings.preloadData,DataEnum.PreloadData.id));
		DriveManager.download(new DriveFile(Log.LogSource,DataEnum.LogSource.id));
		SaveSystem.load();
		Lib.sendMessage(event, "Files pulled from Drive and reloaded");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
