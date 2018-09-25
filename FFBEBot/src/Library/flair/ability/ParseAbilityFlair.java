package Library.flair.ability;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;

import Library.flair.FlairCSSParser;

public class ParseAbilityFlair {
	public static void main(String[] args) throws IOException{
		File flairDest = new File("src/Library/flair/ability");
		flairDest.mkdirs();
		//36x36 ability flair resolution
		int fH = 36;
		int fW = 36;
		String unitCSSSource = "https://b.thumbs.redditmedia.com/GsKQ-nkhBdXNCYCYSndg1FnCgUS5CnwN_3iwSyZfr-c.css";
		String css = Jsoup.connect(unitCSSSource).execute().body();
		String abilityFlairSource = extractFlairSource(css);
		Matcher m = Pattern.compile("a\\Q[href*=\"\\E/a(\\d*?)/\\Q\"]\\E.*?\\{background:-(\\d*?)em 0\\}").matcher(css);
		BufferedImage flairs = ImageIO.read(new URL(abilityFlairSource).openStream());
		while(m.find()){
			BufferedImage f = new BufferedImage(fW, fH, BufferedImage.TYPE_INT_ARGB);
			Graphics g = f.getGraphics();
			g.drawImage(flairs, Integer.parseInt(m.group(2))*-fW, 0, null);
			ImageIO.write(f, "PNG", new File(flairDest.getAbsoluteFile()+"/a"+m.group(1)+".png"));
		}
	}
	private static String extractFlairSource(String css){
		Matcher m = Pattern.compile("\\Qa[href^=\"#A/Icons/\"]:after{background-image:url(\"\\E(.*?)\\Q\")!important}\\E").matcher(css);
		if(m.matches()){
			return "https:"+m.group(1);
		}
		else{
			return "https://b.thumbs.redditmedia.com/Oq_KgmqJyY_xvQbw_tSieLp0dHOlB3JLGHqOEnbHgsM.png";
		}
	}
}
