package commands;

import java.util.ArrayList;

import Library.pulls.PullUnit;
import Library.summon.SummonedUnit;
import Library.summon.banner.Banner;
import global.record.Data;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;

public class ElevenPull extends CommandGenerics implements Command, Selection {

	
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ArrayList<String> possible=new ArrayList<String>();//string of options, useless more or less
		String msgHead="A you sure you want do "+Integer.parseInt(args[0])+" 10+1 pulls from the "+Pull.getBanner(args.length>1?args[1]:"")+" banner?";
		Select select=new Select(possible,System.currentTimeMillis(),this,possible,msgHead);//none of this is really even used..., why did I make the select interface this way?...
		select.additionalData=args;//arguments, so that it can get # of units and banner to summon from
		Selector.setSelection(select, event);//pass to selection for yes/no option
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="11pull [amount] [banner]\n"
				+ "\tpulls the amount of units specified, units will go into unit inventory\n"
				+ "\t[amount] amount of units to pull(5000 lapis per 11pull(10000 for non current LE banners))\n"
				+ "\t[banner] banner to pull from, if not specified will pull from most recent banner";
		Lib.sendMessage(event, s);

	}
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		if(chosen.selected==0){
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					try{
						int num=Integer.parseInt(chosen.additionalData[0]);
						Data user=SaveSystem.getUser(event.getAuthor().getId());
						if(num>1800){//capped to 1800 units, beyond this it is close to Discord's 8MB file upload size cap
							num=1800;
						}
						Banner pullBanner=Pull.getBanner(chosen.additionalData.length>1?(chosen.additionalData[1]==null?"null":chosen.additionalData[1]):"null");
						int cost=0;
						if(Banner.LEBanner(pullBanner)){
							cost=10000*num;
						}
						else{
							cost=5000*num;
						}
						if(cost>user.lapis){
							Lib.sendMessage(event, "You do not have enough lapis do "+num+" 10+1 pulls");
						}
						else{
							ArrayList<SummonedUnit> units=util.rng.summon.Pull.pull11(num,pullBanner);
							user.lapis-=cost;
							for(SummonedUnit u:units){
								user.units.add(new PullUnit(u.unit,u.rarity));
							}
							SaveSystem.setUser(user);
							new Summon().sendImage(event, units,pullBanner);
							Lib.sendMessage(event, "You have "+user.lapis+" lapis left");
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
