package commands;

import java.util.ArrayList;

import Library.summon.Unit;
import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.SpamControl;
import util.rng.summon.Pull;

public class Nier extends CommandGenerics implements Command{
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		if(super.called(args, event)){
			
		}
		return SpamControl.isSpam(event, "summon");
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					Banner pullBanner = Banner.Nier;
					try{
						pullBanner=Banner.Nier;
						int num=Integer.parseInt(args[0]);
						if(num>1000){//capped to 1800 units, beyond this it is close to Discord's 8MB file upload size cap//adjusted to lower
							num=1000;
						}
						if(num==11){
							new Summon().sendImage(event, Pull.pull11(pullBanner),pullBanner.name);
						}
						else if(num==0){
							ArrayList<UnitSpecific> units=new ArrayList<UnitSpecific>();
							units.add(new UnitSpecific(Unit.Bedile, 3));
							new Summon().sendImage(event,units,pullBanner.name);
						}
						else{
							new Summon().sendImage(event, Pull.pull(num,pullBanner),pullBanner.name);
						}
					}
					catch(Exception e){
						new Summon().sendImage(event, Pull.pull11(pullBanner), pullBanner.name);
					}
				}
			});
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, SaveSystem.getPrefix(event)+"summon [amount]"
				+ "\n\tsummons [amount] units from the rare summon pool");
		
	}
}
