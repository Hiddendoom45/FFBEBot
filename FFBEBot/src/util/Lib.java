package util;

import java.util.Arrays;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Lib.ElementFilter;
import global.record.SaveSystem;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
/**
 * Library of various useful methods that are used all over the place
 * @author Allen
 *
 */
public class Lib {
	public static void printHelp(MessageReceivedEvent event){
		String msg="__***Help List***__\n"
				+ "Use "+SaveSystem.getPrefix(event)+"help [command] to get more info on a specific command, i.e.: "+SaveSystem.getPrefix(event)+"help ping\n\n"
				+ "__**Modules**__\n"
				+ "**Core** - `ping` `invite`\n"
				+ "Core commands for bot\n\n"
				+ "**Exvicus** - `awaken` `equipment` `lore` `skill` `unitart` `unit`\n"
				+ "Commands to extract info from Exvicus wiki(best for GL players)\n\n"
				+ "**Reddit** - `rawaken` `requipment` `rskill` `runit`\n"
				+ "Commands to extract info from Reddit wiki(best for JP players or GL players looking for future info)\n\n"
				+ "**Salt** - `summon` `salty` `waifu`\n"
				+ "Random commands based on chance\n\n"
				+ "**WIP** - `whale` `banner` `11pull` `pull` `unitinventory`\n"
				+ "Commands that are work in progress currently unimplemented\n\n"
				+ "Don't include the example brackets when using commands!\n"
				+ "To view mod commands, use "+SaveSystem.getModPrefix(event)+"help";
		Lib.sendMessage(event, msg);
	}
	public static void printModHelp(MessageReceivedEvent event){
		String msg="Mod Help List\n"
				+ SaveSystem.getModPrefix(event)+"join [MSG]"
				+ "\t`sets a join message for the server, leave blank for no message`\n"
				+ SaveSystem.getModPrefix(event)+"prefix [prefix]"
				+ "\t`changes the prefix used for the bot's commands`\n"
				+ SaveSystem.getModPrefix(event)+"sleep [timeout]"
				+ "\t`temporariy disable bot`\n"
				+ SaveSystem.getModPrefix(event)+"toggle"
				+ "\t`toggles various toggleable server options`";
		Lib.sendMessage(event, msg);
	}
	public static Message sendMessageFormated(MessageReceivedEvent event,String msg){
		return event.getChannel().sendMessage(Lib.FormatMessage(event,msg));
	}
	public static Message sendMessage(MessageReceivedEvent event,String msg){
		return event.getChannel().sendMessage(msg);
	}
	public static String FormatMessage(MessageReceivedEvent event,String msg){
		return msg.replace("%userMention%", event.getAuthor().getAsMention()).
				replace("%userName%", event.getAuthorName()).
				replace("%selfMention%", event.getJDA().getSelfInfo().getAsMention());
	}
	public static Message sendMessageFormated(GuildMemberJoinEvent event,String msg){
		return event.getGuild().getPublicChannel().sendMessage(Lib.FormatMessage(event,msg));
	}
	public static String FormatMessage(GuildMemberJoinEvent event,String msg){
		return msg.replace("%userMention%", event.getUser().getAsMention()).
		replace("%userName%", event.getUser().getUsername()).
		replace("%guildName%",event.getGuild().getName());
	}
	public static Elements getNestedDeep(Elements element,String[] tags){
		Elements ele=element;
		for(String s:tags){
			ele=Lib.getNested(ele, s);
		}
		return ele;
	}
	public static Elements getNested(Elements element, String tag){
		Elements ele=new Elements();
		for(Element eles:element){
			ele.addAll(eles.getElementsByTag(tag));
		}
		return ele;
	}
	public static Elements getNestedItem(Elements element,int index,String tag){
		Elements ele=new Elements();
		for(Element eles:element){
			try{
				ele.add(eles.getElementsByTag(tag).get(index));
			}catch(Exception e){};
		}
		return ele;
	}
	public static Elements getElesAfter(Elements elements, ElementFilter after){
		Elements afters=new Elements();
		boolean found=false;
		for(Element e:elements){
			if(found){
				afters.add(e);
				found=false;
			}
			else{
				if(after.filtered(e)){
					found=true;
				}
			}
		}
		return afters;
	}
	public static Element getEleAfter(Elements elements,ElementFilter after){
		Elements e=getElesAfter(elements,after);
		if(e.size()>0){
			return e.first();
		}
		return null;
		
	}
	public static int getTRCount(Element table){
		Elements tr=table.getElementsByTag("tr");
		return tr.size();
	}
	public static int getTDCount(Element table,int row){
		Elements td=table.getElementsByTag("tr").get(row).getElementsByTag("td");
		return td.size();
	}
	public static Element getCell(int row, int col,Element table){
		return table.getElementsByTag("tr").get(row).getElementsByTag("td").size()>0? 
				table.getElementsByTag("tr").get(row).getElementsByTag("td").get(col):null;
	}
	public static Element getHeader(int row, Element table){
		return table.getElementsByTag("tr").get(row).getElementsByTag("th").get(0);
	}
	public static String getLink(Element ele){
		Elements links = ele.getElementsByTag("a");
		for (Element link : links) {
			return link.attr("abs:href");
		}
		return "";
	}
	public static String pad(String s, int pad){
		for(int i=s.length();i<pad;i++){
			s=s+" ";
		}
		return s;
	}
	/**
	 * if s trimmed and split is part of Character.isDigit 
	 * @param s string to check if it's a number
	 * @return if s is all numbers or not
	 */
	public static boolean isNumber(String s){
		for(char c:s.trim().toCharArray()){
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
	}
	/**
	 * gets only number chars from a string
	 * @param s string to extract number from
	 * @return number with non digit characters removed
	 */
	public static int extractNumber(String s){
		String i="";
		for(char c:s.trim().toCharArray()){
			if(Character.isDigit(c)){
				i+=c;
			}
		}
		return Integer.parseInt(i);
	}
	public static int[] extractNumbers(String s){
		String i="";
		boolean number=false;
		for(char c:s.trim().toCharArray()){
			if(number){
				if(Character.isDigit(c)){
					i+=c;
				}
				else{
					i+=",";
					number=false;
				}
			}
			else{
				if(Character.isDigit(c)){
					i+=c;
					number=true;
				}
			}
		}
		if(i.endsWith(",")){
			i=i.substring(0, i.length()-1);
		}
		String[] nums=i.split(",");
		int[] ints=new int[nums.length];
		for(int d=0;d<nums.length;d++){
			ints[d]=Integer.parseInt(nums[d]);
		}
		return ints;
	}
	public static boolean contains(Object obj,Object[] os){
		for(Object o:os){
			if(o.equals(obj)){
				return true;
			}
		}
		return false;
	}
	public static String extract(String[] args){
		String out="";
		for(String s:args){
			out+=" "+s;
		}
		return out.substring(1);
	}
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}
}
