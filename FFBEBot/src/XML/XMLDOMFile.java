package XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import XML.Elements;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLDOMFile {
	private Document doc;
	public static void main(String []args) {
		XMLDOMFile xml= new XMLDOMFile();
			xml.ReadNewXMLFile(new File("/Users/Allen/Desktop/test.xml"));
			Elements element=new Elements("test");
			element.setAttributes(new ArrayList<Attribute>());
			element.getAttributes().add(new Attribute("test","useless"));
			element.getChilds().add(new Elements("randomstuffs","random etc."));
			xml.parseToDOM(element,(Element)xml.getDoc().getFirstChild());
			System.out.println(xml.createNewXMLFile(new File("/Users/Allen/Desktop/test.xml")));
			System.out.println(xml.toStringDocument());
		
			
		System.out.println(xml.toStringElement((Element)xml.getDoc().getDocumentElement()));
		
	}
	public XMLDOMFile(){
	}
	public XMLDOMFile(File file) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
		Document doc=dBuilder.parse(file);
		this.doc=doc;
		doc.getDocumentElement().normalize();
	}
	public boolean ReadNewXMLFile(File file){
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			return false;
		}
		Document doc;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException | IOException e) {
			return false;
		}
		this.doc= doc;
		doc.getDocumentElement().normalize();
		return true;
	}
	public boolean createNewXMLFile(File file){
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (TransformerConfigurationException e) {return false;}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {return false;}
		return true;//will only get this far if it successfully gets through writing the file
	}
	public boolean newDocument(){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {return false;}
		doc = docBuilder.newDocument();
		return true;
	}
	public NodeList getElementsbyName(String name){
		NodeList nList=doc.getElementsByTagName(name);
		return nList;
	}
	public String toStringDocument(){
		return toStringElement((Element)doc.getFirstChild());
	}
	public String toStringElement(Element element){
		String toString ="";
		toString+="Element:"+element.getNodeName()+"\n";
		if(element.hasAttributes()){
			NamedNodeMap atr=element.getAttributes();
			for(int i=0;i<atr.getLength();i++){
				toString+="Attribute:"+atr.item(i).getNodeName()+"="+atr.item(i).getNodeValue()+"\n";
			}
		}
		if(hasChildElement(element)){
			NodeList childs=element.getChildNodes();
			for(int i=0;i<childs.getLength();i++){
				if(childs.item(i).getNodeType()==Node.ELEMENT_NODE){
					toString+=toStringElement((Element)childs.item(i));
				}
			}
		}
		else if(!element.getTextContent().equals("")){
			toString+="Text:"+element.getTextContent()+"\n";
		}
		return toString;
	}
	public Elements parseToElements(Element element){
		Elements elements=new Elements(element.getNodeName());
		if(element.hasAttributes()){
			NamedNodeMap atr=element.getAttributes();
			ArrayList<Attribute> attributes= new ArrayList<Attribute>();
			for(int i=0;i<atr.getLength();i++){
				attributes.add(new Attribute(atr.item(i).getNodeName(),atr.item(i).getNodeValue()));
			}
		}
		if(hasChildElement(element)){
			elements.setChilds(new ArrayList<Elements>());
			ArrayList<Node> childs=getChildElements(element);
			for(Node n:childs){
				elements.getChilds().add(parseToElements((Element)n));
			}
		}
		else{
			elements.setText(element.getTextContent());
		}
		return elements;
	}
	public ArrayList<Elements> findElements(String name){
		NodeList elements=doc.getElementsByTagName(name);
		ArrayList<Elements> returnEle=new ArrayList<Elements>();
		for(int i=0;i<elements.getLength();i++){
			returnEle.add(parseToElements((Element)elements.item(i)));
		}
		return returnEle;
	}
	public void parseToDOM(Elements elements){
		Element root= doc.createElement(elements.getTagName());
		if(elements.getAttributes()!=null){
			for(Attribute a:elements.getAttributes()){
				root.setAttribute(a.getName(), a.getValue());
			}
		}
		if(elements.getChilds()!=null){
			for(Elements s:elements.getChilds()){
				root.appendChild(parseToDOM(s,root));
			}
		}
		doc.appendChild(root);
	}
	public Element parseToDOM(Elements elements,Element root){
		Element element=doc.createElement(elements.getTagName());
		if(elements.getAttributes()!=null){
			for(Attribute a:elements.getAttributes()){
				element.setAttribute(a.getName(), a.getValue());
			}
		}
		if(elements.getChilds()!=null){
			for(Elements s:elements.getChilds()){
				element.appendChild(parseToDOM(s,element));
			}
		}
		root.appendChild(element);
		return element;
		
	}
	public Document getDoc(){
		return doc;
	}
	//sets doc as null so that memory resources will be released
	public void clearDoc(){
		doc=null;
	}
	//checks to see if the child nodes contain a child element
	private boolean hasChildElement(Node node){
		NodeList childs=node.getChildNodes();
		for(int i=0;i<childs.getLength();i++){
			if(childs.item(i).getNodeType()==Node.ELEMENT_NODE){
				return true;
			}
		}
		return false;
	}
	//gets the child element nodes
	private ArrayList<Node> getChildElements(Node node){
		ArrayList<Node> childElement= new ArrayList<Node>();
		NodeList childs=node.getChildNodes();
		for(int i=0;i<childs.getLength();i++){
			if(childs.item(i).getNodeType()==Node.ELEMENT_NODE){
				childElement.add(childs.item(i));
			}
		}
		return childElement;
	}
	public boolean isDocNull(){
		if(doc==null){
			return true;
		}
		return false;
	}
  
  

}