package util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Library.ElementFilter;
import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
/**
 * Library of various useful methods that are used all over the place
 * @author Allen
 *
 */
public class Lib {
	//methods sending data
	/**
	 * message data for help menu
	 * @param event
	 */
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
	
	/**
	 * message data for mod help menu
	 * @param event
	 */
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
	/**
	 * Sends a message which will be deleted after a period of time
	 * @param event message recieved
	 * @param msg message to send in response
	 * @param timeout time in seconds after which the message will be deleted
	 */
	public static void sendTempMessage(MessageReceivedEvent event, String msg,long timeout){
		Settings.executor.execute(new Runnable(){
			public void run(){
				try {
					String id=sendMessageFormated(event, msg).getId();
					TimeUnit.SECONDS.sleep(timeout);
					event.getChannel().deleteMessageById(id);
				} catch (Exception e) {
					Log.log("ERROR", "error sending delayed message");
					Log.logShortError(e, 5);
				}
				
			}
		});
	}
	/**
	 * Formats the message <br/>
	 * Special formatting <br/> 
	 * %userMention% mentions the user that sent the message <br/>
	 * %userName% prints name of user that sent the message <br/>
	 * %selfMention% mentions the bot
	 * @param event message received
	 * @param msg message to send in response
	 * @return message that was sent
	 */
	public static Message sendMessageFormated(MessageReceivedEvent event,String msg){
		return Lib.sendMessage(event,Lib.FormatMessage(event,msg));
	}
	/**
	 * generic send message, will have wrappers to fix some issues and errors in relation to sending messages
	 * @param event message received
	 * @param msg message to send someone
	 * @return message that was sent
	 */
	public static Message sendMessage(MessageReceivedEvent event,String msg){
		try{
			//if(!SpamControl.isSpam(event, "global")&&!event.isPrivate()&&!msg.contains("too many messages, please wait")) return null;//disabled to avoid a bunch of pain
			return event.getChannel().sendMessage(msg);
		}catch(net.dv8tion.jda.exceptions.RateLimitedException e){
			try {
				TimeUnit.MILLISECONDS.sleep(extractNumber(e.getMessage()));
				return sendMessage(event,msg);
			} catch (InterruptedException e1) {}
		}
		//it really shouldn't really go this far should return from spam check or from the delayed task, if it does retry
		return sendMessage(event,msg);
	}
	/**
	 * Formats the message <br/>
	 * Special formatting <br/> 
	 * %userMention% mentions the user that sent the message <br/>
	 * %userName% prints name of user that sent the message <br/>
	 * %selfMention% mentions the bot
	 * @param event message received
	 * @param msg message to send in response
	 * @return string of formatted message to send
	 */
	public static String FormatMessage(MessageReceivedEvent event,String msg){
		return msg.replace("%userMention%", event.getAuthor().getAsMention()).
				replace("%userName%", event.getAuthorName()).
				replace("%selfMention%", event.getJDA().getSelfInfo().getAsMention());
	}
	/**
	 * Formats and send message for guild member joining <br/>
	 * Special formatting<br/>
	 * %userMention% mentions the user that joined<br/>
	 * %userName% prints name of the user<br/>
	 * %guildName% prints name of the server
	 * @param event user join event
	 * @param msg message to send in response
	 * @return message sent
	 */
	public static Message sendMessageFormated(GuildMemberJoinEvent event,String msg){
		return event.getGuild().getPublicChannel().sendMessage(Lib.FormatMessage(event,msg));
	}
	/**
	 * Formats and send message for guild member joining <br/>
	 * Special formatting<br/>
	 * %userMention% mentions the user that joined<br/>
	 * %userName% prints name of the user<br/>
	 * %guildName% prints name of the server
	 * event user join event
	 * @param msg message to send in response
	 * @return string of formatted message to send
	 */
	public static String FormatMessage(GuildMemberJoinEvent event,String msg){
		return msg.replace("%userMention%", event.getUser().getAsMention()).
		replace("%userName%", event.getUser().getUsername()).
		replace("%guildName%",event.getGuild().getName());
	}
	//stuff for handling elements in a web page
	/**
	 * Gets elements within an element based on tag name
	 * @param element elements to get nestled element
	 * @param tags list of tags to get the nestled element for, going right to left, i.e. ["first","second"] gets &lt;second/&gt; in &lt;first&gt;&lt;second/&gt;&lt;/first&gt; 
	 * @return list of elements that match the parameters
	 */
	public static Elements getNestedDeep(Elements element,String[] tags){
		Elements ele=element;
		for(String s:tags){
			ele=Lib.getNested(ele, s);
		}
		return ele;
	}
	/**
	 * Gets elements within an element based on tagname
	 * @param element elements to get nestled element
	 * @param tag tag to get within the main element
	 * @return list of elements that match the parameters
	 */
	public static Elements getNested(Elements element, String tag){
		Elements ele=new Elements();
		if(element==null){
			return new Elements();
		}
		for(Element eles:element){
			ele.addAll(eles.getElementsByTag(tag));
		}
		return ele;
	}
	/**
	 * 
	 * Gets elements within an element based on tagname
	 * @param element elements to get nestled element
	 * @param index index of the element in the list of elements that match parameter for each specific element in given list
	 * @param tag tag to get within the main element
	 * @return list of elements that match the parameters
	 */
	public static Elements getNestedItem(Elements element,int index,String tag){
		Elements ele=new Elements();
		for(Element eles:element){
			try{
				ele.add(eles.getElementsByTag(tag).get(index));
			}catch(Exception e){};
		}
		return ele;
	}
	/**
	 * Gets elements after what matches element filter
	 * @param elements element list for which you want get the element after for
	 * @param after filter containing parameters for the element
	 * @return elements after any element matching element filter
	 */
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
	/**
	 * Gets elements after what matches element filter
	 * @param elements element list for which you want get the element after for
	 * @param after filter containing parameters for the element
	 * @return elements after any element matching element filter
	 */
	public static Element getEleAfter(Elements elements,ElementFilter after){
		Elements e=getElesAfter(elements,after);
		if(e.size()>0){
			return e.first();
		}
		return null;
		
	}
	/**
	 * gets table rows in a table
	 * @param table table element &lt;tbody&gt;
	 * @return number of rows, does not include &lt;thead&gt;
	 */
	public static int getTRCount(Element table){
		Elements tr=table.getElementsByTag("tr");
		return tr.size();
	}
	/**
	 * Gets columns in the row
	 * @param table table element &lt;tbody&gt;
	 * @param row row #
	 * @return number of columns
	 */
	public static int getTDCount(Element table,int row){
		Elements td=table.getElementsByTag("tr").get(row).getElementsByTag("td");
		return td.size();
	}
	/**
	 * gets a specific cell in a table
	 * @param row row #
	 * @param col column #
	 * @param table table element &lt;tbody&gt;
	 * @return element representing the specific cell
	 */
	public static Element getCell(int row, int col,Element table){
		return table.getElementsByTag("tr").get(row).getElementsByTag("td").size()>0? 
				table.getElementsByTag("tr").get(row).getElementsByTag("td").get(col):null;
	}
	/**
	 * get specific row
	 * @param row row #
	 * @param table table element &lt;tbody&gt;
	 * @return element representing the specific header row
	 */
	public static Element getHeader(int row, Element table){
		return table.getElementsByTag("tr").get(row).getElementsByTag("th").get(0);
	}
	/**
	 * gets link in element
	 * @param ele element to get link from
	 * @return String
	 */
	public static String getLink(Element ele){
		Elements links = ele.getElementsByTag("a");
		for (Element link : links) {
			return link.attr("abs:href");
		}
		return "";
	}
	//random utilities
	/**
	 * makes sure that string is at least x length, padding it w/ spaces
	 * @param s string to pad
	 * @param pad minimum length of string
	 * @return padded string
	 */
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
	/**
	 * gets numbers(integers) from string
	 * @param s string to get numbers from
	 * @return array of all the numbers in the string
	 */
	public static int[] extractNumbers(String s){
		String i="";
		boolean number=false;//if currently indexing a number
		boolean dot=false;//decimal place
		for(char c:s.trim().toCharArray()){
			if(number){
				if(Character.isDigit(c)||dot?false:c=='.'){
					if(c=='.'){
						dot=true;
					}
					i+=c;
				}
				else{
					i+=",";
					number=false;
					dot=false;
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
	/**
	 * if x array contains obj
	 * @param obj object if it contains
	 * @param os array of objects
	 * @return if os contains obj
	 */
	public static boolean contains(Object obj,Object[] os){
		for(Object o:os){
			if(o.equals(obj)){
				return true;
			}
		}
		return false;
	}
	/**
	 * converts a string array to 
	 * @param args
	 * @return
	 */
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
