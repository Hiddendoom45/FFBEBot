package util;

import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Class to handle selection events that allow the user to chose one of multiple options
 * @author Allen
 *
 */
public class Selector {
	public final static int NumType=0;//default
	public final static int AlphaType=1;//A=0 etc, afterwards AA, AB etc
	public final static int NullType=2;//doesn't matter what is entered, will return regardless, no fixed input necessary
	public final static int YNType=3;//if it's a yes/no question
	private static final HashMap<Integer,Select> selections=new HashMap<Integer,Select>();//all the currently ongoing selection events
	/**
	 * check if a selection event is going on
	 * @param event event to check
	 * @return true if there's a selection event going on, or false if not
	 */
	public static boolean parseSelection(MessageReceivedEvent event){
		//overall check for if there is an event
		if(selections.containsKey(key(event))){
			Log.log("status", "selectionText:" + event.getMessage().getContent()+" from "+event.getAuthor()+(event.isFromType(ChannelType.PRIVATE)?"":" on "+event.getGuild()));
			//if user exits
			if(event.getMessage().getContent().equals("exit")){
				event.getChannel().sendTyping();//notify message recieved
				selfPrune(selections.get(key(event)),event);//delete messages in relation to selection
				selections.remove(key(event));//remove event
				Lib.sendMessage(event, event.getAuthor().getName()+" exited menu");//send message to update status
				return true;
			}
			//if typical selection event
			else{
				event.getChannel().sendTyping();//notify message recieved
				Select select=selections.get(key(event));//select representing the event
				//check if valid
				if(valid(select.source.getInputType(),event)&&Selection(select.source.getInputType(),event.getMessage().getContent())<select.options.size()){
					select.selected=Selection(select.source.getInputType(),event.getMessage().getContent());//create new selected to return
					select.selectedText=event.getMessage().getContent();//set text of selected
					selfPrune(select,event);//delete messages in relation to selection
					selections.remove(key(event));//remove event //before triggering main command so selections can be chained
					select.source.selectionChosen(select, event);//return to main command that selection has been chosen
				}
				//if not send message that they selected the wrong one
				else{
					selfPrune(select,event);
					if(select.tries>=3){//to avoid infinate loops, fails after 3 attempts
						selections.remove(key(event));
						Lib.sendMessageFormated(event, "%userMention% Exited selection menu due to 3 incorrect responses");
					}
					else{
						select.tries++;
						if(select.file==null){
							select.messageID=Lib.sendMessage(event, select.errMessage).getId();
						}
						else{
							select.messageID=Lib.sendFile(event, select.errMessage, select.file).getId();
						}
					}
				}
				return true;
			} 
		}
		else{
			return false;
		}
	}
	/**
	 * creates a new selection event, called by commands
	 * @param selection selection command
	 * @param event event
	 */
	public static void setSelection(Select selection,MessageReceivedEvent event){
		if(selection.file==null){
			selection.messageID=Lib.sendMessage(event,selection.message).getId();
		}
		else{
			selection.messageID=Lib.sendFile(event, selection.message, selection.file).getId();
		}
		selections.put(key(event), selection);
	}
	/**
	 * if input is valid selection
	 * @param SelectionType type
	 * @param event event containing the message
	 * @return if valid
	 */
	private static boolean valid(int SelectionType,MessageReceivedEvent event){
		if(SelectionType==0){//number, just check if it's a number
			try{
				Integer.parseInt(event.getMessage().getContent());
				return true;
			}catch(NumberFormatException e){
				Log.log("CMDERROR","NaN:"+event.getMessage().getContent());
				return false;
			}
		}
		else if(SelectionType==1){//if message is a letter
			return isAlpha(event.getMessage().getContent());
		}
		else if(SelectionType==2){//null selection
			return true;
		}
		else if(SelectionType==3){//yes no selection
			String message=event.getMessage().getContent();
			if(message.toLowerCase().contains("y")||message.toLowerCase().contains("n")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/**
	 * get index of selection
	 * @param SelectionType type
	 * @param selection string representing what user selected
	 * @return index of selection
	 */
	private static int Selection(int SelectionType,String selection){
		if(SelectionType==Selector.NumType){//number, just parse it
			return Integer.parseInt(selection);
		}
		else if(SelectionType==1){//letters convert to numbers
			if(selection.length()==Selector.AlphaType){
				return selection.toUpperCase().toCharArray()[0]-65;
			}
			else{
				char[] letters=selection.toUpperCase().toCharArray();
				int chosen=0;
				for(int i=0;i<letters.length;i++){
					chosen+=(letters[i]-64)*(Math.pow(26, (letters.length-i-1)));
				}
				return chosen-1;
			}
		}
		else if(SelectionType==Selector.YNType){
			if(selection.toLowerCase().contains("y")){
				return 0;
			}
			else if(selection.toLowerCase().contains("n")){
				return 1;
			}
			else{
				return -1;
			}
		}
		else if(SelectionType==-1){//null nothing chosen, index is nothing
			return -1;
		}
		else return -1;//null type, returns -1 by default
	}
	/**
	 * checks if string is all characters
	 * @param s string to check
	 * @return if all characters
	 */
	private static boolean isAlpha(String s){
		char[] source= s.toUpperCase().toCharArray();
		for(char c:source){
			if(!Character.isUpperCase(c)){
				return false;
			}
		}
		return true;
	}
	/**
	 * method used to delete messages in relation 
	 * @param s
	 * @param e
	 */
	private static void selfPrune(Select s,MessageReceivedEvent e){
		try{
			e.getChannel().getMessageById(s.messageID).complete().delete().queue();
			if(e.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)){
				e.getMessage().delete().queue();
				Log.log("DELETE", "deleted message "+e.getMessage().getContent());
			}
		}catch(Exception e1){
			if(!e.isFromType(ChannelType.PRIVATE)){
				Log.logShortError(e1, 7);
			}
		}
	}
	private static int key(MessageReceivedEvent e){
		return (""+e.getAuthor()+e.getChannel()).hashCode();
	}


}
