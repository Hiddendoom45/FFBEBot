package util;

import commands.Command;

public class HistoryLL{
	private HistoryLLNode tip;
	
	public HistoryLL(){}
	
	public HistoryLL(HistoryLLNode tip){
		this.tip = tip;
	}
	public HistoryLLNode get(){
		if(tip==null)return tip;
		if(tip.isExpired()){
			tip = null;
		}
		return tip;
	}
	
	public int msgSince(Command command){
		HistoryLLNode next = get();
		int since = 0;
		while(!(next==null)){
			since+=next.getMessagesSince();
			System.out.println(next.getCommandName());
			if(next.getCommandName().equals(Lib.extractCmdName(command))){
				return since;
			}
			next = next.getNext();
		}
		return -1;
	}
	
	public HistoryLLNode lastInstance(Command command){
		HistoryLLNode next = get();
		while(!(next==null)){
			if(next.getCommandName().equals(Lib.extractCmdName(command))){
				return next;
			}
			next = next.getNext();
		}
		return null;
	}
	
	public void append(HistoryLLNode node){
		if(tip==null){
			tip = node;
		}
		else{
			tip = tip.append(node);
		}
	}
}
