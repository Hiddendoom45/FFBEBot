package util.unit;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import global.record.SaveSystem;
import util.Lib;

public class RedditOverview {
	public ArrayList<String> possible=new ArrayList<String>();
	public ArrayList<Element> possibleData=new ArrayList<Element>();
	public RedditOverview(String unitName){
		Document doc = null;
		try{
			doc=SaveSystem.redditO;
			Element data=doc.getElementsByClass("wiki").get(0);
			Elements units=data.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for(Element u:units){
				Element unit=Lib.getNestedItem(new Elements(u), 2, "td").first();
				if(!(unit==null)){
					if(unit.text().toLowerCase().contains(unitName.toLowerCase())){
						this.possible.add(unit.text());
						possibleData.add(u);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public ArrayList<String> getNames(){
		return possible;
	}
	public unitData getData(int selection){
		return new unitData(possibleData.get(selection));
	}
	public class unitData{
		public String unitUrl;
		public String name;
		public String JPname;
		public String origin;
		public int baseR;
		public int maxR;
		public unitData(Element row){
			unitUrl=row.getElementsByAttribute("href").get(1).absUrl("href");
			name=row.child(2).text();
			JPname=row.child(1).text();
			origin=row.child(3).text();
			baseR=Lib.extractNumber(row.child(4).child(0).attr("href"));
			maxR=Lib.extractNumber(row.child(5).child(0).attr("href"));
		}
		
	}
}
