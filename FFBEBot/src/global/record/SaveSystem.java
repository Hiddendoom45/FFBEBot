package global.record;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import XML.Attribute;
import XML.XMLStAXFile;
import XML.Elements;
import global.Main;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class SaveSystem {
	public static void setup(){
		if(!new File(Settings.dataSource).exists()){
			List<Guild> guilds=Main.jda.getGuilds();
			Elements root=new Elements("FFBEBot");
			for(Guild g:guilds){
				root.add(new Settings(g.getId()).parseToElements());
			}
			Elements override=new Elements("override");
			root.add(override);
			XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
			file.writeXMLFile();
			file.startWriter();
			file.writeElement(root);
			file.endWriter();
		}
		load();
	}
	public static void load(){
		Settings.guilds.clear();
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		ArrayList<Elements> guilds=file.parseToElements("guild");
		for(Elements e:guilds){
			Settings.guilds.put(e.getAttribute("id").getValue(), new Settings(e));
		}
		file.endReader();
	}
	public static Settings getGuild(String id){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements guild=file.parseToElements(new Attribute("id",id)).get(0);
		file.endReader();
		return new Settings(guild);
	}
	public static String getSetting(String id,String tag){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements guild=file.parseToElements(new Attribute("id",id)).get(0);
		String s= guild.getChilds(tag).get(0).getText();
		file.endReader();
		return s;
	}
	public static void setSetting(Settings guild){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("guild")){
				if(doc.getChilds().get(i).getAttribute("id").getValue().equals(guild.id)){
					doc.getChilds().remove(i);
				}
			}
		}
		doc.getChilds().add(guild.parseToElements());
		file.writeXMLFile();
		file.startWriter();
		file.writeElement(doc);
		file.endWriter();
	}
	public static String getJoin(GuildMemberJoinEvent event){
		return getJoin(event.getGuild());
	}
	public static String getJoin(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String join=Settings.guilds.get(guild.getId()).joinMsg;
			if(join.equals("")){
				return Settings.join;
			}
			else{
				return join;
			}
		}
		else{
			return Settings.join;
		}
	}
	public static String getPrefix(MessageReceivedEvent event){
		if(event.isPrivate()){
			return Settings.join;
		}
		else{
			return getPrefix(event.getGuild());
		}
	}
	public static String getPrefix(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String prefix=Settings.guilds.get(guild.getId()).guildPrefix;
			if(prefix.equals("")){
				return Settings.prefix;
			}
			else{
				return prefix;
			}
		}
		else{
			return Settings.prefix;
		}
		
	}
	public static String getModPrefix(MessageReceivedEvent event){
		if(event.isPrivate()){
			return Settings.modPrefix;
		}
		else{
			return getModPrefix(event.getGuild());
		}
	}
	public static String getModPrefix(Guild guild){
		if(Settings.guilds.containsKey(guild.getId())){
			String modPrefix=Settings.guilds.get(guild.getId()).guildModPrefix;
			if(modPrefix.equals("")){
				return Settings.modPrefix;
			}
			else{
				return modPrefix;
			}
		}
		else{
			return Settings.modPrefix;
		}
	}
	public static String[] getOverrides() {
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		String[] overrides = null;
		try{
			overrides=file.parseToElements("override").get(0).getText().split(",");
		}catch(Exception e){};
		return overrides;
	}
	public static void removeOverride(String overide){
		XMLStAXFile file=new XMLStAXFile(new File(Settings.dataSource));
		file.readXMLFile();
		Elements doc=file.parseDocToElements();
		file.endReader();
		String[] overrides=SaveSystem.getOverrides();
		String out="";
		for(String s:overrides){
			if(!s.equals(overide)){
				out+=s+",";
			}
		}
		out=out.substring(0, out.length()>=1?out.length()-1:0);
		for(int i=0;i<doc.getChilds().size();i++){
			if(doc.getChilds().get(i).getTagName().equals("override")){
				doc.getChilds().get(i).setText(out);
			}
		}
		file.writeXMLFile();
		file.startWriter();
		file.writeElement(doc);
		file.endWriter();
	}
}
