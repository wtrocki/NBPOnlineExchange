package pl.streamsoft.exchange.core.reader;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.streamsoft.exchange.core.model.ExchangeEntity;
import pl.streamsoft.exchange.core.model.ExchangeTable;

public class ModelCreator implements ValidNodes {

	ArrayList<Document> inputs = new ArrayList<Document>();
	ExchangeTable table;

	public ModelCreator() {
	}

	public void setInput(Document doc) {
		inputs.add(doc);
	}

	public void createModel() {
		for (Document doc : inputs) {
			doc.normalizeDocument();
			Element element = doc.getDocumentElement();
			if (table == null) {
				createExchangeTable(element);
			}
			createPositions(element);
		}
	}

	private void createExchangeTable(Element element) {
		NodeList data = element.getElementsByTagName(DATE);
		if (data.getLength() == 1) {
			Node item = data.item(0).getChildNodes().item(0);
			String dataString = item.getNodeValue();
			table = new ExchangeTable(dataString);
		}
	}

	private void createPositions(Element element) {
		NodeList pozycje = element.getElementsByTagName(POSITION);
		int length = pozycje.getLength();
		if ((pozycje != null && length > 0)) {
			for (int i = 0; i < length; i++) {
				Element positionElement = (Element) pozycje.item(i);
				ExchangeEntity entity = getEntity(positionElement);
				table.getEntites().add(entity);
			}
		}
	}

	private ExchangeEntity getEntity(Element positionElement) {

		String name = getTextValue(positionElement, NAME);
		String code = getTextValue(positionElement, CURRENCY_CODE);
		String country = getTextValue(positionElement, COUNTRY);
		String exchangeRate = getTextValue(positionElement, EXCHANGE_RATE);
		int conversionRate = getIntValue(positionElement, CONVERSION_RATE);

		return new ExchangeEntity(name, code, country, exchangeRate,
				conversionRate);
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if the
	 * Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element element, String name) {
		return Integer.parseInt(getTextValue(element, name));
	}

	public ExchangeTable getTable() {
		return table;
	}
}
