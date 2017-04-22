package commands;

import java.util.ArrayList;
import java.util.HashMap;

import Library.summon.banner.Banner;
import commands.sub.DoSummon;
import global.record.SaveSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.Select;
import util.Selection;
import util.Selector;
import util.rng.summon.SummonTypes;
/**
 * Gets a list of banners and shortcut to summon from them
 * @author Allen
 *
 */
public class Banners extends CommandGenerics implements Command, Selection {
	HashMap<Long,BannerStatus> save=new HashMap<Long,BannerStatus>();//stores information on what part of banner selection it's at.
	@Override
	public void selectionChosen(Select chosen, MessageReceivedEvent event) {
		BannerStatus status=save.get(chosen.ID);
		save.remove(chosen.ID);
		if(status.bannerPage){
			if(chosen.selected==10){//next page int
				listBanners(event,status.index+1);
			}
			else if(chosen.options.size()<10&&chosen.selected==1+chosen.options.size()){//exit option
				return;
			}
			else{
				sendBannerInfo(event,getBanner(chosen.options.get(chosen.selected)));
			}
		}
		else{
			if(chosen.selected>0){
				if(chosen.selected==4){//exit option
					Lib.sendMessage(event, "Exited banners menu");
					return;
				}
				else if(chosen.selected==1){//summon simulation
					new DoSummon(event, status.b,SummonTypes.SummonSimulate);
				}
				else if(chosen.selected==2){
					new DoSummon(event, status.b,SummonTypes.Pull);
				}
				else if(chosen.selected==3){
					new DoSummon(event, status.b,SummonTypes.Elevenpull);
				}
			}
			else {
				Lib.sendMessage(event, "Banner name is "+status.b+" \ntype `"+SaveSystem.getPrefix(event)+"summon [amount] "+status.b +" to simulate summons from this banner"
						+ "\n type "+SaveSystem.getPrefix(event)+"pull [amount] "+status.b+" to pull units from this banner"
						+ "\n type "+SaveSystem.getPrefix(event)+"11pull "+status.b+" to do a 10+1 summon from this banner");
			}
		}
	}

	@Override
	public int getInputType() {
		return 0;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if(args.length>0){
			if(validBanner(args[0])){
				sendBannerInfo(event,getBanner(args[0]));
			}
			else if(Lib.isNumber(args[0])){
				listBanners(event,Integer.parseInt(args[0])-1);
			}
			else{
				Lib.sendMessage(event, "not a banner name, use "+SaveSystem.getPrefix(event)+"banner to lookup available banners");
			}
		}
		else{
			listBanners(event,0);
		}

	}
	/**
	 * Generates a paged list of banners, with options to go to the next page
	 * @param event
	 * @param page
	 */
	public void listBanners(MessageReceivedEvent event, int page){
		ArrayList<String> names=new ArrayList<String>();
		int i=0;
		for(Banner b:Banner.values()){
			i++;
			if(i>page*10&&i<=(page+1)*10){//only if within 10 page range
				names.add(b.toString());
			}
		}
		if(i>(page+1)*10){//if there were more banners after, trigger adding the next button
			names.add("next Banner Page "+(page+2));
		}
		else{
			names.add("exit");
		}
		Select select=new Select(names, System.currentTimeMillis(), this, names, "Choose the banner you want to view more information about:");
		save.put(select.ID, new BannerStatus(page));
		Selector.setSelection(select, event);
	}
	public boolean validBanner(String banner){
		for(Banner b:Banner.values()){
			if(b.toString().toLowerCase().equals(banner.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	public Banner getBanner(String banner){
		for(Banner b:Banner.values()){
			if(b.toString().toLowerCase().equals(banner.toLowerCase())){
				return b;
			}
		}
		return null;
	}
	public void sendBannerInfo(MessageReceivedEvent event,Banner banner){
		String head=banner.name+" ["+banner.toString()+"]\nFeatured units:\n";//head displays basic banner info
		System.out.println(banner);
		for(int i=0;i<banner.featured.length;i++){
			head+=banner.featured[i].name+" ";
			head+=(Lib.Summation(banner.percent[i])/100.00)+"%\n";
		}
		head+="\nchoose one of the following options by typing in the number";
		ArrayList<String> names=new ArrayList<String>();
		names.add("banner name (name you put when summoning from it using `summon`,`pull`, and `11pull` commands)");
		names.add("simulate summon from this banner");
		names.add("pull from this banner");
		names.add("do a 10+1 pull from this banner");
		names.add("exit");
		Select select=new Select(names,System.currentTimeMillis(),this,names,head);
		save.put(select.ID, new BannerStatus(banner,false));//option picked flag false for banner info selection
		Selector.setSelection(select, event);
	}
	@Override
	public void help(MessageReceivedEvent event) {
		String s="banners [banner]/[page]\n"
				+ "\tif no arguments, lists all banners you can summon from\n"
				+ "\t[banner] specify a specific banner you want to find more info about"
				+ "\t[page] in the banner list you want to jump to ";
		Lib.sendMessage(event, s);

	}
	private class BannerStatus{
		boolean bannerPage;//if true index is for banner page instead of banner
		int index;
		Banner b;
		@SuppressWarnings("unused")
		boolean optionPicked;//might be useless
		BannerStatus(int index){
			bannerPage=true;
			this.index=index;
		}
		BannerStatus(Banner b,boolean optionPicked){
			this.optionPicked=optionPicked;
			this.b=b;
			bannerPage=false;
		}
	}
}
