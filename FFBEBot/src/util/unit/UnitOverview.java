package util.unit;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Lib.ElementFilter;
import global.record.Settings;
import util.Lib;

public class UnitOverview {
	public ArrayList<String> possible=new ArrayList<String>();
	public ArrayList<Element> possibleData=new ArrayList<Element>();
	public UnitOverview(String unitName){
		Document doc = null;
		try{
			while(true){
				doc = Jsoup.connect("https://exviuswiki.com/Unit_List").userAgent(Settings.UA).timeout(10000).get();
				if(!(doc==null))break;
			}
			Elements data=doc.getElementById("mw-content-text").children();
			Elements first=new Elements();
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Main Character")));
			first.add(Lib.getEleAfter(data, new ElementFilter("h3","Friend Summon")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Rare Summon")));
			first.addAll(Lib.getElesAfter(data, new ElementFilter("h3","Limited Unit")));
			Elements units =Lib.getNested(first, "tr");
			for(Element u:units){
				Element unit=Lib.getNestedItem(new Elements(u), 1, "td").first();
				if(!(unit==null)){
					if(unit.text().toLowerCase().contains(unitName.toLowerCase())){
						this.possible.add(unit.text());
						possibleData.add(u);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		};
	}
	public ArrayList<String> getNames(){
		return possible;
	}
	public unitData getData(int selection){
		return new unitData(possibleData.get(selection));
	}
	public class unitData{
		public String imgUrl;
		public String unitUrl;
		public String name;
		public String origin;
		public String role;
		public String baseR;
		public String maxR;
		public String trustName;
		public unitData(Element row){
			imgUrl=row.getElementsByAttribute("src").first().attr("abs:src");
			unitUrl=row.child(1).child(0).absUrl("href");
			name=row.child(1).text();
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
		for(Element e:possibleData){
			s+=new unitData(e);
		}
		return s;
	}
	
}
