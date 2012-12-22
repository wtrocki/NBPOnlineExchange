package pl.streamsoft.exchange.core.pref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class Configuration {

	public final static String OFFLINE_IF_NO_CONNECTION = "offline";

	/**
	 * @uml.property name="urlElements"
	 */
	private final ArrayList<FetchURLElement> urlElements;
	private final HashMap<String, String> configurationElements;

	public Configuration(Vector<IParsedElement> parsedElements) {
		urlElements = new ArrayList<FetchURLElement>();
		configurationElements = new HashMap<String, String>(2);

		for (IParsedElement element : parsedElements) {
			if (element instanceof FetchURLElement) {
				urlElements.add((FetchURLElement) element);
			} else if (element instanceof ConfElement) {
				ConfElement confElement = (ConfElement) element;
				if (confElement.getName().equalsIgnoreCase(
						OFFLINE_IF_NO_CONNECTION))
					configurationElements.put(OFFLINE_IF_NO_CONNECTION,
							confElement.getValue());
			} else {
				System.out.print("Configuration: Invalid configuration file");
			}
		}
	}

	/**
	 * Getter of the property <tt>urlElements</tt>
	 * 
	 * @return Returns the urlElements.
	 * @uml.property name="urlElements"
	 */
	public ArrayList<FetchURLElement> getUrlElements() {
		return urlElements;
	}

	public String getOFFLINE_IF_NO_CONNECTION() {
		return configurationElements.get(OFFLINE_IF_NO_CONNECTION);
	}
}
