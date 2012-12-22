package pl.streamsoft.exchange.core.pref;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pl.streamsoft.exchange.core.IParser;

public class ConfigurationParser implements IParser {

	static enum ValidNodes {
		fetchURL, config
	}

	public final static String XML_LOCATION = "resources/config.xml";
	private final static int ELEMENTS_INITIAL_SIZE = 5;
	private static ConfigurationParser INSTANCE;

	private final Vector<IParsedElement> parsedElements;

	private ConfigurationParser() {
		parsedElements = new Vector<IParsedElement>(ELEMENTS_INITIAL_SIZE);
	}

	/**
	 */
	public static synchronized ConfigurationParser getDefault() {
		return (INSTANCE == null) ? new ConfigurationParser() : INSTANCE;
	}

	public Vector<IParsedElement> getParsedElements() {
		return parsedElements;
	}

	public Configuration createConfiguration() {
		try {
			parseConfiguration();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Configuration(parsedElements);
	}

	/**
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * */
	public void parseConfiguration() throws ParserConfigurationException,
			SAXException, IOException {
		File file = new File(XML_LOCATION);
		Document doc = parseXML(file);
		NodeList childNodes = getURLs(doc);

		for (int s = 0; s < childNodes.getLength(); s++) {
			Node fstNode = childNodes.item(s);
			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
				addNewURI((Element) fstNode);
			}
		}

		NodeList confNodes = getConfs(doc);
		for (int s = 0; s < confNodes.getLength(); s++) {
			Node fstNode = confNodes.item(s);
			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
				addNewConf((Element) fstNode);
			}
		}
	}

	private Document parseXML(File file) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		return doc;
	}

	private NodeList getURLs(Document doc) {
		doc.getDocumentElement().normalize();
		String string = ValidNodes.fetchURL.toString();
		NodeList childNodes = doc.getElementsByTagName(string);
		return childNodes;
	}

	private NodeList getConfs(Document doc) {
		doc.getDocumentElement().normalize();
		String string = ValidNodes.config.toString();
		NodeList childNodes = doc.getElementsByTagName(string);
		return childNodes;
	}

	private void addNewURI(Element fstNode) throws MalformedURLException {
		getParsedElements().add(new FetchURLElement(fstNode));
	}

	private void addNewConf(Element fstNode) throws MalformedURLException {
		getParsedElements().add(new ConfElement(fstNode));
	}

}
