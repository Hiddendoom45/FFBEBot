package util.unit.exvius;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import global.record.Log;
import util.Lib;
/**
 * Stores basic awakening info
 * @author Allen
 *
 */
public class AwakenInfo {
	//materials
	public final AwakenMat[] mats;
	//matchers for each type of material
	private static final Matcher matPat = Pattern.compile("([^\\n]*?) \\(([\\d]+)\\)").matcher("");
	private static final Matcher gilPat = Pattern.compile("([\\d,]+)").matcher("");
	private static final Matcher prismPat = Pattern.compile("([^\\n]*?)'s Prism").matcher("");
	public AwakenInfo(String vals){
		String[] mats = vals.split("\n");
		this.mats = new AwakenMat[mats.length];
		for(int i = 0;i<mats.length;i++){
			matPat.reset(mats[i].trim());
			gilPat.reset(mats[i].trim());
			prismPat.reset(mats[i].trim());
			if(matPat.matches()){
				this.mats[i] = new AwakenMat(matPat.group(1),Integer.parseInt(matPat.group(2)));
			}
			else if(gilPat.matches()){
				this.mats[i] = new AwakenMat("Gil",Lib.extractNumber(gilPat.group(1),-1));
			}
			else if(prismPat.matches()){
				this.mats[i] = new AwakenMat(prismPat.group(0),1);
				this.mats[i].prism = true;
			}
			else{
				this.mats[i] = new AwakenMat(mats[i],-1);
				this.mats[i].nonMatch=true;
				Log.log("ERROR", "Warning mat does not match regexes "+mats[i]);
			}
		}
	}
	@Override
	public String toString(){
		return Arrays.stream(mats).map(m -> m.toString()).collect(Collectors.joining(", "));
	}
	public class AwakenMat{
		private boolean prism = false;
		private boolean nonMatch = false;
		public final String name;
		public final int count;
		public AwakenMat(String name, int count){
			this.name=name;
			this.count=count;
		}
		public boolean isPrism(){
			return prism;
		}
		public boolean nonMatch(){
			return nonMatch;
		}
		@Override
		public String toString(){
			if(prism){
				return name+" (1)";
			}
			else if(nonMatch){
				return name;
			}
			else{
				return name+" ("+count+")";
			}
		}
	}
}
