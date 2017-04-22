package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import XML.Elements;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamConstants;

public class XMLStAXFile {
	private XMLStreamReader reader;
	private XMLStreamWriter writer;
	//variables held so they can be closed later on
	private FileWriter write;
	private FileReader read;
	private File file;
	private int wIndent=0;
	/**
	 * creates new object, does nothing else<p>
	 * invoke either {@link #readNewXMLFile(File)} or {@link #writeNewXMLFile(File)} to load file to read/write
	 * @see #XMLStAXFile(File)
	 */
	public XMLStAXFile() {
	}
	/**
	 * creates new object with specified file<p>
	 * invoke {@link #readXMLFile()} or {@link #writeXMLFile()} to read/write
	 * @param file >file to be saved within object
	 */
	public XMLStAXFile(File file){
		this.file=file;
	}
	/**
	 * sets up the reader to read a new XML file
	 * @param file used to read new XML file, is also saved to the object file variable
	 * @return returns false if something goes wrong, otherwise true
	 * @see #readXMLFile()
	 */
	public boolean readNewXMLFile(File file){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader eventReader;
		try {
			read=new FileReader(file);
			eventReader = factory.createXMLStreamReader(read);
		} catch (FileNotFoundException | XMLStreamException e) {return false;}
		this.reader=eventReader;
		this.file=file;
		return true;
	}
	/**
	 * sets up reader using file variable stored in object
	 * @return returns false if something goes wrong, otherwise true
	 * @see #readNewXMLFile(File)
	 */
	public boolean readXMLFile(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader eventReader;
		try {
			read=new FileReader(file);
			eventReader = factory.createXMLStreamReader(read);
		} catch (FileNotFoundException | XMLStreamException e) {return false;}
		this.reader=eventReader;
		return true;
	}
	/**
	 * sets up writer to write a new XML file<p>
	 * invoke {@link #startWriter()} to begin writing to the file
	 * @param file used to read new XML file, is also saved to the object file variable
	 * @return returns false if something goes wrong otherwise true
	 * @see #writeXMLFile()
	 */
	public boolean writeNewXMLFile(File file){
		XMLOutputFactory xof =  XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;
		try {
			write=new FileWriter(file+"write");
			writer = xof.createXMLStreamWriter(write);
		} catch (XMLStreamException | IOException e) {return false;}
		this.writer=writer;
		return true;
	}
	/**
	 * sets up writer to write a new XML file using the file variable stored in object<p>
	 * invoke {@link #startWriter()} to begin writing to the file
	 * @return returns false if something goes wrong, otherwise true
	 * @see #writeNewXMLFile(File)
	 */
	public boolean writeXMLFile(){
		XMLOutputFactory xof =  XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;
		try {
			write=new FileWriter(file+"write");
			writer = xof.createXMLStreamWriter(write);
		} catch (XMLStreamException | IOException e) {return false;}
		this.writer=writer;
		return true;
	}
	/**
	 * resets cursor API back to start of file, do each time you need to read from file
	 * @return returns false if something goes wrong, otherwise true
	 * @see #readXMLFile()
	 */
	public boolean resetReader(){
		return readXMLFile();
	}
	/**
	 * resets cursor API back to start of file, do each time you need to overwrite file
	 * @return returns false if something goes wrong, otherwise true
	 * @see #writeXMLFile()
	 */
	public boolean resetWriter(){
		return writeXMLFile();
	}
	/**
	 * parses the documents into the Elements model
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @return Elements object representing document
	 */
	public Elements parseDocToElements(){
		Elements returnEle=null;
		try {
			while(reader.hasNext()){
				switch(reader.getEventType()){
				case XMLStreamConstants.START_ELEMENT:
					if(returnEle==null){
						returnEle=new Elements(""+reader.getName());
						for(int i=0;i<reader.getAttributeCount();i++){
							returnEle.getAttributes().add(new Attribute(""+reader.getAttributeName(i),reader.getAttributeValue(i)));
						}
					}
					else{
						returnEle.getChilds().add(parseToElements());
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String text=reader.getText().trim();
					if(!text.isEmpty()){
						returnEle.setText(text);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					return returnEle;
				}
				reader.next();
			}
		} catch (XMLStreamException e) {}
		return null;
	}
	/**
	 * parses from the point whatever needs to be parsed to Elements<p>
	 * //does not support nestled elements<p> 
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @return Elements object representing document
	 * 
	 */
	private Elements parseToElements(){
		Elements returnEle=null;
		try {
			while(reader.hasNext()){
				switch(reader.getEventType()){
				case XMLStreamConstants.START_ELEMENT:
					if(returnEle==null){
						returnEle=new Elements(""+reader.getName());

						for(int i=0;i<reader.getAttributeCount();i++){
							returnEle.getAttributes().add(new Attribute(""+reader.getAttributeName(i),reader.getAttributeValue(i)));
						}
					}
					else{
						returnEle.getChilds().add(parseToElements());
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String text=reader.getText().trim();
					if(!text.isEmpty()){
						returnEle.setText(returnEle.getText()+text);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					return returnEle;
				}
				reader.next();
			}
		} catch (XMLStreamException e) {}
		return null;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, 
	 * {@link #resetReader()}<p>
	 * make sure to call {@link #endReader()} when all data has been read
	 * @param name >Name of tag you want to filter out
	 * @return Elements object array representing the tags with the name specified
	 * 
	 */
	public ArrayList<Elements> parseToElements(String name){
		ArrayList<Elements> returnEles= new ArrayList<Elements>();
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					if((reader.getName()+"").equals(name)){
						returnEles.add(parseToElements());
					}
				}
			}
		} catch (XMLStreamException e) {}
		return returnEles;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, 
	 * {@link #resetReader()}<p>
	 * make sure to call {@link #endReader()} when all data has been read
	 * @param atr Attribute variable (name,value) used to filter tags
	 * @return Elements object array representing tags with the exact attribute specified
	 */
	public ArrayList<Elements> parseToElements(Attribute atr){
		ArrayList<Elements> returnEles= new ArrayList<Elements>();
		try{
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean containsAtr=false;
					for(int i=0;i<reader.getAttributeCount();i++){
						if((reader.getAttributeName(i)+"").equals(atr.getName())&&reader.getAttributeValue(i).equals(atr.getValue())){
							containsAtr=true;
						}
					}
					if(containsAtr){
						returnEles.add(parseToElements());
					}
				}
			}
		} catch (XMLStreamException e) {}
		return returnEles;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, 
	 * {@link #resetReader()}<p>
	 * make sure to call {@link #endReader()} when all data has been read
	 * @param atrs Attribute variables (name,value) used to filter tags
	 * @return Elements object array representing tags with the exact attributes specified
	 */
	public ArrayList<Elements> parseToElements(ArrayList<Attribute> atrs){
		ArrayList<Elements> returnEles= new ArrayList<Elements>();
		try{
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean[] containsAtr=new boolean[atrs.size()];
					for(int i=0;i<reader.getAttributeCount();i++){
						for(int c=0;c<atrs.size();c++){
							if((reader.getAttributeName(i)+"").equals(atrs.get(c).getName())&&reader.getAttributeValue(i).equals(atrs.get(c).getValue())){
								containsAtr[c]=true;
							}
						}
					}
					boolean continues=true;
					for(boolean b:containsAtr){
						if(!b){
							continues=false;
						}
					}
					if(continues){
						returnEles.add(parseToElements());
					}
				}
			}
		}catch (XMLStreamException e) {}
		return returnEles;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, 
	 * {@link #resetReader()}<p>
	 * make sure to call {@link #endReader()} when all data has been read
	 * @param name >name you want to filter out
	 * @param atr Attribute variable (name,value) used to filter tags
	 * @return Elements object array representing all tags with specified name and attribute
	 */
	public ArrayList<Elements> parseToElements(String name,Attribute atr){
		ArrayList<Elements> returnEles= new ArrayList<Elements>();
		try{
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean containsAtr=false;
					for(int i=0;i<reader.getAttributeCount();i++){
						if((reader.getAttributeName(i)+"").equals(atr.getName())&&reader.getAttributeValue(i).equals(atr.getValue())){
							containsAtr=true;
						}
					}
					if(containsAtr&&(reader.getName()+"").equals(name)){
						returnEles.add(parseToElements());
					}
				}
			}
		} catch (XMLStreamException e) {}
		return returnEles;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, 
	 * {@link #resetReader()}<p>
	 * make sure to call {@link #endReader()} when all data has been read
	 * @param name >name you want to filter out
	 * @param atr Attribute variables (name,value) used to filter tags
	 * @return Elements object array representing all tags with specified name and attributes
	 */
	public ArrayList<Elements> parseToElements(String name,ArrayList<Attribute> atrs){
		ArrayList<Elements> returnEles= new ArrayList<Elements>();
		try{
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean[] containsAtr=new boolean[atrs.size()];
					for(int i=0;i<reader.getAttributeCount();i++){
						for(int c=0;c<atrs.size();c++){
							if((reader.getAttributeName(i)+"").equals(atrs.get(c).getName())&&reader.getAttributeValue(i).equals(atrs.get(c).getValue())){
								containsAtr[c]=true;
							}
						}
					}
					boolean continues=true;
					for(boolean b:containsAtr){
						if(!b){
							continues=false;
						}
					}
					if(continues&&(reader.getName()+"").equals(name)){
						returnEles.add(parseToElements());
					}
				}
			}
		}catch (XMLStreamException e) {}
		return returnEles;
	}
	/**
	 * ends reader <p>
	 * recommended such that resources are freed<p>
	 * invoke after all of the parsing is done
	 * @see #parseDocToElements()
	 * @see #parseToElements(String)
	 * @see #parseToElements(Attribute)
	 * @see #parseToElements(ArrayList)
	 * @see #parseToElements(String, Attribute)
	 * @see #parseToElements(String, ArrayList)
	 */
	public boolean endReader(){
		try {
			reader.close();
			read.close();
			return true;
		} catch (XMLStreamException | IOException e) {
			return false;
		}
	}
	/**
	 * starts writer<p>
	 * invoke after {@link #writeNewXMLFile(File)}, {@link #writeXMLFile()}, {@link #resetReader()}
	 * @see #writeNewXMLFile()
	 * @see #writeXMLFile()
	 * @see #resetWriter()
	 * @return false if something goes wrong, otherwise true
	 */
	public boolean startWriter(){
		try {
			writer.writeStartDocument();
		} catch (XMLStreamException e) {return false;}
		return true;
	}
	/**
	 * ends writer and closes document
	 * @return false if something goes wrong, otherwise true
	 */
	public boolean endWriter(){
		try {
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			write.close();
			//TimeUnit.SECONDS.sleep(30);//to determine if sleep is needed for windows
			if(file.exists()){
				Files.delete(file.toPath());
			}
			new File(file+"write").renameTo(file);
		} catch (XMLStreamException | IOException e) {}
		return true;
	}
	/**
	 * make sure to start writer {@link #startWriter()} 
	 * make sure to end writer {@link #endWriter()}
	 * @param element Element to write to file at the point
	 * @return false if something went wrong, otherwise true
	 */
	public boolean writeElement(Elements element){
		try {
			writer.writeCharacters("\n");
			for(int i=0;i<wIndent;i++){
				writer.writeCharacters("\t");
			}
			if(element.getChilds().size()>0||!element.getText().equals("")){
				writer.writeStartElement(element.getTagName());
			}
			else{
				writer.writeEmptyElement(element.getTagName());
			}
			if(element.getAttributes()!=null&&element.getAttributes().size()>0){
				for(Attribute atr:element.getAttributes()){
					if(!(atr.getValue()==null)){
						writer.writeAttribute(atr.getName(), atr.getValue());
					}
				}
			}
			wIndent++;
			if(element.getChilds()!=null&&element.getChilds().size()>0){
				for(Elements child:element.getChilds()){
					if(!writeElement(child)){
						return false;
					}
				}
			}
			else if(element.getText()!=null&&!element.getText().trim().isEmpty()){
				writer.writeCharacters("\n");
				for(int i=0;i<wIndent;i++){
					writer.writeCharacters("\t");
				}
				writer.writeCharacters(element.getText());
			}
			wIndent--;
			if(element.getChilds().size()>0||!element.getText().equals("")){
				writer.writeCharacters("\n");
				for(int i=0;i<wIndent;i++){
					writer.writeCharacters("\t");
				}
			writer.writeEndElement();
			}
		} catch (XMLStreamException e) {return false;}
		return true;
	}
	/**
	 * make sure to start writer {@link #startWriter()}
	 * make sure to end writer {@link #endWriter()}
	 * @param elements array object of elements to write to xml file at point
	 * @return false if something goes wrong, otherwise true
	 * @see #writeElement(Elements)
	 */
	public boolean writeElement(ArrayList<Elements> elements){
		for(Elements element:elements){
			if(!writeElement(element)){
				return false;
			}
		}
		return true;
	}
	/**
	 * make sure to start writer {@link #startWriter()}
	 * make sure to end writer {@link #endWriter()}
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @return false if something goes wrong, otherwise true
	 */
	public boolean writeFromDocument(){
		return writeElement(parseToElements());
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()} 
	 * @return String representing all the elements in document
	 */
	public String toStringDocument(){
		return toStringElement();
	}
	private String toStringElement(){
		int indent=0;
		String toString="";
		boolean end=false;
		try {
			while(reader.hasNext()&&!end){
				switch(reader.getEventType()){
				case XMLStreamConstants.START_ELEMENT:
					for(int i=0;i<indent;i++){
						toString+="    ";
					}
					toString+="TagName:"+reader.getName()+"\n";
					indent++;
					for(int i=0;i<reader.getAttributeCount();i++){
						for(int c=0;c<indent;c++){
							toString+="    ";
						}
						toString+="Attribute:"+reader.getAttributeLocalName(i)+"="+reader.getAttributeValue(i)+"\n";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String text=reader.getText().trim();
					if(!text.isEmpty()){
						for(int i=0;i<indent;i++){
							toString+="    ";
						}
						toString+="Text:"+text+"\n";
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					indent--;
					if(indent==0){
						end=true;
					}
					break;
				}

				reader.next();
			}
		} catch (XMLStreamException e) {}
		return toString;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @param name  >Name of tag you want to filter out
	 * @return String representing the tags with the name specified
	 */
	public String toStringElement(String name){
		String toString="";
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					if((reader.getName()+"").equals(name)){
						toString+=toStringElement();
					}
				}
			}
		} catch (XMLStreamException e) {}

		return toString;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @param atr Attribute variable (name,value) used to filter tags
	 * @return String representing tags with the exact attribute specified
	 */
	public String toStringElement(Attribute atr){
		String toString="";
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean containsAtr=false;
					for(int i=0;i<reader.getAttributeCount();i++){
						if((reader.getAttributeName(i)+"").equals(atr.getName())&&reader.getAttributeValue(i).equals(atr.getValue())){
							containsAtr=true;
						}
					}
					if(containsAtr){
						toString+=toStringElement();
					}
				}
			}
		} catch (XMLStreamException e) {}
		return toString;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @param atr Attribute variables (name,value) used to filter tags
	 * @return String representing tags with the exact attributes specified
	 */
	public String toStringElement(ArrayList<Attribute> atrs){
		String toString="";
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean[] containsAtr=new boolean[atrs.size()];
					for(int i=0;i<reader.getAttributeCount();i++){
						for(int c=0;c<atrs.size();c++){
							if((reader.getAttributeName(i)+"").equals(atrs.get(c).getName())&&reader.getAttributeValue(i).equals(atrs.get(c).getValue())){
								containsAtr[c]=true;
							}
						}
					}
					boolean continues=true;
					for(boolean b:containsAtr){
						if(!b){
							continues=false;
						}
					}
					if(continues){
						toString+=toStringElement();
					}
				}
			}
		} catch (XMLStreamException e) {}
		return toString;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @param name  >Name of tag you want to filter out
	 * @param atr Attribute variable (name,value) used to filter tags
	 * @return String representing all tags with specified name and attribute
	 */
	public String toStringElement(String name,Attribute atr){
		String toString="";
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean containsAtr=false;
					for(int i=0;i<reader.getAttributeCount();i++){
						if((reader.getAttributeName(i)+"").equals(atr.getName())&&reader.getAttributeValue(i).equals(atr.getValue())){
							containsAtr=true;
						}
					}
					if(containsAtr&&(""+reader.getName()).equals(name)){
						toString+=toStringElement();
					}
				}
			}
		} catch (XMLStreamException e) {}

		return toString;
	}
	/**
	 * make sure reader is initialized or reset by calling {@link #readNewXMLFile(File)}, {@link #readXMLFile()}, {@link #resetReader()}
	 * @param name  >Name of tag you want to filter out
	 * @param atrs Attribute variables (name,value) used to filter tags
	 * @return String representing all tags with specified name and attributes
	 */
	public String toStringElement(String name,ArrayList<Attribute> atrs){
		
		String toString="";
		try {
			while(reader.hasNext()){
				reader.next();
				if(reader.getEventType()==XMLStreamConstants.START_ELEMENT){
					boolean[] containsAtr=new boolean[atrs.size()];
					for(int i=0;i<reader.getAttributeCount();i++){
						for(int c=0;c<atrs.size();c++){
							if((reader.getAttributeName(i)+"").equals(atrs.get(c).getName())&&reader.getAttributeValue(i).equals(atrs.get(c).getValue())){
								containsAtr[c]=true;
							}
						}
					}
					boolean continues=true;
					for(boolean b:containsAtr){
						if(!b){
							continues=false;
						}
					}
					if(continues&&(reader.getName()+"").equals(name)){
						toString+=toStringElement();
					}
				}
			}
		} catch (XMLStreamException e) {}

		return toString;
	}

}
