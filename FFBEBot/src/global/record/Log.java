package global.record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Log {
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
			String existing="";
			if(new File(Settings.saveSource).exists()){
				BufferedReader in=new BufferedReader(new FileReader(new File(Settings.saveSource)));
				while(in.ready()){
					existing+=in.readLine()+"\n";
				}
				in.close();
			}
			BufferedWriter out=new BufferedWriter(new FileWriter(new File(Settings.saveSource)));
			lock.acquire();
			out.write(existing);
			for(String s:log){
				out.append(s+"\n");
			}
			out.append(new SimpleDateFormat("[MM-dd HH:mm:ss]").format(new Date())+"[Log]log saved");
			log.clear();
			lock.release();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
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
	public static void logError(Exception e){
		String s=e.toString();
		for(StackTraceElement er:e.getStackTrace()){
			s+="\n\tat"+er.toString();
		}
		e.printStackTrace();
		log("ERROR",s);
		e.printStackTrace();
	}
	public static String getLog(int length){
		String out="";
		try {
			lock.acquire();
			for(int i=length<log.size()?log.size()-length:0;i<log.size();i++){
				out+=log.get(i)+"\n";
			}
			lock.release();
			if(length>log.size()){
				out=getSavedLog(length-log.size())+out;
			}
		} catch (InterruptedException e) {}
		return out;
	}
	public static String getSavedLog(int length){
		String existing="";
		try{
			if(new File(Settings.saveSource).exists()){
				BufferedReader in=new BufferedReader(new FileReader(new File(Settings.saveSource)));
				while(in.ready()){
					existing+=in.readLine()+"\n";
				}
				in.close();
			}
			String out="";
			String[] logs=existing.split("\n");
			for(int i=logs.length>length?logs.length-length:0;i<logs.length;i++){
				out+=logs[i]+"\n";
			}
			return out;
		}catch(Exception e){
		}
		return existing;
	}


}
