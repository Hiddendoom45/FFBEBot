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
		String msg="Help\n"
				+ SaveSystem.getPrefix(event)+"units"
				+ "\t`get unit data`\n"
				+ SaveSystem.getPrefix(event)+"unitskill [unit]"
				+ "\t`gets skills of unit (does not include information on most unreleased units)`\n"
				+ SaveSystem.getPrefix(event)+"equipment [unit]"
				+ "\t`find the equipment a unit can use`\n"
				+ SaveSystem.getPrefix(event)+"awaken [unit][rarity]"
				+ "\t`find awakening mats for a unit`\n"
				+"\n for all above commands, add r to beginning to use data from reddit i.e. `"
				+ SaveSystem.getPrefix(event)+"runits` to get unit data from reddit\n"
				+ SaveSystem.getPrefix(event)+"ping"
				+ "\t`ping command to see how fast bot response is`\n"
				+ SaveSystem.getPrefix(event)+"unitart [unit][rarity]"
				+ "\t`get art of unit at rarity(if specified)`\n"
				+ SaveSystem.getPrefix(event)+"unitlore [unit] [rarity]"
				+ "\t`gets lore of unit`\n"
				+ SaveSystem.getPrefix(event)+"waifus"
				+ "\t `find a waifu`\n"
				+ SaveSystem.getPrefix(event)+"maintenance"
				+ "\t`for GUMI maintenances`\n"
				+ SaveSystem.getPrefix(event)+"salt"
				+"\t`invoke saltiness(WIP) AKA Tilith`\n"
				+ SaveSystem.getPrefix(event)+"chanceTilith [#11pulls] [#regular pulls]"
				+ "\t`calculates potential chance of getting Tilith`*probably not 100% accurate ** based on estimated 3.333% chance for tilith\n"
				+ SaveSystem.getPrefix(event)+"whale"
				+ "\t`watch videos of whales pulling(WIP)`\n"
				+ SaveSystem.getPrefix(event)+"summon"
				+ "\t`summon simulator(WIP)`";
		Lib.sendMessage(event, msg);
	}
	public static void printModHelp(MessageReceivedEvent event){
		String msg="Mod Help\n"
				+ SaveSystem.getModPrefix(event)+"disable"
				+ "\t`shutdown the bot(takes it offline)`\n"
				+ SaveSystem.getModPrefix(event)+"join [MSG]"
				+ "\t`sets a join message for the server, leave blank for no message`\n"
				+ SaveSystem.getModPrefix(event)+"prefix [prefix]"
				+ "\t`changes the prefix used for the bot's commands`\n"
				+ SaveSystem.getModPrefix(event)+"sleep [timeout]"
				+ "\t`there really isn't much reason you'd want to take the bot offline for a bit`\n"
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
		System.out.println(s);
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
		System.out.println(i);
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
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}
}
