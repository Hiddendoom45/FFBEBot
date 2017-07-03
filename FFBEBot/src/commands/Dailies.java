package commands;

import java.util.concurrent.TimeUnit;

import global.record.Data;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Dailies extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Data user=SaveSystem.getUser(event.getAuthor().getId());
		if(user.dailyReady()){
			user.lapis+=500;
			user.dailyTime=System.currentTimeMillis();
			SaveSystem.setUser(user);
			Lib.sendMessageWithSpecials(event, "**%userName%** you have recieved your daily 500 %lapis%");
		}
		else{
			long diff=Settings.dailyTime+86400000-System.currentTimeMillis();
			int hours=(int) TimeUnit.MILLISECONDS.toHours(diff)%24;
			int minutes=(int) TimeUnit.MILLISECONDS.toMinutes(diff)%60;
			int seconds=(int) TimeUnit.MILLISECONDS.toSeconds(diff)%60;
			Lib.sendMessageWithSpecials(event, "**%userName%** your daily %lapis% refreshes in "+(hours==0?"":hours+" hours ")+(minutes==0?"":minutes+" minutes ")+(seconds==0?"":seconds+" seconds"));
		}
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="dailies\n"
				+ "\tgives you your daily 500 lapis";
		Lib.sendMessage(event, s);
		
	}

}
