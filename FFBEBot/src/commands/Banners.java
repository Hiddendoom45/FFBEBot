package commands;

import java.util.ArrayList;

import Library.summon.banner.Banner;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
/**
 * Gets a list of banners and how to summon from them
 * @author Allen
 *
 */
public class Banners extends CommandGenerics implements Command, Selection {

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		
	}

	@Override
	public int getInputType() {
		return 0;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0){
			
		}
		else{
			listBanners(event);
		}

	}
	public void listBanners(MessageReceivedEvent event){
		ArrayList<String> names=new ArrayList<String>();
		for(Banner b:Banner.values()){
			names.add(b.name);
		}
		Select select=new Select(names, System.currentTimeMillis(), this, names, "Choose the banner you want to view more information about:");
		Selector.setSelection(select, event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s="banners [banner]\n"
				+ "\tif no arguments, lists all banners you can summon from\n"
				+ "\t[banner] specify a specific banner you want to find more info about";
		Lib.sendMessage(event, s);

	}

}
