package commands;


import global.Main;
import global.record.Secrets;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
//to get user feedback on what's going wrong
public class BugReport extends CommandGenerics implements Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(Settings.blacklist.contains(event.getAuthor().getId())){
			return;
		}
		if(args.length>0){
			String s="";
			s+=Lib.debugGuildUser(event)+"\n"+Lib.extract(args);
			Main.jda.getGuildById(Secrets.testServerID).getTextChannelById(Secrets.bugChannelID).sendMessage(s).complete();
			Lib.sendMessage(event, "Message sent to bug reporting channel");
		}
		else{
			help(event);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="bugreport [info]\n"
				+ "[info] information about the bug that occured\n"
				+ "please provide info on what command was used and what you typed in etc";
		Lib.sendMessage(event, s);
	}

}
