package commands.overide;

import java.util.HashMap;

import commands.*;
import global.record.PostgresDB;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;

public class DumpAnalytics extends OverrideGenerics{

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event){
		String s ="";
		if(args.containsKey("c")){
			for(String a:args.get("c")){
				s+=PostgresDB.grepClass(a);
			}
		}
		else if(args.containsKey("d")){
			PostgresDB.purgeCommandUse();
			s+="all entries deleted";
		}
		else{
			s+=PostgresDB.grepClass(Awaken.class.getSimpleName());
			s+=PostgresDB.grepClass(Banners.class.getSimpleName());
			s+=PostgresDB.grepClass(BugReport.class.getSimpleName());
			s+=PostgresDB.grepClass(Chance.class.getSimpleName());
			s+=PostgresDB.grepClass(Dailies.class.getSimpleName());
			s+=PostgresDB.grepClass(DailyPull.class.getSimpleName());
			s+=PostgresDB.grepClass(ElevenPull.class.getSimpleName());
			s+=PostgresDB.grepClass(Emote.class.getSimpleName());
			s+=PostgresDB.grepClass(Equipment.class.getSimpleName());
			s+=PostgresDB.grepClass(Flair.class.getSimpleName());
			s+=PostgresDB.grepClass(GuaranteeSummon.class.getSimpleName());
			s+=PostgresDB.grepClass(Hax.class.getSimpleName());
			s+=PostgresDB.grepClass(Husbando.class.getSimpleName());
			s+=PostgresDB.grepClass(Info.class.getSimpleName());
			s+=PostgresDB.grepClass(Invite.class.getSimpleName());
			s+=PostgresDB.grepClass(Lapis.class.getSimpleName());
			s+=PostgresDB.grepClass(Lore.class.getSimpleName());
			s+=PostgresDB.grepClass(Maintenance.class.getSimpleName());
			s+=PostgresDB.grepClass(Nier.class.getSimpleName());
			s+=PostgresDB.grepClass(Ping.class.getSimpleName());
			s+=PostgresDB.grepClass(Pull.class.getSimpleName());
			s+=PostgresDB.grepClass(RAwaken.class.getSimpleName());
			s+=PostgresDB.grepClass(REquipment.class.getSimpleName());
			s+=PostgresDB.grepClass(RSkill.class.getSimpleName());
			s+=PostgresDB.grepClass(RUnits.class.getSimpleName());
			s+=PostgresDB.grepClass(SacredCrystal.class.getSimpleName());
			s+=PostgresDB.grepClass(Salty.class.getSimpleName());
			s+=PostgresDB.grepClass(Servers.class.getSimpleName());
			s+=PostgresDB.grepClass(Sieghart.class.getSimpleName());
			s+=PostgresDB.grepClass(Skill.class.getSimpleName());
			s+=PostgresDB.grepClass(Summon.class.getSimpleName());
			s+=PostgresDB.grepClass(UnitArt.class.getSimpleName());
			s+=PostgresDB.grepClass(UnitAwaken.class.getSimpleName());
			s+=PostgresDB.grepClass(UnitInventory.class.getSimpleName());
			s+=PostgresDB.grepClass(Units.class.getSimpleName());
			s+=PostgresDB.grepClass(UnitSell.class.getSimpleName());
			s+=PostgresDB.grepClass(Waifu.class.getSimpleName());
		}
		Lib.sendMessage(event, s);
	}

	@Override
	public void help(MessageReceivedEvent event){
		String s ="-c className";
		Lib.sendMessage(event, s);
	}

}
