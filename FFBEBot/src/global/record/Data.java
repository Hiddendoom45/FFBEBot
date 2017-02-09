package global.record;



import XML.Attribute;
import XML.Elements;

/**
 * Class for holding data variables related to preloading and it is the class used to represent te data for a user
 * @author Allen
 *
 */
public class Data {
	public static String redditO;
	public static String redditUnits;
	public static String exvicusO;
	public static String exvicusUnits;
	
	private String id;//userID
	
	
	public Data(String id){
		this.id=id;
	}
	public Data(Elements root){
		id=root.getAttribute(id).getValue();
		
	}
	public String getID(){
		return id;
	}
	public Elements parseToElements(){
		Elements root=new Elements("user");
		root.getAttributes().add(new Attribute("id",id));
		
		return root;
	}
	/**
	 * A wrapper for getting an array for an element easily, assuming the arrays is text separated by ','
	 * @param ele element to search for array
	 * @param setting name of the element whose text is the array
	 * @return
	 */
	@SuppressWarnings("unused")
	private String[] textArray(Elements ele,String setting){
		String[] out=new String[ele.getChilds(setting).size()];
		int i=0;
		for(Elements e:ele.getChilds(setting)){
			out[i]=e.getText();
			i++;
		}
		return out;
	}
	/**
	 * A wrapper to get String value for element without crashing process if it doesn't exist
	 * @param root Element you want to search for the element
	 * @param tagname name of element you want to get
	 * @return
	 */
	private static String getString(Elements root,String tagname){
		try{
			return root.getChilds(tagname).get(0).getText();
		}
		catch(IndexOutOfBoundsException e){
			Log.log("ERROR", "Invalid for element "+tagname);
			Log.logError(e);
			return "";
		}
	}
	/**
	 * A wrapper to get the boolean value for an element without crashing process if it doesn't exist
	 * @param normal default value if boolean value is not found
	 * @param ele Element you want to search for the element
	 * @param tagname name of element you want to get
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean getBooleanSetting(boolean normal,Elements ele,String tagname){
		if(ele.getChilds(tagname).size()<=0){
			return normal;
		}
		if(normal){
			return ele.getChilds(tagname).get(0).getText().equals("false")?false:true;
		}
		else{
			return ele.getChilds(tagname).get(0).getText().equals("true")?true:false;
		}
	}
	
	
	//static stuff in relation to preload stuff
	public static void setData(Elements preload){
		redditO=getString(preload,"redditOverview");
		redditUnits=getString(preload,"redditUnits");
		exvicusO=getString(preload,"exvicusOverview");
		exvicusUnits=getString(preload,"exvicusUnits");
	}
	public static Elements parseDataToElements(){
		Elements root=new Elements("preload");
		Elements current;
		
		current=new Elements("redditOverview").setText(redditO);
		root.add(current);
		
		current=new Elements("redditUnits").setText(redditUnits);
		root.add(current);
		
		current=new Elements("exvicusOverview").setText(exvicusO);
		root.add(current);
		
		current=new Elements("exvicusUnits").setText(exvicusUnits);
		root.add(current);
		
		return root;
	}
}
