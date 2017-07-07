package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class DailyPull extends CommandGenerics implements Command,Selection {
	public Banner DailyBanner=Banner.Current;
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		if(user.dailyPullReady()&&user.lapis>=250){
			ArrayList<String> possible=new ArrayList<String>();//string of options, useless more or less
			String msgHead="A you sure you want to do a daily pull from the "+DailyBanner+" banner?";
			Select select=new Select(possible,System.currentTimeMillis(),this,possible,msgHead);//none of this is really even used..., why did I make the select interface this way?...
			select.additionalData=args;//arguments, so that it can get # of units and banner to summon from
			Selector.setSelection(select, event);//pass to selection for yes/no option
		}
		else if(user.lapis<250){
			Lib.sendMessageWithSpecials(event, "**%userName%** you do not have enough %lapis% to do a daily pull");
		}
		else{
			long diff=Settings.dailyTime+86400000-System.currentTimeMillis();
			int hours=(int) TimeUnit.MILLISECONDS.toHours(diff)%24;
			int minutes=(int) TimeUnit.MILLISECONDS.toMinutes(diff)%60;
			int seconds=(int) TimeUnit.MILLISECONDS.toSeconds(diff)%60;
			Lib.sendMessageFormated(event, "**%userName%** your daily pull refreshes in "+(hours==0?"":hours+" hours ")+(minutes==0?"":minutes+" minutes ")+(seconds==0?"":seconds+" seconds"));
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="dailypull\n"
				+ "\tpulls one unit from the current daily banner, units will go into unit inventory\n";
		Lib.sendMessage(event, s);
	}

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		if(chosen.selected==0){
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					try{
						int num=1;
						Data user=SaveSystem.getUser(event.getAuthor().getId());
						Banner pullBanner=Settings.DefaultBanner;//use current default for dailies to lock it in there
						int cost=250;
						if(cost>user.lapis){
							Lib.sendMessageEmoted(event, "You do not have enough %lapis% to summon "+num+" times");
						}
						else{
							List<UnitSpecific> units=util.rng.summon.Pull.pull(num,pullBanner);
							user.lapis-=cost;
							for(UnitSpecific u:units){
								user.units.add(new PullUnit(u.unit,u.rarity));
							}
							SaveSystem.setUser(user);
							new Summon().sendImage(event, units,pullBanner.name);
							Lib.sendMessageEmoted(event, "You have "+user.lapis+" %lapis% left");
						}
						user.dailyPullTime=System.currentTimeMillis();
						SaveSystem.setUser(user);
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
