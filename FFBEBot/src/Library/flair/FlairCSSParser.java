package Library.flair;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FlairCSSParser {
	public static String getFlairCSSURL() throws IOException{
		String url = "https://old.reddit.com/r/FFBraveExvius/";
		Document doc = Jsoup.connect(url).execute().parse();
		return doc.getElementsByAttributeValue("ref", "applied_subreddit_stylesheet").first().absUrl("href");
	}
}
