package util.rng;

import java.util.List;
import java.util.Random;
/**
 * collection of methods to choose things randomly
 * @author Allen
 *
 */
public class RandomLibs {
	public static <T> T SelectRandom(List<T> options){
		Random rand=new Random();
		return options.get(rand.nextInt(options.size()));
	}
	public static <T> T SelectRandom(T[] options){
		Random rand=new Random();
		return options[rand.nextInt(options.length)];
	}
}
