package pl.streamsoft.exchange.gui;

import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import pl.streamsoft.exchange.core.dba.query.CRUD;
import pl.streamsoft.exchange.core.model.ExchangeTable;
import pl.streamsoft.exchange.core.util.UtilLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

public class HistoryDialog extends Dialog {

	protected Shell shlHistoriaPobranychZasobw;
	private List listWidget;
	private java.util.List<ExchangeTable> findList;
	private ExchangeTable result;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public HistoryDialog(Shell parent, int style) {
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
		shlHistoriaPobranychZasobw.open();
		shlHistoriaPobranychZasobw.layout();
		Display display = getParent().getDisplay();
		while (!shlHistoriaPobranychZasobw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return null;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlHistoriaPobranychZasobw = new Shell(getParent(), SWT.CLOSE
				| SWT.TITLE | SWT.APPLICATION_MODAL);

		// Get the resolution
		Rectangle pDisplayBounds = shlHistoriaPobranychZasobw.getDisplay()
				.getBounds();
		int width = 340;
		int height = 250;
		// This formulae calculate the shell's Left ant Top
		int nLeft = (pDisplayBounds.width - width) / 2;
		int nTop = (pDisplayBounds.height - height) / 2;
		shlHistoriaPobranychZasobw.setSize(340, 279);
		// Set shell bounds,
		shlHistoriaPobranychZasobw.setBounds(nLeft, nTop, width, height);
		shlHistoriaPobranychZasobw.setText("Historia pobranych zasob\u00F3w");

		Button btnZaaduj = new Button(shlHistoriaPobranychZasobw, SWT.NONE);
		btnZaaduj.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = listWidget.getSelectionIndex();
				result = findList.get(selectionIndex);
				shlHistoriaPobranychZasobw.close();
			}
		});
		btnZaaduj.setBounds(246, 46, 68, 23);
		btnZaaduj.setText("Za\u0142aduj");

		Button btnZamknij = new Button(shlHistoriaPobranychZasobw, SWT.NONE);
		btnZamknij.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlHistoriaPobranychZasobw.close();
			}
		});
		btnZamknij.setBounds(246, 124, 68, 23);
		btnZamknij.setText("Zamknij");

		Label lblNewLabel = new Label(shlHistoriaPobranychZasobw, SWT.WRAP);
		lblNewLabel.setBounds(10, 10, 236, 37);
		lblNewLabel
				.setText("Tabele kurs\u00F3w walut pobrane podczas poprzednich uruchomie\u0144:");

		listWidget = new List(shlHistoriaPobranychZasobw, SWT.BORDER);
		listWidget.setBounds(10, 47, 217, 168);

		Button btnNewButton = new Button(shlHistoriaPobranychZasobw, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = listWidget.getSelectionIndex();
				if (selectionIndex != -1) {
					ExchangeTable exchangeTable = findList.get(selectionIndex);
					CRUD.inst().delete(exchangeTable);
					findList.remove(selectionIndex);
					listWidget.remove(selectionIndex);
				}
			}
		});
		btnNewButton.setBounds(246, 84, 68, 23);
		btnNewButton.setText("Usu\u0144");
		initDialog();
	}

	private void initDialog() {
		try {
			Query<ExchangeTable> query = Ebean.createQuery(ExchangeTable.class);
			findList = query.findList();
			initList(findList);
		} catch (Exception e) {
			UtilLogger.logError(e.getMessage());
		}
	}

	private void initList(java.util.List<ExchangeTable> findList) {
		for (int i = 0; i < findList.size(); i++) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			String dateString = formatter.format(findList.get(i)
					.getPublicationDate());
			listWidget.add(i + 1 + ". Kursy walut z dnia : " + dateString);
		}
	}

	public ExchangeTable getResult() {
		return result;
	}
}
