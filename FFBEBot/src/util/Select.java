package util;

import java.io.File;
import java.util.ArrayList;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * object representing the options for a selections 
 * @author Allen
 *
 */
public class Select {
	/**
	 * Array used by command class when selecting option(not necessary)
	 */
	public ArrayList<String> options;//options to return
	/**
	 * ID to indentify which selection this is in case of multiples being executed
	 */
	public long ID;//ID for what selection it is used for various purposes, mainly to identify the selction
	/**
	 * option # of the array selected
	 */
	public int selected;//index that is selected
	/**
	 * where this selection was executed from(to get input type)
	 */
	public Selection source;//command where it originated from
	/**
	 * message sent displaying options
	 */
	public String msg;//message to send(if there is one) for custom stuffs
	/**
	 * ID of this messsage(so it can be self pruned to clean things up)
	 */
	public String messageID;//ID of message sent by bot(response)//may be broken up into bits with (,) to signify multiple messages sent
	/**
	 * names of the different options(used to configure the message in a custom manner)
	 */
	public ArrayList<String> names;//names of the different options
	/**
	 * additional data variable that can be used to hold additional data about the selection
	 */
	public String[] additionalData;//use to hold additional information
	public String selectedText;
	public int tries=1;
	public File file;//file to send, auto attached to end of messages if non null
	public Message message;//message sent by default
	public Message errMessage;//message sent when error is trigered
	public Select(ArrayList<String> options,long ID,Selection source,String msg,File file){
		this.options=options;
		this.ID=ID;
		this.source=source;
		this.msg=msg;
		this.file=file;
		message=new MessageBuilder().append(msg).build();
		errMessage=new MessageBuilder().append("Incorrect option type `exit` to exit menu\n"+msg).build();
		//to avoid selector from not validating due to nonexistant index
		if(source.getInputType()==Selector.YNType){
			options.add("");
			options.add("");
		}
	}
	public Select(ArrayList<String> options,long ID,Selection source,String msg){
		this(options,ID,source,msg, null);
	}
	public Select(ArrayList<String> options, long ID, Selection source,ArrayList<String> names){
		this(options,ID,source,names,"Please select one of the following(type one of the following numbers):");
	}
	public Select(ArrayList<String> options, long ID, Selection source,ArrayList<String> names,String msgHead){
		this(options,ID,source, parse(source, names,msgHead),null);
		this.names=names;
	}
	
	public static String parse(Selection source,ArrayList<String> names,String msgHead){
		String msg=msgHead;//stuff to put before list in the names
		if(source.getInputType()==Selector.NumType){
			int i=0;
			for(String name:names){
				msg+="\n"+i+":"+name;
				i++;
			}
		}
		else if(source.getInputType()==Selector.YNType){
			msg+="\nY/yes\nN/no";
		}
		return msg;
	}
}