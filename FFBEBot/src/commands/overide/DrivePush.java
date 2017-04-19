package commands.overide;

import java.util.HashMap;

import global.record.Log;
import global.record.Settings;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class DrivePush extends OverrideGenerics implements OverrideCommand{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		DriveManager.update(new DriveFile(Settings.dataSource,DataEnum.FFBEData.id));
		DriveManager.update(new DriveFile(Settings.preloadData,DataEnum.PreloadData.id));
		DriveManager.update(new DriveFile(Log.LogSource,DataEnum.LogSource.id));
		Lib.sendMessage(event, "Pushed files to Google Drive");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
