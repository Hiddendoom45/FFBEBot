package commands;

import java.util.List;

import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import global.record.Log;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.SpamControl;

public class GuaranteeSummon extends CommandGenerics implements Command {
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
				try{
					List<UnitSpecific> units=util.rng.summon.Pull.pull5base(1, Banner.Current);
					units.addAll(util.rng.summon.Pull.pull(10, Banner.Current));
					new Summon().sendImage(event, units,"Guarantee 5*");
				}catch(Exception e){
					Log.logError(e);
				}
			}
		});


	}

	@Override
	public void help(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
