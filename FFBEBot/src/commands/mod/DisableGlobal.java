package commands.mod;

import java.util.ArrayList;

import Library.ModuleEnum;
import commands.Command;
import global.record.ModuleController;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;

public class DisableGlobal extends ModGenerics implements Command,Selection {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ArrayList<String> values=new ArrayList<String>();
		for(ModuleEnum m:ModuleEnum.values()){
			values.add(m.toString());
		}
		Select select=new Select(values, System.currentTimeMillis(), this, values, "Select Which Module you want to disable globally:");
		Selector.setSelection(select, event);
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"gldisable\n"
				+ "\tdisable/enable a module across all channels\n"
				+ "\ta module may be renabled/disabled induvidally in a specific channel using `"+SaveSystem.getModPrefix(event)+"disable`";
		Lib.sendMessage(event, s);
	}

	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		Settings guild=SaveSystem.getGuild(event.getGuild().getId());
		String module=chosen.options.get(chosen.selected).toString();
		if(guild.disabled.containsKey(module)){
			guild.disabled.get(module).toggleGlobalDisable();
		}
		else{
			guild.disabled.put(module, new ModuleController(module).toggleGlobalDisable());
		}
		SaveSystem.setSetting(guild);
		SaveSystem.loadGuilds();
	}

	@Override
	public int getInputType() {
		return 0;
	}

}
