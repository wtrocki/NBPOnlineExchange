package pl.streamsoft.exchange.core.reader;

import pl.streamsoft.exchange.core.IParser;
import pl.streamsoft.exchange.core.model.ExchangeTable;
import pl.streamsoft.exchange.core.pref.Configuration;
import pl.streamsoft.exchange.core.pref.ConfigurationParser;

public class ActualReader implements IParser {

	private static ActualReader INSTANCE;
	private Configuration configuration;
	private final ModelParser parser = new ModelParser();

	private ActualReader() {
	}

	public static ActualReader getDefault() {
		return INSTANCE == null ? INSTANCE = new ActualReader() : INSTANCE;
	}

	public void initConfiguration() {
		configuration = ConfigurationParser.getDefault().createConfiguration();
	}

	public void createModel() {
		parser.setInput(getConfiguration().getUrlElements());
	}

	public ExchangeTable getExchangeTable() {
		if (parser != null)
			return parser.getModelCreator().getTable();
		return null;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
}
