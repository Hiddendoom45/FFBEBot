package commands;

import java.io.IOException;

import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.unit.UnitInfo;
import util.unit.UnitOverview;
import util.unit.exvius.AwakenInfo.AwakenMat;
import util.Lib;

public class Awaken extends UnitSelection {
	private void sendAwakening(UnitInfo info,int rarity,MessageReceivedEvent event){
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Awakening mats for "+info.unitName);
		if(rarity==0){
			for(int i=0;i<info.awakening.length;i++){
				String s = "";
				for(AwakenMat a:info.awakening[i].mats){
					s+=(a.nonMatch()?"":formatEmote(a.isPrism()?"Unit Prism":a.name))+a.toString()+"\n";
				}
				s = Lib.EmoteMessage(event, s);
				embed.addField((info.minRarity+i)+"★",s,true);
			}
		}
		else{
			String s = "";
			for(AwakenMat a:info.awakening[rarity-info.minRarity].mats){
				s+=(a.nonMatch()?"":formatEmote(a.isPrism()?"Unit Prism":a.name))+a.toString()+"\n";
			}
			s = Lib.EmoteMessage(event, s);
			embed.addField(rarity+"★", s, true);
		}
		Lib.sendEmbed(event, embed);
	}
	private String formatEmote(String name){
		StringBuilder b = new StringBuilder();
		b.append('%');
		boolean space = false;
		for(char c:name.toLowerCase().toCharArray()){
			if(Character.isAlphabetic(c)||c==' '){
				if(c==' '){
					space = true;
				}
				else if(space){
					b.append(Character.toUpperCase(c));
					space = false;
				}
				else{
					b.append(c);
				}
			}
		}
		b.append('%');
		return b.toString();
	}
	@Override 
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("Status", "Awakening mats "+(args.length>0?"for "+Lib.extract(args):"")+" by "+event.getAuthor().getName()+(event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild()));
		return super.called(args, event);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		if(Ounit.getData(0).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(0).name+" has not been created yet");
		}
		else{
			sendAwakening(SaveSystem.getExviusUnit(Ounit.getData(0).name),rarity,event);
		}
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		if(Ounit.getData(selection).isNew){
			Lib.sendMessage(event, "The wiki page for "+Ounit.getData(selection).name+" has not been created yet");
		}
		else{
			sendAwakening(SaveSystem.getExviusUnit(Ounit.getData(selection).name),rarity,event);
		}
		
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s=SaveSystem.getPrefix(event)+"awaken [unitname] [rarity]\n"
				+ "\tgets the awakening mats for a unit\n"
				+ "\t[unitname] unit to get the awakening mats for(doesn't have to be the full name)\n"
				+ "\t[rarity] mats needed to awaken to from [rarity] to next rarity";
		Lib.sendMessage(event, s);
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

}
