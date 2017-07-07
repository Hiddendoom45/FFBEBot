package commands;

import java.util.ArrayList;
import java.util.List;

import Library.pulls.PullUnit;
import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;
import global.record.Data;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.SpamControl;

public class ElevenPull extends CommandGenerics implements Command, Selection {

	@Override
	/**
	 * default call, logs command using super method, checks to make sure proper elements are available
	 */
	public boolean called(String[] args, MessageReceivedEvent event) {
		super.called(args,event);
		return SpamControl.isSpam(event, "summon");
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ArrayList<String> possible=new ArrayList<String>();//string of options, useless more or less
		String msgHead="A you sure you want do a 10+1 pulls from the "+Pull.getBanner(args.length>0?args[0]:"")+" banner?";
		Select select=new Select(possible,System.currentTimeMillis(),this,possible,msgHead);//none of this is really even used..., why did I make the select interface this way?...
		select.additionalData=args;//arguments, so that it can get # of units and banner to summon from
		Selector.setSelection(select, event);//pass to selection for yes/no option
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="11pull [banner]\n"
				+ "\tpulls 10+1 units(normal costs 5000 %lapis%, LE banners cost 10000 %lapis%), units will go into unit inventory\n"
				+ "\t[banner] banner to pull from, if not specified will pull from most recent banner";
		Lib.sendMessageEmoted(event, s);

	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		if(chosen.selected==0){
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					try{
						Data user=SaveSystem.getUser(event.getAuthor().getId());
						Banner pullBanner=Pull.getBanner(chosen.additionalData.length>1?(chosen.additionalData[1]==null?"null":chosen.additionalData[1]):"null");
						int cost=0;
						if(Banner.LEBanner(pullBanner)){
							cost=10000;
						}
						else{
							cost=5000;
						}
						if(cost>user.lapis){
							Lib.sendMessageEmoted(event, "You do not have enough %lapis% do a 10+1 pulls");
						}
						else{
							List<UnitSpecific> units=util.rng.summon.Pull.pull11(1,pullBanner);
							user.lapis-=cost;
							for(UnitSpecific u:units){
								user.units.add(new PullUnit(u.unit,u.rarity));
							}
							SaveSystem.setUser(user);
							new Summon().sendImage(event, units,pullBanner.name);
							Lib.sendMessage(event, "You have "+user.lapis+" %lapis% left");
						}
					}
					catch(Exception e){
						Log.logError(e);
					}
				}
			});
		}
	}

	@Override
	public int getInputType() {
		return 3;
	}

}
