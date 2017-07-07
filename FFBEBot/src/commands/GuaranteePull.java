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

public class GuaranteePull extends CommandGenerics implements Command,Selection {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		if(user.base5guarantee){
			String s="%userName%, you have already used your guarantee 5* base summon";
			Lib.sendMessageFormated(event, s);
			return;
		}
		ArrayList<String> possible=new ArrayList<String>();//string of options, useless more or less
		String msgHead="A you sure you want do a guarantee 5* 10+1 pull for 5000 lapis?";
		Select select=new Select(possible,System.currentTimeMillis(),this,possible,msgHead);//none of this is really even used..., why did I make the select interface this way?...
		select.additionalData=args;//arguments, so that it can get # of units and banner to summon from
		Selector.setSelection(select, event);//pass to selection for yes/no option

	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"5pull\n"
				+ "\tdoes a 10+1 pull with guarantee 5* on +1(likely limited for events)";
		Lib.sendMessage(event, s);
	}

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		if(chosen.selected==0){
			Settings.executor.execute(new Runnable(){//execute in new thread so that long summon commands don't lock everything else
				public void run(){
					try{
						Data user=SaveSystem.getUser(event.getAuthor().getId());
						int cost=5000;
						if(cost>user.lapis){
							Lib.sendMessageEmoted(event, "You do not have enough %lapis% to do the guarantee 5* pull");
						}
						else{
							List<UnitSpecific> units=util.rng.summon.Pull.pull5base(1,Banner.Current);
							units.addAll(util.rng.summon.Pull.pull(10, Banner.Current));
							user.lapis-=cost;
							for(UnitSpecific u:units){
								user.units.add(new PullUnit(u.unit,u.rarity));
							}
							user.base5guarantee=true;
							SaveSystem.setUser(user);
							new Summon().sendImage(event, units,"Guarantee 5*");
							Lib.sendMessageEmoted(event, "You have "+user.lapis+" %lapis% left");
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
		return Selector.YNType;
	}

}
