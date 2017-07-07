package commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import Library.summon.UnitSpecific;
import global.record.Data;
import global.record.Log;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Counter;
import util.CounterPool;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.rng.summon.SummonImageBuilder;

/**
 * A template class used to select units for whatever stuff needs that sort of thing
 * @author Allen
 *
 */
public abstract class UnitPaging extends CommandGenerics implements Selection,Command{
	private String msgHead;
	private HashMap<Long,List<Integer>> numSelect=new HashMap<Long,List<Integer>>();
	protected static String headDefault="Please select one or more of the following units by typing the number(s) separated by commas\n"
			+ "i.e. `0,1,2,3,4` or `2,9,1` etc\n"
			+ "or by typing one of the following numbers to switch pages\n"
			+ "10:previous page\n"
			+ "11:next page";
	public UnitPaging(String msgHead){
		this.msgHead=msgHead;
	}
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		renderAndSend(event,0,System.currentTimeMillis());
	}
	public List<UnitSpecific> getUnitSet(int set, List<? extends UnitSpecific> units){
		Vector<UnitSpecific> unitlist=new Vector<UnitSpecific>(10);
		try{
			for(int i=10*set;i<10*(set+1);i++){
				unitlist.add(units.get(i));
			}
		}catch(IndexOutOfBoundsException e){};
		return unitlist;
	}
	@Override
	public abstract void help(MessageReceivedEvent event);
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		int[] selections=Lib.extractNumbers(chosen.selectedText);
		Vector<Integer> selected=new Vector<Integer>();
		List<Integer> selects=numSelect.get(chosen.ID);
		if(!(selects==null)){
			selected.addAll(selects);
		}
		int paging=0;//no page change
		int set=Integer.parseInt(chosen.additionalData[0]);
		for(int i:selections){
			if(i<=11){
				if(i==10){
					paging=-1;//previous page
				}
				else if(i==11){
					paging=1;//next page
				}
				else{
					if(!selected.contains((set*10)+i)){
						selected.add((set*10)+i);//add to list
					}
				}
			}
		}
		if(paging==0){
			unitsSelected(event,selected);
		}
		else{
			numSelect.put(chosen.ID, selected);
			if(paging==-1){
				if(set==0){
					//not sure if error message but will just proceed with action
					unitsSelected(event,selected);
				}
				else{
					renderAndSend(event,set-1,chosen.ID);
				}
			}
			else if(paging==1){
				Data user=SaveSystem.getUser(event.getAuthor().getId());
				if(user.units.size()>(set*10)+1){
					renderAndSend(event,set+1,chosen.ID);
				}
				else{
					unitsSelected(event,selected);
				}
			}
		}
	}
	
	public abstract void unitsSelected(MessageReceivedEvent event,List<Integer> selectedIndexes);
	/**
	 * Used to build the page
	 */
	public void renderAndSend(MessageReceivedEvent event,int set,long ID){
		try{
			Data user=SaveSystem.getUser(event.getAuthor().getId());
			ArrayList<String> names=new ArrayList<String>(1);
			Counter count=new Counter("Finding Units %count%/"+(user.units.size()>=(set*10)+10?10:user.units.size()%10), event);
			CounterPool.getPool().add(count);
			BufferedImage units=new SummonImageBuilder(1).addUnit(getUnitSet(set,user.units)).buildColumnsDynamically().buildWithNumbers().build(event, count);
			File file= new File(Lib.newFileName("pageSelect")+".png");
			ImageIO.write(units, "PNG", file);
			count.terminate();
			Select select=new Select(names, ID, this, msgHead, new File("pageSelect.png"));
			select.file=file;
			select.additionalData=new String[]{set+""};//current set
			Selector.setSelection(select, event);//start Selection Event
		}catch(Exception e){
			Log.logError(e);
		}
	}
	@Override
	public int getInputType() {
		return Selector.NullType;
	}
	
}
