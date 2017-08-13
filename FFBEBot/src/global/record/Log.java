package global.record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import googleutil.drive.DataEnum;
import googleutil.drive.DriveFile;
import googleutil.drive.DriveManager;
/**
 * Class that records and logs everything
 * @author Allen
 *
 */
public class Log {
	public static final String LogSource="FFBEBotLog";
	private static ArrayList<String> log=new ArrayList<String>();
	private static boolean setup=false;
	private static Semaphore lock=new Semaphore(1);
	public static void setup(){
		if(setup){
			return;
		}
		Runnable save=new Runnable(){
			public void run(){
				save();
			}
		};
		Settings.executor.scheduleAtFixedRate(save, 1, 6, TimeUnit.HOURS);
		setup=true;
	}
	public static void save(){
		try{
			lock.acquire();
			BufferedWriter out=new BufferedWriter(new FileWriter(new File(Settings.saveSource+"write")));
			if(new File(Settings.saveSource).exists()){
				BufferedReader in=new BufferedReader(new FileReader(new File(Settings.saveSource)));
				while(in.ready()){
					out.append(in.readLine()+"\n");
				}
				in.close();
			}
			else{
				new File(Settings.saveSource).createNewFile();
			}
			for(String s:log){
				out.append(s+"\n");
			}
			out.append(new SimpleDateFormat("[MM-dd HH:mm:ss]").format(new Date())+"[Log]log saved");
			out.close();
			Files.delete(new File(Settings.saveSource).toPath());
			new File(Settings.saveSource+"write").renameTo(new File(Settings.saveSource));
			log.clear();
		}
		catch(Exception e){
			Log.logError(e);
		}
		finally{
			lock.release();
		}
		DriveManager.update(new DriveFile(Log.LogSource,DataEnum.LogSource.id));
	}
	/**
	 * archives the log file for future reference so that it isn't referenced again 
	 */
	public static void archive(){
		save();
		log.clear();
		String name=Log.LogSource+"Final-"+new SimpleDateFormat("[yyyy-MM-dd-HH]").format(new Date());//Name based on time
		int i=0;
		while(new File(name).exists()){
			i++;
			if(i==0){
				name=name+i;
			}
			else{
				name=name.subSequence(0, name.length()-1)+""+i;
			}
		}
		try{
			lock.acquire();
			new File(Log.LogSource).renameTo(new File(name));
		}
		catch(Exception e){
			logError(e);
		}
		finally{
			lock.release();
		}
	}
	/**
	 * competely clears and deletes the log
	 */
	public static void clear(){
		log.clear();
		try {
			lock.acquire();
			Files.delete(new File(LogSource).toPath());
		} catch (InterruptedException | IOException e) {
			//although log is cleared, this will cause message to be outputted to console and for viewing cause log isn't cleared
			log("ERROR", "error clearing log");
			logError(e);
		}
		finally{
			lock.release();
		}
		
	}
	public static void log(String type, String msg){
		try{
			lock.acquire();
			String time=new SimpleDateFormat("HH:mm:ss").format(new Date());
			String out="["+time+"]"+"["+type+"]"+msg;
			System.out.println(out);
			log.add(out);
			lock.release();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void logShortError(Exception e,int lines){
		String s=e.toString();
		int i=0;
		for(StackTraceElement er:e.getStackTrace()){
			if(i==lines){
				break;
			}
			s+="\n\tat "+er.toString();
			i++;
		}
		e.printStackTrace();
		log("ERROR",s);
	}
	public static void logError(Exception e){
		logShortError(e,e.getStackTrace().length);
	}
	public static String getLog(int length,int start){
		String out="";
		try {
			lock.acquire();
			for(int i=length+start<log.size()?log.size()-length-start:0;i<log.size();i++){
				if(i<log.size()-start){
					out+=log.get(i)+"\n";
				}
			}
			lock.release();
			if(length+start>log.size()){
				out=getSavedLog((start>log.size()?length:length-(log.size()-start)),(start>log.size()?start-log.size():0))+out;
			}
		} catch (InterruptedException e) {}
		return out;
	}
	public static String getSavedLog(int length,int start){
		String existing="";
		try{
			StringBuilder build=new StringBuilder(); 
			if(new File(Settings.saveSource).exists()){
				BufferedReader in=new BufferedReader(new FileReader(new File(Settings.saveSource)));
				while(in.ready()){
					build.append(in.readLine()+"\n");
				}
				in.close();
			}
			existing=build.toString();
			String out="";
			String[] logs=existing.split("\n");
			for(int i=logs.length>length+start?logs.length-length-start:0;i<logs.length;i++){
				if(i<logs.length-start){
					out+=logs[i]+"\n";
				}
			}
			return out;
		}catch(Exception e){
		}
		return existing;
	}


}
