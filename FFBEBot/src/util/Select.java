package util;

import java.util.ArrayList;

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
	public Select(ArrayList<String> options,long ID,Selection source,String msg){
		this.options=options;
		this.ID=ID;
		this.source=source;
		this.msg=msg;
	}
	public Select(ArrayList<String> options, long ID, Selection source,ArrayList<String> names){
		this(options,ID,source, parse(source, names));
		this.names=names;
	}
	public static String parse(Selection source,ArrayList<String> names){
		String msg="Please select one of the following:";
		int i=0;
		for(String name:names){
			msg+="\n"+i+":"+name;
			i++;
		}
		return msg;
	}
}