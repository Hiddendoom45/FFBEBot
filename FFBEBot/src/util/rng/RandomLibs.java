package util.rng;

import java.util.ArrayList;
import java.util.Random;
/**
 * collection of methods to choose things randomly
 * @author Allen
 *
 */
public class RandomLibs {
	public static String SelectRandom(ArrayList<String> options){
		Random rand=new Random();
		return options.get(rand.nextInt(options.size()));
	}
	public static String SelectRandom(String[] options){
		Random rand=new Random();
		return options[rand.nextInt(options.length)];
	}
	public static <T> T SelectRandom(T[] options){
		Random rand=new Random();
		return options[rand.nextInt(options.length)];
	}
}
