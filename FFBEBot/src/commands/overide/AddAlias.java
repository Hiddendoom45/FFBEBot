package commands.overide;

import java.util.HashMap;

import global.record.Log;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.unit.UnitAlias;

public class AddAlias extends OverrideGenerics{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event){
		try{
			if(args.containsKey("id")){
				int id = Integer.parseInt(args.get("id")[0]);
				if(args.containsKey("list")){
					Lib.sendMessage(event, UnitAlias.aliases.get(id).toString());
				}
				else if(args.containsKey("name")){
					String name = Lib.extract(args.get("name"));
					if(args.containsKey("add")){
						UnitAlias.aliases.get(id).addAlias(name);
						Lib.sendMessage(event, "Alias added");
					}
					else if(args.containsKey("remove")){
						UnitAlias.aliases.get(id).addAlias(name);
						Lib.sendMessage(event, "Alias removed");
					}
					if(!args.containsKey("noserialize")){
						UnitAlias.serialize();
						DriveManager.update(new DriveFile(UnitAlias.saveLoc.getName(),DataEnum.AliasJSON.id));
					}
				}
			}
		}catch(Exception e){
			Log.logError(e);
		}
	}

	@Override
	public void help(MessageReceivedEvent event){
		Lib.sendMessage(event, "-id [#] -name [alias] -add | -remove | -list | -noserialize");
	}

}
