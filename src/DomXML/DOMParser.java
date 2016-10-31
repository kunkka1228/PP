package DomXML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {
	private DocumentBuilderFactory builderFactory = DocumentBuilderFactory
			.newInstance();
	// Load and parse XML file into DOM
	private Document document = null;
	private String filename;
	private Document parse(String filePath) {
		
		Document document = null;
		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public DOMParser(String filename) {
		this.filename=filename;
		document = parse(filename);
	}

	public String[] getAttributeByTagName(String tagName,String attributeName) {
		Element rootElement = document.getDocumentElement();
		NodeList nodeList = rootElement.getElementsByTagName(tagName);
		String[] arr=new String[nodeList.getLength()];
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String attribute = element.getAttribute(attributeName);
				arr[i]=attribute;
			}
		}
		return arr;
	}
	
	public boolean deleteNodeByTagName(String tagName,String attributeName,String attributeValue){
		NodeList nodeList=document.getElementsByTagName(tagName);
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String value=element.getAttribute(attributeName);
				if(attributeValue.equals(value)){
					element.getParentNode().removeChild(element);
				}		
			}
		}		
		return writeToFile();
	}
	
	public boolean updateNodeAttributeByTagName(String tagName,String attributeName,String oldAttributeValue,String newAttributeValue){
		NodeList nodeList=document.getElementsByTagName(tagName);
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String value=element.getAttribute(attributeName);
				if(oldAttributeValue.equals(value)){
					element.setAttribute(attributeName, newAttributeValue);
				}		
			}
		}		
		return writeToFile();
	}
	
	public boolean addNode(String tagName,String[] attributeName,String[] attributeValue){
		Element rootElement = document.getDocumentElement();
		Element newel= document.createElement(tagName);
		for(int i=0;i<attributeName.length;i++){
			newel.setAttribute(attributeName[i], attributeValue[i]);
		}
		rootElement.appendChild(newel);
		return writeToFile();
	}
	
	private boolean writeToFile(){
		TransformerFactory factory=TransformerFactory.newInstance();
		try {
			Transformer t=factory.newTransformer();
			DOMSource xml=new DOMSource(document);//
			StreamResult s=new StreamResult(new File(filename));
			t.transform(xml, s);
		} catch (TransformerConfigurationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
			
		} catch (TransformerException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int[] getColorByID(String tagName, String id){
		int[] arr=new int[3];
		NodeList nodeList = document.getElementsByTagName(tagName);
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String attribute = element.getAttribute("id");
				if(id.equals(attribute)){
					arr[0]=Integer.parseInt(element.getAttribute("color-r"));
					arr[1]=Integer.parseInt(element.getAttribute("color-g"));
					arr[2]=Integer.parseInt(element.getAttribute("color-b"));
				}
			}
		}
		
		return arr;
	}
}