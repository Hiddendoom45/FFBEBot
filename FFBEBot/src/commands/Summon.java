package commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Library.summon.UnitSpecific;
import Library.summon.Unit;
import Library.summon.banner.Banner;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;
import util.SpamControl;
import util.rng.summon.Pull;
import util.rng.summon.SummonImageBuilder;

public class Summon extends CommandGenerics implements Command {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		if(super.called(args, event)){
			
		}
		return SpamControl.isSpam(event, "summon");
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0&&Lib.isNumber(args[0])){
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					try{
						int num=Integer.parseInt(args[0]);
						if(num>1000){//capped to 1800 units, beyond this it is close to Discord's 8MB file upload size cap//adjusted to lower
							num=1000;
						}
						Banner pullBanner=getBanner(args.length>1?(args[1]==null?"null":args[1]):"null");
						if(num==11){
							sendImage(event, Pull.pull11(pullBanner),pullBanner.name);
						}
						else if(num==0){
							ArrayList<UnitSpecific> units=new ArrayList<UnitSpecific>();
							units.add(new UnitSpecific(Unit.Bedile, 3));
							sendImage(event,units,pullBanner.name);
						}
						else{
							sendImage(event, Pull.pull(num,pullBanner),pullBanner.name);
						}
					}
					catch(Exception e){
						Log.logError(e);
					}
				}
			});
		}
		else{
			help(event);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
				+ "\n\tsummons [amount] units from the rare summon pool");
		
	}

	private Banner getBanner(String s){
		for(Banner b:Banner.values()){
			if(s.toLowerCase().equals(b.name.toLowerCase())||s.toLowerCase().equals(b.toString().toLowerCase())){
				return b;
			}
		}
		return Settings.DefaultBanner;
	}
	public void sendImage(MessageReceivedEvent event, List<UnitSpecific> units,String bannerName){
		Counter count=new Counter("Summoning Units...(%count%/"+units.size()+")",event);
		double factor=1;
		if(units.size()>100){
			factor=0.5;
		}
		BufferedImage build;
		if(units.size()<25){
			build=new SummonImageBuilder(factor).basePlate(3, "/Library/summon/6star.png").addUnit(units).build(event, count);
		}
		else{
			build=new SummonImageBuilder(factor).buildColumnsDynamically()
					.basePlate(3, "/Library/summon/6star.png").addUnit(units).build(event, count);
		}
		try{
			count.setMessage("Uploading...");
			Settings.upload.acquire();
			ImageIO.write(build, "PNG", new File("summons.png"));
			Lib.sendFile(event, Lib.FormatMessage(event, "%userMention% summoned "+units.size()+" units from the "+bannerName+" rare summon banner"), 
					new File("summons.png"));
		}catch(Exception e){
			Log.logError(e);
		}
		finally{
			count.terminate();
			Settings.upload.release();
		}
		try {
			Files.delete(new File("summons.png").toPath());
		} catch (IOException e) {
			Log.logShortError(e, 5);
		}
	}
}
