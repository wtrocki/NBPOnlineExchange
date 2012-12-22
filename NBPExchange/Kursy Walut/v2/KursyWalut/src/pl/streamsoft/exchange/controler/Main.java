package pl.streamsoft.exchange.controler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import pl.streamsoft.exchange.core.dba.query.CRUD;
import pl.streamsoft.exchange.core.model.ExchangeTable;
import pl.streamsoft.exchange.core.reader.ActualReader;
import pl.streamsoft.exchange.gui.AplicationComposite;
import pl.streamsoft.exchange.gui.init.SplashComposite;

public class Main {

	private static SplashComposite sps;

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Init splash mechanism.
		createSplash();
		// Init application mechanism
		initAplication();
	}

	private static void initAplication() {
		ActualReader ar = ActualReader.getDefault();
		ar.initConfiguration();
		sps.stepProgres();
		ar.createModel();
		sps.stepProgres();
		ExchangeTable exchangeTable = ar.getExchangeTable();
		CRUD.inst().store(exchangeTable);

		sps.stepProgres();
		AplicationComposite ap = new AplicationComposite();
		ap.createContents();
		sps.stepProgres();
		ap.setInput(exchangeTable);
		sps.dispose();
		ap.open();
	}

	private static void createSplash() {
		Display display = Display.getDefault();
		Shell splashShell = new Shell(display);
		sps = new SplashComposite(splashShell, SWT.NONE);
		sps.open();
	}
}
