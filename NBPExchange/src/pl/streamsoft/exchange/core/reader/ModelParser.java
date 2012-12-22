package pl.streamsoft.exchange.core.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import pl.streamsoft.exchange.core.IParser;
import pl.streamsoft.exchange.core.pref.FetchURLElement;
import pl.streamsoft.exchange.core.util.UtilLogger;

public class ModelParser implements IParser {

	private final ModelCreator modelCreator;

	public ModelParser() {
		modelCreator = new ModelCreator();
	}

	public void setInput(ArrayList<FetchURLElement> elements) {
		for (FetchURLElement e : elements) {
			InputStream openStream;
			try {
				openStream = e.getUrl().openStream();
				if (openStream != null) {
					Document doc = parseXML(openStream);
					modelCreator.setInput(doc);
				} else {
					showError();
					UtilLogger.logError("Invalid stream for XML file");
					return;
				}
			} catch (Exception e1) {
				showError();
				UtilLogger.logError(e1.getMessage());
			}
		}
		modelCreator.createModel();
	}

	private void showError() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				MessageBox error = new MessageBox(new Shell(Display
						.getCurrent()));
				error.setMessage("B³¹d po³¹czenia z internetem");
				error.setText("£adowanie danych");
				error.open();
			}
		});
	}

	protected Document parseXML(InputStream is)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		return doc;
	}

	protected Document parseXML(File is) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		return doc;
	}

	public ModelCreator getModelCreator() {
		return modelCreator;
	}

}
