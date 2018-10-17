package commands.overide;

import java.util.HashMap;
import java.util.stream.Collectors;

import Library.summon.banner.Banner;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Lib;
import util.rng.RNGTester;

public class RateTest extends OverrideGenerics {

	@Override
	public void action(HashMap<String, String[]> args, MessageReceivedEvent event) {
		if(args.containsKey("type")&&args.containsKey("num")){
			int pulls = Integer.parseInt(args.get("num")[0]);
			Banner banner = Banner.valueOf(args.get("banner")[0]);
			switch(args.get("type")[0]){
			case "pull" : Lib.sendMessage(event, RNGTester.testPullRates(pulls, banner).stream().collect(Collectors.joining("\n")));
			break;
			case "pull11" : Lib.sendMessage(event, RNGTester.test11PullRates(pulls, banner).stream().collect(Collectors.joining("\n")));
			break;
			case "base5" : Lib.sendMessage(event, RNGTester.testBase5PullRates(pulls, banner).stream().collect(Collectors.joining("\n")));
			default : break;
			}
		}
	}

	@Override
	public void help(MessageReceivedEvent event) {
		String s = "ratetest -type [pull|pull11|base5] -num [#] -banner [banner]";
		Lib.sendMessage(event, s);
	}

}
