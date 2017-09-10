package global;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import global.record.Log;
import global.record.SaveSystem;
import global.record.Settings;
import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;

/**
 * restarts the bot periodically to prevent detection as virus?(stupid dad)
 * @author Allen
 *
 */
public class Restarter {
	public static int sleep=60;
	public static void setup(){
		Runnable check=new Runnable(){//potential restart(1/10 chance every hour)
			public void run(){
				Random rand=new Random();
				if(rand.nextInt(4)==0){
					int sleep=rand.nextInt(14400);//time during hour to start sleep
					Restarter.sleep(sleep);
					Restarter.sleep=rand.nextInt(100)+10;//amount of time to sleep for
				}
			}
		};
		Settings.executor.scheduleWithFixedDelay(check, 1, 1, TimeUnit.HOURS);
	}
	/**
	 * disconnects the bot for specified period of time
	 * @param time time till disconnect
	 */
	public static void sleep(int time){
		Runnable sleeper=Restarter.getSleeper();
		Settings.executor.schedule(sleeper, time, TimeUnit.SECONDS);
	}
	public static Runnable getSleeper(){
		return new Runnable(){
			public void run(){
				Log.log("system", "Bot is sleeping for "+Restarter.sleep+" secounds");
				Main.shutdown();
				try {
					Restarter.SleepActivity();
					TimeUnit.SECONDS.sleep(sleep);
					//Main.startup();
				} catch (Exception e) {
					Log.logError(e);
				}

			}
		};
	}
	public static void SleepActivity(){
		SaveSystem.pushUserData();
		DriveManager.download(new DriveFile(Log.LogSource,DataEnum.LogSource.id));
	}
}


