package commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Library.summon.SummonedUnit;
import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Counter;
import util.Lib;
import util.SpamControl;
import util.rng.summon.Pull;
import util.rng.summon.SummonImageBuilder;

public class Summon implements Command {
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("FORBIDDEN", "Summon wrath evoked by "+event.getAuthorName()+(event.isPrivate()?"":" on "+event.getGuild()));
		event.getChannel().sendTyping();
		if(args.length>0&&Lib.isNumber(args[0])){
		return SpamControl.isSpam(event,"summon");
		}
		else{
			help(event);
			return false;
		}
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
			public void run(){
				try{
					int num=Integer.parseInt(args[0]);
					if(num>1800){//capped to 1800 units, beyond this it is close to Discord's 8MB file upload size cap
						num=1800;
					}
					Banner pullBanner=getBanner(args.length>1?(args[1]==null?"null":args[1]):"null");
					if(num==11){
						sendImage(event, Pull.pull11(pullBanner),pullBanner);
					}
					else{
						sendImage(event, Pull.pull(num,pullBanner),pullBanner);
					}
				}
				catch(Exception e){
					Log.logError(e);
				}
			}
		});
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
				+ "\n\tsummons [amount] units from the rare summon pool");
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	private Banner getBanner(String s){
		for(Banner b:Banner.values()){
			if(s.toLowerCase().equals(b.name.toLowerCase())||s.toLowerCase().equals(b.toString().toLowerCase())){
				return b;
			}
		}
		return Settings.DefaultBanner;
	}
	public void sendImage(MessageReceivedEvent event, ArrayList<SummonedUnit> units,Banner banner){
		ArrayList<UnitSpecific> us=new ArrayList<UnitSpecific>();
		for(SummonedUnit u:units){
			us.add(u.toSpecific());
		}
		Counter count=new Counter("Summoning Units...(%count%/"+units.size()+")",event);
		double factor=1;
		if(us.size()>100){
			factor=0.5;
		}
		BufferedImage build;
		if(us.size()<25){
			build=new SummonImageBuilder(factor).basePlate(3, "/Library/summon/6star.png").addUnit(us).build(event, count);
		}
		else{
			build=new SummonImageBuilder(factor).buildColumnsDynamically()
					.basePlate(3, "/Library/summon/6star.png").addUnit(us).build(event, count);
		}
		try{
			count.setMessage("Uploading...");
			ImageIO.write(build, "PNG", new File("summons.png"));
			event.getChannel().sendFile(new File("summons.png"),new MessageBuilder().appendString(
					Lib.FormatMessage(event, "%userMention% summoned "+units.size()+" units from the "+banner.name+" rare summon banner")).build());
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
