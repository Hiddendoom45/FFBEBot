package commands;

import global.record.Data;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class Give extends CommandGenerics implements Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(event.getMessage().getMentionedUsers().size()>0&&args.length>0&&Lib.isNumber(args[0])){
			int lapis=0;
			try{
				lapis=Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				Lib.sendMessage(event, "Error:NaN");
				return;
			}
			Data reciever=SaveSystem.getUser(event.getMessage().getMentionedUsers().get(0).getId());
			Data giver=SaveSystem.getUser(event.getAuthor().getId());
			if(giver.lapis>=lapis){
				reciever.lapis+=lapis;
				giver.lapis-=lapis;
				SaveSystem.setUser(giver);
				SaveSystem.setUser(reciever);
				Lib.sendMessageEmoted(event, "Sent "+lapis+" %lapis% to "+event.getMessage().getMentionedUsers().get(0).getName());
			}
			else{
				Lib.sendMessageEmoted(event, "You do not have "+lapis+" %lapis% to send");
			}
		}
		else{
			help(event);
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="give [lapis] [@user]\n"
				+ "\t gives lapis to a user\n"
				+ "\t[lapis] amount of %lapis% to give\n"
				+ "\t[@user] user to give the %lapis% to";
		Lib.sendMessageEmoted(event, s);
	}
	
}
