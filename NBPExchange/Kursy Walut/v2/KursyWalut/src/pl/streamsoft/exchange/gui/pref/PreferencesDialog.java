package pl.streamsoft.exchange.gui.pref;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import pl.streamsoft.exchange.core.pref.Configuration;
import pl.streamsoft.exchange.core.pref.FetchURLElement;
import pl.streamsoft.exchange.core.reader.ActualReader;

public class PreferencesDialog extends Dialog {

	protected Shell shlPreference;
	private Text txtTest;
	private final Configuration configuration;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PreferencesDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		configuration = ActualReader.getDefault().getConfiguration();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public void open() {
		createContents();
		shlPreference.open();
		shlPreference.layout();
		Display display = getParent().getDisplay();
		while (!shlPreference.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlPreference = new Shell(getParent(), SWT.DIALOG_TRIM
				| SWT.PRIMARY_MODAL);
		shlPreference.setMinimumSize(new Point(105, 33));
		shlPreference.setText("Preferencje");
		int x = 436;
		int y = 207;
		shlPreference.setSize(436, 401);
		Rectangle pDisplayBounds = shlPreference.getDisplay().getBounds();

		int nLeft = (pDisplayBounds.width - x) / 2;
		int nTop = (pDisplayBounds.height - y) / 2;

		shlPreference.setBounds(nLeft, nTop, x, y);

		Group grpAdresyPobierania = new Group(shlPreference, SWT.NONE);
		grpAdresyPobierania.setText("Adresy pobierania");
		grpAdresyPobierania.setBounds(10, 10, 409, 122);

		txtTest = new Text(grpAdresyPobierania, SWT.BORDER | SWT.MULTI);
		txtTest.setForeground(SWTResourceManager.getColor(25, 25, 112));
		txtTest.setBackground(SWTResourceManager.getColor(230, 230, 250));
		txtTest.setEditable(false);
		txtTest.setBounds(10, 20, 389, 92);

		if (configuration != null) {
			StringBuffer sb = new StringBuffer();
			for (FetchURLElement e : configuration.getUrlElements()) {
				sb.append("\n------- ").append(e.toString())
						.append(" -------\n");
			}
			txtTest.setText(sb.toString());
		}

		Button btnOdrzu = new Button(shlPreference, SWT.NONE);
		btnOdrzu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlPreference.dispose();
			}
		});
		btnOdrzu.setBounds(351, 148, 68, 23);
		btnOdrzu.setText("Zamknij");
	}
}
