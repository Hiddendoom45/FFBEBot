package global;

import java.util.Arrays;
import java.util.HashMap;

import global.record.Settings;

public class ArgumentParser {
	public static ArgContainer handleArguments(String raw){
		String[] array=raw.split(" ");
		HashMap<String,String[]> args=new HashMap<String,String[]>();
		String arg="";
		String values="";
		String command="";
		System.out.println(Arrays.toString(array));
		for(String s:array){
			if(s.startsWith(Settings.overrideArg)){
				if(!arg.equals("")){
					args.put(arg, values.substring(0, (values.length()>0?values.length()-1:0)).split(","));
				}
				arg=s.substring(1);
				values="";
			}
			else if(s.startsWith(Settings.overridePrefix)){
				command=s.substring(1);
			}
			else{
				values+=s+",";
			}
		}
		args.put(arg, values.substring(0, (values.length()>0?values.length()-1:0)).split(","));
		return new ArgContainer(args,raw,command);
	}
	public static class ArgContainer{
		public String command;
		public String raw;
		public HashMap<String,String[]> args=new HashMap<String,String[]>();
		ArgContainer(HashMap<String,String[]> args, String raw, String command){
			this.command=command;
			this.raw=raw;
			this.args=args;
		}
	}
}
