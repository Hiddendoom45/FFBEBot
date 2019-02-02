package util.unit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import global.record.Log;


/**
 * Main class to manage the various unit aliases available
 * @author Allen
 *
 */
public class UnitAlias{
	public static final HashMap<Integer,UnitAlias> aliases = new HashMap<Integer,UnitAlias>();
	public static final File saveLoc=new File("unitAlias.json");
	public static void serialize(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<UnitAlias> alias = new ArrayList<UnitAlias>();
		for(int i:aliases.keySet()){
			alias.add(aliases.get(i).dedupe());
		}
		try(BufferedWriter out = Files.newBufferedWriter(saveLoc.toPath(),StandardOpenOption.CREATE,StandardOpenOption.WRITE)){
			gson.toJson(alias.toArray(new UnitAlias[]{}), out);
		}catch(IOException e){
			Log.logError(e);
		}
	}

	public static void deserialize(){
		Gson gson = new Gson();
		if(saveLoc.exists()){
			try(BufferedReader in = Files.newBufferedReader(saveLoc.toPath())){
				UnitAlias[] ua = gson.fromJson(in, UnitAlias[].class);
				for(UnitAlias a:ua){
					aliases.put(a.id, a);
				}
			}catch(IOException e){
				Log.logError(e);
			}
		}
	}
	public static void dumpExivus(UnitOverview.unitData[] data){
		for(UnitOverview.unitData d:data){
			if(d.id>-1){
				UnitAlias a;
				if(aliases.containsKey(d.id)){
					a = aliases.get(d.id);
				}
				else{
					a =  new UnitAlias(d.id);
				}
				a.setEENName(d.name);
				aliases.put(a.id, a);
			}
		}
	}
	public static void dumpReddit(RedditOverview.unitData[] data){
		for(RedditOverview.unitData d:data){
			if(d.unitID>-1){
				UnitAlias a;
				if(aliases.containsKey(d.unitID)){
					a = aliases.get(d.unitID);
				}
				else{
					a =  new UnitAlias(d.unitID);
				}
				a.setRENName(d.name);
				a.setRJPName(d.JPname);
				aliases.put(a.id, a);
			}
		}
	}
	public static boolean testAlias(String s,int id){
		if(aliases.containsKey(id)){
			return aliases.get(id).containsAlias(s);
		}
		return false;
	}
	//basic stuff
	private int id;
	private String rJPName="";
	private String rENName="";
	private String eENName="";
	//everextending list of additional aliases
	private TreeSet<String> otherAlias = new TreeSet<String>();
	//hack to make sure it's not null when deserializing from gson
	{
		
	}
	public UnitAlias(int id){
		this.id=id;
	}
	public UnitAlias setRJPName(String name){
		if(!name.equals(rJPName)){
			if(!rJPName.equals(""))otherAlias.add(rJPName);
			rJPName=name.toLowerCase();
		}
		return this;
	}
	public UnitAlias setRENName(String name){
		if(!name.equals(rENName)){
			if(!rENName.equals(""))otherAlias.add(rENName);
			rENName=name.toLowerCase();
		}
		return this;
	}
	public UnitAlias setEENName(String name){
		if(!name.equals(eENName)){
			if(!eENName.equals(""))otherAlias.add(eENName);
			eENName=name.toLowerCase();
		}
		return this;
	}
	public UnitAlias addAlias(String name){
		otherAlias.add(name.toLowerCase());
		return this;
	}
	public UnitAlias removeAlias(String name){
		if(!(name==null)){
			otherAlias.remove(name.toLowerCase());
		}
		return this;
	}
	public UnitAlias dedupe(){
		String[] remove = new String[otherAlias.size()];
		int i = 0;
		for(String s:otherAlias){
			if(rJPName.equals(s)){
				remove[i]=s;
				i++;
			}
			else if(rENName.equals(s)){
				remove[i]=s;
				i++;
			}
			else if(eENName.equals(s)){
				remove[i]=s;
				i++;
			}
		}
		for(String s:remove){
			removeAlias(s);
		}
		return this;
	}
	public boolean containsAlias(String name){
		if(rJPName.contains(name)) return true;
		else if(rENName.contains(name)) return true;
		else if(eENName.contains(name)) return true;
		for(String s:otherAlias){
			if(s.contains(name)) return true;
		}
		return false;
	}
	public String toString(){
		return "["+id+"]r:"+rJPName+"|"+rENName+" e:"+eENName+" o:"+otherAlias;
	}

}
