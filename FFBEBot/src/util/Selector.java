package util;

import java.util.HashMap;

import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class Selector {
	public final static int NumType=0;
	public final static int AlphaType=1;//A=0 etc, afterwards AA, AB etc
	public final static int NullType=2;
	private static final HashMap<Integer,Select> selections=new HashMap<Integer,Select>();
	public static boolean parseSelection(MessageReceivedEvent event){
		if(event.getMessage().getContent().equals("exit")&&selections.containsKey(key(event))){
			event.getChannel().sendTyping();
			selfPrune(selections.get(key(event)),event);
			selections.remove(key(event));
			Lib.sendMessage(event, event.getAuthorName()+" exited menu");
			return true;
		}
		else if(selections.containsKey(key(event))){
			event.getChannel().sendTyping();
			Select select=selections.get(key(event));
			if(valid(select.source.getInputType(),event)&&Selection(select.source.getInputType(),event.getMessage().getContent())<select.options.size()){
				select.selected=Selection(select.source.getInputType(),event.getMessage().getContent());
				select.selectedText=event.getMessage().getContent();
				selfPrune(select,event);
				select.source.selectionChosen(select, event);
				selections.remove(key(event));
			}
			else{
				selfPrune(select,event);
				if(select.tries>=3){
					selections.remove(key(event));
					Lib.sendMessageFormated(event, "%userMention% Exited selection menu due to 3 incorrect responses");
				}
				else{
					select.tries++;
					select.messageID=Lib.sendMessage(event, "Incorrect option use `exit` to exit menu\n"+select.msg).getId();
				}
			}
			return true;
		} 
		else{
			return false;
		}
	}
	public static void setSelection(Select selection,MessageReceivedEvent event){
		selection.messageID=Lib.sendMessage(event,selection.msg).getId();
		selections.put(key(event), selection);
		
	}
	private static boolean valid(int SelectionType,MessageReceivedEvent event){
		if(SelectionType==0){
			try{
				Integer.parseInt(event.getMessage().getContent());
				return true;
			}catch(NumberFormatException e){
				Log.logError(e);
				return false;
			}
		}
		else if(SelectionType==1){
			return isAlpha(event.getMessage().getContent());
		}
		else if(SelectionType==2){
			return true;
		}
		else{
			return false;
		}
	}
	private static int Selection(int SelectionType,String selection){
		if(SelectionType==0){
			return Integer.parseInt(selection);
		}
		else if(SelectionType==1){
			if(selection.length()==1){
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
		else if(SelectionType==-1){
			return -1;
		}
		else return -1;
	}
	private static boolean isAlpha(String s){
		char[] source= s.toUpperCase().toCharArray();
		for(char c:source){
			if(!Character.isUpperCase(c)){
				return false;
			}
		}
		return true;
	}
	private static void selfPrune(Select s,MessageReceivedEvent e){
		try{
			e.getChannel().getMessageById(s.messageID).deleteMessage();
			e.getMessage().deleteMessage();
		}catch(Exception e1){
			if(!e.isPrivate()){
				Log.log("ERROR", "Bot does not have permission to delete messages on "+e.getGuild().getName());
			}
		}
	}
	private static int key(MessageReceivedEvent e){
		return (""+e.getAuthor()+e.getChannel()).hashCode();
	}
	
	
}
