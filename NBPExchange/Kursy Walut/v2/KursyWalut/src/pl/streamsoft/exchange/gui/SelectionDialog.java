package pl.streamsoft.exchange.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * TODO Miss this functionality. To consider on third stage
 * 
 * @author troken
 * 
 */
public class SelectionDialog extends Dialog {

	protected Object result;
	protected Shell shlZapiszListeWybranych;
	private Text txtCzyToPrzydatne;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public SelectionDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlZapiszListeWybranych.open();
		shlZapiszListeWybranych.layout();
		Display display = getParent().getDisplay();
		while (!shlZapiszListeWybranych.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlZapiszListeWybranych = new Shell(getParent(), SWT.CLOSE | SWT.TITLE);
		shlZapiszListeWybranych.setSize(450, 321);
		shlZapiszListeWybranych.setText("Zapisz liste wybranych kurs\u00F3w");

		Group grpListyWybranychKursw = new Group(shlZapiszListeWybranych,
				SWT.NONE);
		grpListyWybranychKursw.setText("Zestawy kurs\u00F3w walut");
		grpListyWybranychKursw.setBounds(10, 58, 316, 191);

		List list = new List(grpListyWybranychKursw, SWT.BORDER);
		list.setBounds(10, 23, 296, 158);

		txtCzyToPrzydatne = new Text(shlZapiszListeWybranych, SWT.BORDER);
		txtCzyToPrzydatne.setText("TODO");
		txtCzyToPrzydatne.setBounds(20, 21, 306, 19);

		Label label = new Label(shlZapiszListeWybranych, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label.setBounds(10, 258, 424, 2);

		Button okButton = new Button(shlZapiszListeWybranych, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		okButton.setBounds(292, 266, 68, 23);
		okButton.setText("Wybierz");

		Button btnNewButton = new Button(shlZapiszListeWybranych, SWT.NONE);
		btnNewButton.setBounds(339, 19, 95, 23);
		btnNewButton.setText("Dodaj");

		Button btnUsu = new Button(shlZapiszListeWybranych, SWT.NONE);
		btnUsu.setBounds(339, 75, 95, 23);
		btnUsu.setText("Usu\u0144 zaznaczone");

		Button btnAnuluj = new Button(shlZapiszListeWybranych, SWT.NONE);
		btnAnuluj.setBounds(366, 266, 68, 23);
		btnAnuluj.setText("Anuluj");
	}
}
