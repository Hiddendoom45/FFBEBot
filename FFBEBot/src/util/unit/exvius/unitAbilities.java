package util.unit.exvius;

import java.util.Vector;


import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import util.Lib;

public class unitAbilities{
	public ability[] abilities;
	public conditional[] conditionals;
	public unitAbilities(Element abilityTable){
		Element head=new Element(Tag.valueOf("ol"), "test");
		boolean active = true;
		boolean conditional =false;
		Vector<ability> abilities=new Vector<ability>();
		Vector<conditional> conditionals=new Vector<conditional>();
		if(abilityTable==null)return;//in case magic==none
		for(int i=1;i<abilityTable.children().size();i++){
			if(abilityTable.child(i).text().trim().equals("Active")){
				active=true;
				conditional=false;
			}
			else if(abilityTable.child(i).text().trim().equals("Trait")){
				active=false;
				conditional=false;
			}
			else if(abilityTable.child(i).text().trim().equals("Conditional")){
				conditional=true;
			}
			else if(!(abilityTable.child(i).getElementsByTag("th").size()>0)){
				if(conditional){
					if(abilityTable.child(i).children().size()==5)head=abilityTable.child(i).child(0);
					conditionals.add(new conditional(abilityTable.child(i),head));
				}
				else{
					abilities.add(new ability(abilityTable.child(i),active));
				}
			}
		}
		this.abilities=new ability[abilities.size()];
		for(int i=0;i<abilities.size();i++){
			this.abilities[i]=abilities.get(i);
		}
		this.conditionals=new conditional[conditionals.size()];
		for(int i=0;i<conditionals.size();i++){
			this.conditionals[i]=conditionals.get(i);
		}
		
	}
	public class conditional{
		public String condition;
		public String aIconURL;
		public String name;
		public String effect;
		public String hits;
		public String MP;
		public conditional(Element row,Element head){
			if(row.children().size()<4){
				condition=row.child(0).text();
				name=row.child(1).text();
				effect=row.child(2).text();
			}
			else if(row.children().size()<5){
				condition=head.text();
				name=row.child(0).text();
				effect=row.child(1).text();
				hits=row.child(2).text();
				MP=row.child(3).text();
			}
			else{
				condition=row.child(0).text();
				//aIconURL=row.child(1).getElementsByTag("img").first().absUrl("src");
				name=row.child(1).text();
				effect=row.child(2).text();
				hits=row.child(3).text();
				MP=row.child(4).text();
			}
		}
	}
	public class ability{
		public boolean active;
		public String rarity;
		public String level;
		public String aIconURL;
		public String name;
		public String link;
		public String effect;
		public String hits;
		public String MP="-";
		public ability(Element row,boolean active){
			rarity=""+Lib.extractNumber(row.child(0).text());
			level=row.child(1).text();
			aIconURL=row.child(2).getElementsByTag("img").first().absUrl("src");
			name=row.child(3).text();
			link=row.child(3).getElementsByTag("a").first().absUrl("href");
			effect=row.child(4).text();
			if(row.children().size()>6){
			hits=row.child(5).text();
			MP=row.child(6).text();
			}
			else if(row.children().size()>5){
				MP=row.child(5).text();
			}
			this.active=active;
		}
	}
}