package commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Library.pulls.PullUnit;
import Library.summon.UnitSpecific;
import global.record.Data;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;
import util.rng.summon.SummonImageBuilder;

public class UnitInventory extends CommandGenerics implements Command{
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		if(user.units.size()>0){
			sendImage(event);
		}
		else{
			Lib.sendMessage(event, "You have no units in your inventory");
		}

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="unitinventory\n"
				+ "\tshows the units you have";
		Lib.sendMessage(event, s);
	}
	public void sendImage(MessageReceivedEvent event){
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		Counter count=new Counter("Finding Units(%count%/"+user.units.size()+")...",event);
		ArrayList<UnitSpecific> us=new ArrayList<UnitSpecific>();
		for(PullUnit u:user.units){
			us.add(u.toSpecific());
		}
		try {
			double factor;
			if(user.units.size()>100){
				factor=0.5;
			}
			else{
				factor=1;
			}
			BufferedImage build;
			if(user.units.size()<25){
				build=new SummonImageBuilder(factor).addUnit(us).build(event, null);
			}
			else{
				build=new SummonImageBuilder(factor).buildColumnsDynamically().addUnit(us).build(event, count);
			}

			count.setMessage("Uploading...");
			try {
				Settings.upload.acquire();
			} catch (InterruptedException e) {
				Log.logError(e);
			}
			try{
			ImageIO.write(build, "PNG", new File("summons.png"));
			event.getChannel().sendFile(new File("summons.png"),new MessageBuilder().appendString(
					Lib.FormatMessage(event, "%userMention% has the following units")).build());
			}catch(Exception e){
				Log.logError(e);
			}
			finally{
				Settings.upload.release();
			}
			Files.delete(new File("summons.png").toPath());
			count.terminate();
		} catch (IOException e) {
			Log.logError(e);
		}
	}

}
