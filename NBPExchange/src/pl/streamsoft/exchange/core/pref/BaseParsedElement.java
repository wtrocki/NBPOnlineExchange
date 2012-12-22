package pl.streamsoft.exchange.core.pref;

import org.w3c.dom.Element;

public class BaseParsedElement implements IParsedElement {

	String name, value;

	static enum ValidAtributes {
		name, value
	}

	public BaseParsedElement(Element element) {
		name = element.getAttribute(ValidAtributes.name.toString());
		value = element.getAttribute(ValidAtributes.value.toString());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return name + " = " + value;
	}

}