package util.unit.exvius;

import org.jsoup.nodes.Element;

import util.Lib;

public class unitQuotes{
	public quote[] quotes;
	public unitQuotes (Element quoteTable){
		quotes=new quote[quoteTable.children().size()];
		for(int i=0;i<quoteTable.children().size();i++){
			quotes[i]=new quote(quoteTable.child(i));
		}
	}
	public class quote{
		public String rarity;
		public String quote;
		public quote(Element row){
			if(row.children().size()>1){
			rarity=""+Lib.extractNumber(row.child(0).text());
			quote=row.child(1).text();
			}
			else{
				quote=row.child(0).text();
			}
		}
	}
}

