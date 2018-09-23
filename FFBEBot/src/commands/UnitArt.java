package commands;

import java.io.IOException;
import java.util.ArrayList;

import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.MessageEmbed.ImageInfo;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Selection;
import util.unit.UnitInfo;
import util.unit.UnitOverview;

public class UnitArt extends UnitSelection implements Command,Selection  {
	public static void sendImage(UnitOverview Ounits, int rarity,MessageReceivedEvent event,int index){
		if(rarity==0){
			MessageEmbedImpl embed=new MessageEmbedImpl();
			String url = Ounits.getData(index).imgUrl;
			embed.setImage(new ImageInfo(url, url, 500, 500));
			ArrayList<Field> fields = new ArrayList<Field>();
			fields.add(new Field(":art:"+Ounits.getNames().get(index)+" "+Lib.extractNumber(Ounits.getData(index).maxR)+"★", "[link to image]("+url+")", false));
			System.out.println(Ounits.getData(index).maxR+" "+Ounits.getData(index).baseR);
			embed.setFields(fields);
			event.getChannel().sendMessage(embed).complete();
		}
		else{
			UnitInfo info;
			try {
				info = new UnitInfo(Ounits.getData(index).unitUrl);
				MessageEmbedImpl embed=new MessageEmbedImpl();
				String url = (rarity<=info.maxRarity&&rarity>=info.minRarity?info.sprites[rarity-info.minRarity]:"https://d1u5p3l4wpay3k.cloudfront.net/exvius_gamepedia_en/b/b9/Icon-Crimson_Gem.png?version=20c46d237943d81039478b494150c0d8");
				System.out.println(url);
				embed.setImage(new ImageInfo(url, url, 500, 500));
				ArrayList<Field> fields = new ArrayList<Field>();
				fields.add(new Field(":art:"+info.unitName+" "+rarity+"★", "[link to image]("+url+")", false));
				embed.setFields(fields);
				event.getChannel().sendMessage(embed).complete();
			} catch (IOException e) {
				Log.logError(e);
			}
			
		}
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String msg=SaveSystem.getPrefix(event)+"unitart [unitname] [unitrarity]\n"
				+ "\tDisplay image of unit\n"
				+ "\t[unitname] unit you want the art of(doesn't have to be the full name)\n"
				+ "\t[unitrarity] rarity you want the art for";
		Lib.sendMessage(event, msg);
	}
	@Override
	public void onePossible(UnitOverview Ounit, int rarity, MessageReceivedEvent event) throws IOException {
		sendImage(Ounit,rarity,event,0);
		
	}


	@Override
	public void manyPossible(UnitOverview Ounit, int selection, int rarity, MessageReceivedEvent event)
			throws IOException {
		sendImage(Ounit,rarity,event,selection);
		
	}

}
