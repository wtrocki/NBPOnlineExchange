package pl.streamsoft.exchange.core.pref;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Element;

public class FetchURLElement extends BaseParsedElement {
	private URL elementURL;

	public FetchURLElement(Element element) throws MalformedURLException {
		super(element);
		createURL();
	}

	private void createURL() throws MalformedURLException {
		elementURL = new URL(value);
	}

	public URL getUrl() {
		return elementURL;
	}

	@Override
	public String toString() {
		return elementURL.toString();
	}

}
