package Library.flair;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * Very basic parser that literally just grabs the subreddit specific css stylesheet link from the FFBE reddit
 * @author Allen
 *
 */
public class FlairCSSParser {
	public static void main(String[] args) throws IOException{
		System.out.println(getFlairCSSURL());
	}
	public static String getFlairCSSURL() throws IOException{
		String url = "https://old.reddit.com/r/FFBraveExvius/";
		Document doc = Jsoup.connect(url).execute().parse();
		return doc.getElementsByAttributeValue("ref", "applied_subreddit_stylesheet").first().absUrl("href");
	}
}
