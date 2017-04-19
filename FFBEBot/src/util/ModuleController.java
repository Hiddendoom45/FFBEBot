package util;

import java.util.Vector;

import XML.Attribute;
import XML.Elements;

/**
 * used to control whether a module is enabled or disabled per guild
 * @author Allen
 *
 */
public class ModuleController {
	private String name;
	private Vector<String> channels=new Vector<String>();
	private boolean globalDisable=false;
	public ModuleController(String moduleName){
		name=moduleName;
	}
	public ModuleController(Elements root){
		name=root.getAttribute("name").getValue();
		String[] channels=root.getChilds("channels").get(0).getText().split(",");
		for(String s:channels){
			this.channels.add(s);
		}
		globalDisable=Lib.getBooleanSetting(false, root, "globalDisable");
		
	}
	public ModuleController toggle(String channel){
		if(channels.contains(channel)){
			channels.remove(channel);
		}
		else{
			channels.add(channel);
		}
		return this;
	}
	public ModuleController toggleGlobalDisable(){
		globalDisable=!globalDisable;
		channels.clear();
		return this;
	}
	public boolean enabled(String channel){
		System.out.println(globalDisable);
		if(channels.contains(channel)){
			return globalDisable;
		}else{
			return !globalDisable;
		}
	}
	public boolean enabledGlobal(){
		return !globalDisable;
	}
	public Elements parseToElements(){
		Elements root=new Elements("moduleControl").addAttribute(new Attribute("name",name));

		Elements channels=new Elements("channels");
		String text="";
		for(String s:this.channels){
			text+=","+s;
		}
		if(text.length()>0)channels.setText(text.substring(1));
		root.add(channels);
		
		Elements globalDisable=new Elements("globalDisable").setText(""+this.globalDisable);
		root.add(globalDisable);
		return root;
		}
}
