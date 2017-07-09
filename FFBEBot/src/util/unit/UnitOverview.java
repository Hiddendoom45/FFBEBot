package util.unit;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import Library.ElementFilter;
import global.record.Data;
import global.record.Log;
import global.record.Settings;
import util.Lib;

public class UnitOverview {
	public ArrayList<String> possible=new ArrayList<String>();
	public ArrayList<unitData> possibleData=new ArrayList<unitData>();
	public UnitOverview(String unitName){
		try{
			for(unitData u:new Gson().fromJson(Data.exvicusO,unitData[].class)){
				if(u.name.toLowerCase().contains(unitName.toLowerCase())){
					this.possible.add(u.name);
					this.possibleData.add(u);
				}
				}
		}catch(Exception e){
			Log.logError(e);
		}
	}
	public ArrayList<String> getNames(){
		return possible;
	}
	public unitData getData(int selection){
		return possibleData.get(selection);
	}
	public static unitData[] preload(){
		unitData[] udata = null;
		Document doc = null;
		try{
			for(int i=0;i<10;i++){
				try{
					doc = Jsoup.connect(Settings.ExvicusURL+"Unit_List").followRedirects(true).userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(Exception e){Log.logError(e);}
			}
			//generate the rows containing unit data
			Elements data=doc.getElementById("mw-content-text").children();
			Elements first=new Elements();
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Main Character[edit | edit source]")));
			first.add(Lib.getEleAfter(data, new ElementFilter("h3","Friend Summon[edit | edit source]")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Rare Summon[edit | edit source]")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Limited Unit[edit | edit source]")));
			for(int i=0;i<10;i++){
				try{
					doc = Jsoup.connect(Settings.ExvicusURL+"Unreleased_Unit_List").followRedirects(true).userAgent(Settings.UA).timeout(60000).get();
					if(!(doc==null))break;
				}
				catch(Exception e){Log.logError(e);}
			}
			data=doc.getElementById("mw-content-text").children();
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Main Character[edit | edit source]")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Rare Summon[edit | edit source]")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Limited Unit[edit | edit source]")));
			Elements units =Lib.getNested(Lib.getNested(first,"tbody"), "tr");
			
			Elements remove = new Elements();//non unit entries to remove
			for(Element u:units){
				if(u.getElementsByTag("td").size()==0){
					remove.add(u);
				}
			}
			for(Element u:remove){
				units.remove(u);
			}
			udata=new unitData[units.size()];
			int i=0;
			for(Element u:units){
				udata[i]=new unitData(u);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return udata;
	}
	public static class unitData{
		public String imgUrl;
		public String unitUrl;
		public String name;
		public String origin;
		public String role;
		public String baseR;
		public String maxR;
		public String trustName;
		public boolean isNew=false;
		public unitData(Element row){
			imgUrl=row.getElementsByAttribute("src").first().attr("abs:src");
			unitUrl=row.child(1).child(0).absUrl("href");
			name=row.child(1).text();
			if(row.child(1).child(0).hasClass("new"))isNew=true;
			origin=row.child(2).text();
			if(row.childNodeSize()<5){
				role=row.child(3).text();
				baseR=row.child(4).text();
				maxR=row.child(5).text();
				trustName=row.child(6).text();
			}
			else{
				baseR=row.child(3).text();
				maxR=row.child(4).text();
			}
		}
		public String toString(){
			return "imgUrl"+imgUrl+" unitUrl"+unitUrl+" name"+name+" origin"+origin+" role"+role+" baseR"+baseR+" maxR"+maxR+" trust"+trustName;
		}
	}
	public String toString(){
		String s="";
		for(unitData e:possibleData){
			s+=e;
		}
		return s;
	}
	
	
}
