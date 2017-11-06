package util.unit.exvius;


import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class TrustInfo {
	String type="";
	String effect="";
	String stats="";
	String element="";
	String resistence ="";
	
	public TrustInfo(Elements content) {
		boolean h2Trig=false;
		for(Element e:content){
			if(h2Trig){
				if(e.tagName().equals("h2")||e.tagName().equals("h3")){
					return;
				}
				else if(e.tagName().equals("ul")){
					for(Element l:e.children()){
						//System.out.println("l"+l.text());
						if(l.text().startsWith("Type:")){
							type=extractStringRepresentation(l);
						}
						else if(l.text().startsWith("Stats:")){
							stats = extractStringRepresentation(l);
						}
						else if(l.text().startsWith("Element:")){
							element=extractStringRepresentation(l);
						}
						else if(l.text().startsWith("Resistance:")){
							resistence = extractStringRepresentation(l);
						}
						else if(l.text().startsWith("Additional effect:")||l.text().startsWith("Effect:")){
							effect =extractStringRepresentation(l);
						}
					}
				}
				else if(e.tagName().equals("dl")){
					effect+=e.text();
				}
			}
			if(e.tagName().equals("h2")&&(e.text().equals("Statistics[edit | edit source]")||e.text().equals("Statistics"))){
				h2Trig=true;
			}
		}
		
	}
	private String extractStringRepresentation(Element l){
		String s="";
		for(Node n:l.childNodes()){
			if(n instanceof Element){
				Element ele= (Element) n;
				if(ele.tagName().equals("b")){
					//ignore
				}
				else if(ele.tagName().equals("span")){
					try{
						s+= ele.getElementsByAttributeValue("class", "tip module-tooltip").text();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else{
					s+=ele.text();
				}
			}
			else if (n instanceof TextNode){
				s+=n.toString();
			}
		}
		return s;
	}
	public String toString(){
		String s="";
		if(!type.equals("")){
			s+="Type: "+type+"\n";
		}
		if(!stats.equals("")){
			s+="Stats: "+stats+"\n";
		}
		if(!element.equals("")){
			s+="Element: "+element+"\n";
		}
		if(!resistence.equals("")){
			s+="Resistence: "+resistence+"\n";
		}
		if(!effect.equals("")){
			s+="Effect: "+effect+"\n";
		}
		return s;
	}
}
