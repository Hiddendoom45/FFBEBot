package Library.flair.unit;

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

/**
 * Parse the reddit unit flairs as images, saved locally
 * @author Allen
 *
 */

public class ParseUnitFlair {
	
	public static void main(String[] args) throws IOException{
		//self package folder, not meant to be run while packaged in jar
		File flairDest = new File("src/Library/flair/unit");
		flairDest.mkdirs();
		//38x56 flair resolution
		int fH = 38;
		int fW = 56;
		String unitCSSSource = "https://b.thumbs.redditmedia.com/GsKQ-nkhBdXNCYCYSndg1FnCgUS5CnwN_3iwSyZfr-c.css";
		String css = Jsoup.connect(unitCSSSource).execute().body();
		String unitFlairSource = extractFlairSource(css);
		//regex to match css which indicates the unit# and the shift of the main image
		Matcher m = Pattern.compile("a\\Q[href$=\"\\E/u(\\d*?)/\\Q\"]\\E.*?\\{background:0 -(\\d*?)em\\}").matcher(css);
		//read image of all flairs from reddit
		BufferedImage flairs = ImageIO.read(new URL(unitFlairSource).openStream());
		while(m.find()){
			//create new tmp buffered image to draw on, size = flair size
			BufferedImage f = new BufferedImage(fW, fH, BufferedImage.TYPE_INT_ARGB);
			Graphics g = f.getGraphics();
			//pretty much doing the same thing the css does, shift image draw
			g.drawImage(flairs, 0, Integer.parseInt(m.group(2))*-fH, null);
			ImageIO.write(f, "PNG", new File(flairDest.getAbsoluteFile()+"/u"+m.group(1)+".png"));
		}
	}
	private static String extractFlairSource(String css){
		Matcher m = Pattern.compile("\\Qa[href*=\"#I/Icons/\"]:after{background-image:url(\"\\E(.*?)\\Q\")!important}\\E").matcher(css);
		if(m.find()){
			return "https:"+m.group(1);
		}
		else{
			return "https://b.thumbs.redditmedia.com/MAnjfMe3Xqw5CPT3ATJrbXa5xECM5o-yZrevtAcWwTY.png";
		}
	}

}
