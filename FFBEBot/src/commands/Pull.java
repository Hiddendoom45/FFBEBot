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
/**
 * Pull wherein the user pays in lapis currency to summon units for their unit inventory
 * @author Allen
 *
 */
public class Pull extends CommandGenerics implements Command,Selection {
	
	@Override
	/**
	 * default call, logs command using super method, checks to make sure proper elements are available
	 */
	public boolean called(String[] args, MessageReceivedEvent event) {
		super.called(args,event);
		if(args.length>0&&Lib.isNumber(args[0])){
			return SpamControl.isSpam(event, "summon");
		}
		else{
			help(event);
			return false;
		}
	}
	
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ArrayList<String> possible=new ArrayList<String>();//string of options, useless more or less
		String msgHead="A you sure you want to pull "+Integer.parseInt(args[0])+" times from the "+getBanner(args.length>1?args[1]:"")+" banner for "
				+(Integer.parseInt(args[0])*(Banner.LEBanner(getBanner(args.length>1?args[1]:"null"))?1000:500))+" lapis?";//logic for cost
		
		Select select=new Select(possible,System.currentTimeMillis(),this,possible,msgHead);//none of this is really even used..., why did I make the select interface this way?...
		select.additionalData=args;//arguments, so that it can get # of units and banner to summon from
		Selector.setSelection(select, event);//pass to selection for yes/no option
	}
	/**
	 * check to get the banner, returns the default banner as set by the settings if nothing is found
	 * @param s string value to check the banner for
	 * @return
	 */
	public static Banner getBanner(String s){
		for(Banner b:Banner.values()){
			if(s.toLowerCase().equals(b.name.toLowerCase())||s.toLowerCase().equals(b.toString().toLowerCase())){
				return b;
			}
		}
		return Settings.DefaultBanner;
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s="pull [amount] [banner]\n"
				+ "\tpulls the amount of units specified, units will go into unit inventory\n"
				+ "\t[amount] amount of units to pull(500 lapis per summon(1000 for non current LE banners))\n"
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
						Banner pullBanner=getBanner(chosen.additionalData.length>1?(chosen.additionalData[1]==null?"null":chosen.additionalData[1]):"null");
						int cost=0;
						if(Banner.LEBanner(pullBanner)){
							cost=1000*num;
						}
						else{
							cost=500*num;
						}
						if(cost>user.lapis){
							Lib.sendMessage(event, "You do not have enough lapis to summon "+num+" times");
						}
						else{
							List<UnitSpecific> units=util.rng.summon.Pull.pull(num,pullBanner);
							user.lapis-=cost;
							for(UnitSpecific u:units){
								user.units.add(new PullUnit(u.unit,u.rarity));
							}
							SaveSystem.setUser(user);
							new Summon().sendImage(event, units,pullBanner.name);
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
