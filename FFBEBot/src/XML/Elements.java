package XML;

import java.util.ArrayList;

import XML.Attribute;

public class Elements {
	private String tagName;
	private ArrayList<Attribute> attributes=new ArrayList<Attribute>();
	private String text="";
	private ArrayList<Elements> childs=new ArrayList<Elements>();
	/**
	 * Initializes with given tagname all others are intialized as null or ""
	 * @param tagName name of the tag you want to name the element
	 */
	public Elements(String tagName) {
		this.tagName=tagName;
	}
	/**
	 * Intiallizes with given tagname and attribute all others are initialized as null or ""
	 * @param tagName name of the tag you want to name the element
	 * @param attributes array of attributes to initialize this with
	 */
	public Elements(String tagName, ArrayList<Attribute> attributes){
		this.tagName=tagName;
		this.attributes=attributes;
	}
	public Elements(String tagName, Attribute[] atr){
		this.tagName=tagName;
		attributes=new ArrayList<Attribute>();
		for(Attribute a:atr){
			attributes.add(a);
		}
	}
	public Elements(String tagName, String text){
		this.tagName=tagName;
		this.text=text;
	}
	public Elements(String tagName, ArrayList<Attribute> attributes, String text){
		this.tagName=tagName;
		this.attributes=attributes;
		this.text=text;
	}public Elements(String tagName, Attribute[] atr, String text){
		this.tagName=tagName;
		attributes=new ArrayList<Attribute>();
		for(Attribute a:atr){
			attributes.add(a);
		}
		this.text=text;
	}
	public String getTagName() {
		return tagName;
	}
	public Elements setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	public String getText() {
		return text;
	}
	public Elements setText(String text) {
		if(text==null){
			this.text="";
		}
		else{
			this.text = text;
		}
		return this;
	}
	public ArrayList<Attribute> getAttributes() {
		if(attributes==null){
			attributes=new ArrayList<Attribute>();
		}
		return attributes;
	}
	public Attribute getAttribute(String name){
		for(Attribute a:attributes){
			if(a.getName().equals(name)){
				return a;
			}
		}
		return null;
	}
	public Elements addAttribute(Attribute attribute){
		this.attributes.add(attribute);
		return this;
	}
	public Elements setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
		return this;
	}
	public ArrayList<Elements> getChilds() {
		if(childs==null){
			childs=new ArrayList<Elements>();
		}
		return childs;
	}
	public ArrayList<Elements> getChilds(String name){
		ArrayList<Elements> children= new ArrayList<Elements>();
		for(Elements child:childs){
			if(child.getTagName().equals(name)){
				children.add(child);
			}
			else if(child.getChilds().size()>0){
				ArrayList<Elements> childrens=child.getChilds(name);
				for(Elements ele:childrens){
					children.add(ele);
				}
			}
		}
		return children;
	}
	public ArrayList<Elements> getChilds(Attribute atr){
		ArrayList<Elements> children= new ArrayList<Elements>();
		for(Elements child:childs){
			boolean containsAtr=false;
			for(Attribute catr:child.getAttributes()){
				if(catr.getName().equals(atr.getName())&&catr.getValue().equals(atr.getValue())){
					containsAtr=true;
				}
			}
			if(containsAtr){
				children.add(child);
			}
			else if(child.getChilds().size()>0){
				ArrayList<Elements> childrens=child.getChilds(atr);
				for(Elements ele:childrens){
					children.add(ele);
				}
			}
		}
		return children;
	}
	public ArrayList<Elements> getChilds(String name, Attribute atr){
		ArrayList<Elements> children= new ArrayList<Elements>();
		for(Elements child:childs){
			boolean containsAtr=false;
			for(Attribute catr:child.getAttributes()){
				if(catr.getName().equals(atr.getName())&&catr.getValue().equals(atr.getValue())){
					containsAtr=true;
				}
			}
			if(containsAtr&&child.getTagName().equals(name)){
				children.add(child);
			}
			else if(child.getChilds().size()>0){
				ArrayList<Elements> childrens=child.getChilds(atr);
				for(Elements ele:childrens){
					children.add(ele);
				}
			}
		}
		return children;
	}
	public Elements setChilds(ArrayList<Elements> childs) {
		this.childs = childs;
		return this;
	}
	public void add(Elements child){
		childs.add(child);
	}
	public String toString(){
		String s="Name:"+tagName+"\n";
		if(attributes!=null&&attributes.size()>0){
			for(Attribute atr:attributes){
				s+="Attribute:"+atr.getName()+"="+atr.getValue()+"\n";
			}
		}
		s+="Text:"+text+"\n";
		if(childs!=null&&childs.size()>0){
			for(Elements ele:childs){
				s+="child:\n"+ele;
			}
		}
		
		return s;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((childs == null) ? 0 : childs.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elements other = (Elements) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (childs == null) {
			if (other.childs != null)
				return false;
		} else if (!childs.equals(other.childs))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	

}
