package pl.streamsoft.exchange.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilLogger {

	public static void logError(String msg) {
		Logger.getLogger("Exchange").log(Level.INFO, msg);
	}

}
