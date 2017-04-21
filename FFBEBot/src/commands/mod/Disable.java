package commands.mod;

import java.util.ArrayList;

import Library.ModuleEnum;
import commands.Command;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.ModuleController;
import util.Select;
import util.Selection;
import util.Selector;

public class Disable extends ModGenerics implements Selection, Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ArrayList<String> values=new ArrayList<String>();
		for(ModuleEnum mod:ModuleEnum.values()){
			values.add(mod.toString());
		}
		ArrayList<String> names=new ArrayList<String>();
		for(String s:values){
			names.add(s+"["+(CommandEnabled(event,s)?"enabled":"disabled")+"]");
		}
		Select select=new Select(values, System.currentTimeMillis(), this, names, "Select which module you would like to disable/enable in this channel [current status]:");
		Selector.setSelection(select, event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getModPrefix(event)+"disable\n"
				+ "\tdisables a module in the current channel\n"
				+ "\tto disable a module across all channels use `"+SaveSystem.getModPrefix(event)+"disable`";
		Lib.sendMessage(event, s);
	}

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		Settings guild=SaveSystem.getGuild(event.getGuild().getId());
		String module=chosen.options.get(chosen.selected);
		if(guild.disabled.containsKey(module)){
			guild.disabled.get(module).toggle(event.getChannel().getId());
		}
		else{
			guild.disabled.put(module, new ModuleController(module).toggle(event.getChannel().getId()));
		}
		SaveSystem.setSetting(guild);
		SaveSystem.loadGuilds();
		Lib.sendMessage(event, module+" "+(CommandEnabled(event,module)?"enabled":"disabled")+" on the current channel");
	}

	@Override
	public int getInputType() {
		return 0;
	}
	private static boolean CommandEnabled(MessageReceivedEvent event, String module){
		if(event.getChannelType()==ChannelType.PRIVATE)return true;
		Settings guild=SaveSystem.getGuild(event.getGuild().getId());
		ModuleController ctrl=guild.disabled.get(module);
		if(ctrl==null)return true;
		return ctrl.enabled(event.getChannel().getId());
	}

}
