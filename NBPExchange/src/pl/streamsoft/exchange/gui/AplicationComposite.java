package pl.streamsoft.exchange.gui;

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import pl.streamsoft.exchange.core.dba.query.SelectionsManager;
import pl.streamsoft.exchange.core.model.ExchangeEntity;
import pl.streamsoft.exchange.core.model.ExchangeTable;
import pl.streamsoft.exchange.gui.export.FileDialogUtil;
import pl.streamsoft.exchange.gui.pref.PreferencesDialog;

//FIXME Fix mess, split composite to 2 views
public class AplicationComposite {

	protected Shell shlCurrenct;
	private Table resultTable;
	private ExchangeTable tableModel;
	private Table inputTable;
	private TableColumn tblclmnName;
	private TableColumn tblclmnCurrencyCode;
	private TableColumn tblclmnNewColumn;
	private TableColumn tblclmnNewColumn_1;
	private Text filterText;
	private Clipboard cb;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AplicationComposite window = new AplicationComposite();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AplicationComposite() {
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shlCurrenct.open();
		shlCurrenct.layout();
		while (!shlCurrenct.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void createContents() {
		shlCurrenct = new Shell(SWT.CLOSE);
		shlCurrenct.setImage(SWTResourceManager.getImage("images/mainico.gif"));
		int x = 630;
		int y = 467;
		shlCurrenct.setSize(x, y);
		// Get the resolution
		Rectangle pDisplayBounds = shlCurrenct.getDisplay().getBounds();

		// This formulae calculate the shell's Left ant Top
		int nLeft = (pDisplayBounds.width - x) / 2;
		int nTop = (pDisplayBounds.height - y) / 2;

		// Set shell bounds,
		shlCurrenct.setBounds(nLeft, nTop, x, y);

		shlCurrenct.setText("Kalkulator Walut");
		shlCurrenct.setLayout(new GridLayout(3, false));
		cb = new Clipboard(shlCurrenct.getDisplay());

		Menu menu = new Menu(shlCurrenct, SWT.BAR);
		shlCurrenct.setMenuBar(menu);

		MenuItem mntmMain = new MenuItem(menu, SWT.CASCADE);
		mntmMain.setText("Plik");

		Menu menu_1 = new Menu(mntmMain);
		mntmMain.setMenu(menu_1);

		MenuItem mntmReloadData = new MenuItem(menu_1, SWT.NONE);
		mntmReloadData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearGUI();
			}
		});
		mntmReloadData.setText("Nowy arkusz");

		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialogUtil.openTxtFileDialog(shlCurrenct, resultTable);
			}
		});
		mntmNewItem.setText("Exportuj");

		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlCurrenct.getDisplay().close();
			}
		});
		mntmExit.setText("Wyj\u015Bcie");

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Opcje");

		Menu menu_4 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_4);

		MenuItem mntmZaadujWpisz = new MenuItem(menu_4, SWT.NONE);
		mntmZaadujWpisz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HistoryDialog historyDialog = new HistoryDialog(shlCurrenct,
						SWT.NONE);
				historyDialog.open();
				tableModel = historyDialog.getResult();
				if (tableModel != null)
					clearGUI();
			}
		});
		mntmZaadujWpisz.setText("Historia");

		MenuItem mntmPreferences = new MenuItem(menu_4, SWT.NONE);
		mntmPreferences.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PreferencesDialog preferences = new PreferencesDialog(
						shlCurrenct, SWT.NONE);
				preferences.open();
			}
		});
		mntmPreferences.setText("Preferencje");

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Pomoc");

		Menu menu_2 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutDialog ab = new AboutDialog(shlCurrenct, SWT.NONE);
				ab.open();
			}
		});
		mntmAbout.setText("O programie");

		Group grpListaWalutObcych = new Group(shlCurrenct, SWT.NONE);
		grpListaWalutObcych.setText("Lista walut obcych");
		GridData gd_grpListaWalutObcych = new GridData(SWT.FILL, SWT.FILL,
				false, false, 1, 2);
		gd_grpListaWalutObcych.heightHint = 358;
		gd_grpListaWalutObcych.widthHint = 180;
		grpListaWalutObcych.setLayoutData(gd_grpListaWalutObcych);
		grpListaWalutObcych.setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(grpListaWalutObcych, SWT.NONE);
		lblNewLabel.setText("&Filtruj elementy");

		filterText = new Text(grpListaWalutObcych, SWT.BORDER);
		filterText.setBackground(SWTResourceManager.getColor(211, 211, 211));
		GridData gd_text = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_text.widthHint = 76;
		filterText.setLayoutData(gd_text);
		filterText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				inputTable.removeAll();
				initTable(((Text) e.widget).getText());
			}
		});

		Label lblDodajDoLisy = new Label(grpListaWalutObcych, SWT.NONE);
		lblDodajDoLisy.setText("Dodaj do listy");

		Button btnNewButton = new Button(grpListaWalutObcych, SWT.FLAT
				| SWT.CENTER);
		btnNewButton.setText("Dodaj -->");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = inputTable.getSelection();
				for (TableItem ti : selection) {
					ExchangeEntity exchangeEntity = (ExchangeEntity) ti
							.getData();
					TableItem item = new TableItem(resultTable, SWT.NONE);
					item.setText(0, exchangeEntity.getFixedCurrencyName());
					item.setText(1, exchangeEntity.getCurrencyCode());
					item.setText(2, exchangeEntity.getConversionRate() + "");
					item.setText(3, exchangeEntity.getExchangeRate() + "");
					SelectionsManager.inst().add(exchangeEntity);
				}
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.heightHint = 25;
		gd_btnNewButton.widthHint = 77;
		btnNewButton.setLayoutData(gd_btnNewButton);

		inputTable = new Table(grpListaWalutObcych, SWT.BORDER | SWT.MULTI);
		inputTable.setBackground(SWTResourceManager.getColor(211, 211, 211));
		inputTable.setForeground(SWTResourceManager.getColor(25, 25, 112));
		GridData gd_listOfElements = new GridData(SWT.LEFT, SWT.TOP, false,
				false, 2, 1);
		gd_listOfElements.verticalIndent = 5;
		gd_listOfElements.widthHint = 146;
		gd_listOfElements.heightHint = 294;
		inputTable.setLayoutData(gd_listOfElements);
		new Label(shlCurrenct, SWT.NONE);

		Group grpKursWybranychWalut = new Group(shlCurrenct, SWT.NONE);
		grpKursWybranychWalut.setText("Kurs wybranych walut");
		grpKursWybranychWalut.setLayout(new GridLayout(6, false));
		GridData gd_grpKursWybranychWalut = new GridData(SWT.FILL, SWT.FILL,
				false, false, 1, 2);
		gd_grpKursWybranychWalut.heightHint = 370;
		gd_grpKursWybranychWalut.widthHint = 400;
		grpKursWybranychWalut.setLayoutData(gd_grpKursWybranychWalut);

		resultTable = new Table(grpKursWybranychWalut, SWT.BORDER | SWT.CHECK
				| SWT.FULL_SELECTION);
		resultTable.setBackground(SWTResourceManager.getColor(211, 211, 211));
		GridData gd_resultTable = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 6, 1);
		gd_resultTable.widthHint = 365;
		gd_resultTable.heightHint = 321;
		resultTable.setLayoutData(gd_resultTable);
		resultTable.setForeground(SWTResourceManager.getColor(25, 25, 112));
		resultTable.setLinesVisible(true);
		resultTable.setHeaderVisible(true);

		tblclmnName = new TableColumn(resultTable, SWT.NONE);
		tblclmnName.setWidth(161);
		tblclmnName.setText("Nazwa");

		tblclmnCurrencyCode = new TableColumn(resultTable, SWT.NONE);
		tblclmnCurrencyCode.setWidth(60);
		tblclmnCurrencyCode.setText("Kod");

		tblclmnNewColumn = new TableColumn(resultTable, SWT.NONE);
		tblclmnNewColumn.setWidth(80);
		tblclmnNewColumn.setText("Przelicznik");

		tblclmnNewColumn_1 = new TableColumn(resultTable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(79);
		tblclmnNewColumn_1.setText("Kurs");

		Menu menu_3 = new Menu(resultTable);
		resultTable.setMenu(menu_3);

		MenuItem kopiujwItem_1 = new MenuItem(menu_3, SWT.NONE);
		kopiujwItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tableItem = resultTable.getSelection();
				StringBuffer text = new StringBuffer(tableItem[0].getText()
						+ " ").append(tableItem[0].getText(1) + " ")
						.append(tableItem[0].getText(2) + " ")
						.append(tableItem[0].getText(3) + " ");
				TextTransfer textTransfer = TextTransfer.getInstance();
				cb.setContents(new Object[] { text.toString() },
						new Transfer[] { textTransfer });
			}
		});
		kopiujwItem_1.setText("Kopiuj");

		MenuItem mntmWyczyListe = new MenuItem(menu_3, SWT.NONE);
		mntmWyczyListe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearGUI();
			}
		});
		mntmWyczyListe.setText("Wyczyść liste");
		new Label(grpKursWybranychWalut, SWT.NONE);
		new Label(grpKursWybranychWalut, SWT.NONE);
		new Label(grpKursWybranychWalut, SWT.NONE);

		Button upbutton = new Button(grpKursWybranychWalut, SWT.FLAT
				| SWT.CENTER);
		upbutton.setSelection(true);
		upbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = resultTable.getItems();
				int itemCount = resultTable.getItemCount();
				for (int i = itemCount - 1; i >= 0; i--) {
					if (items[i].getChecked()) {
						int index = resultTable.indexOf(items[i]);

						if (index < itemCount - 1) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3) };
							items[i].dispose();
							TableItem item = new TableItem(resultTable,
									SWT.NONE, index + 1);
							item.setChecked(true);
							item.setText(values);
							items = resultTable.getItems();

						}
					}
				}
			}
		});
		GridData downButton = new GridData(SWT.RIGHT, SWT.CENTER, true, false,
				1, 1);
		downButton.heightHint = 25;
		downButton.widthHint = 26;
		upbutton.setLayoutData(downButton);
		upbutton.setImage(SWTResourceManager.getImage("images\\down.jpg"));

		Button btnNewButton_1 = new Button(grpKursWybranychWalut, SWT.FLAT
				| SWT.CENTER);
		btnNewButton_1.setSelection(true);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = resultTable.getItems();
				for (TableItem it : items) {
					if (it.getChecked()) {
						int index = resultTable.indexOf(it);
						if (index > 0) {
							String[] values = { it.getText(0), it.getText(1),
									it.getText(2), it.getText(3) };
							it.dispose();
							TableItem item = new TableItem(resultTable,
									SWT.NONE, index - 1);
							item.setChecked(true);
							item.setText(values);
							items = resultTable.getItems();

						}
					}
				}
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_1.heightHint = 26;
		gd_btnNewButton_1.widthHint = 28;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setImage(SWTResourceManager.getImage("images\\up.jpg"));

		Button btnUsuWybrane = new Button(grpKursWybranychWalut, SWT.FLAT);
		btnUsuWybrane.setSelection(true);
		GridData gd_btnUsuWybrane = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnUsuWybrane.heightHint = 26;
		gd_btnUsuWybrane.widthHint = 27;
		btnUsuWybrane.setLayoutData(gd_btnUsuWybrane);
		btnUsuWybrane.setImage(SWTResourceManager
				.getImage("images\\remove.jpg"));
		btnUsuWybrane.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = resultTable.getItems();
				for (TableItem it : items) {
					if (it.getChecked()) {
						ExchangeEntity exchangeEntity = (ExchangeEntity) it
								.getData();
						SelectionsManager.inst().remove(exchangeEntity);
						int index = resultTable.indexOf(it);
						resultTable.remove(index);
					}
				}
			}
		});

		Label label = new Label(shlCurrenct, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label.heightHint = 383;
		gd_label.widthHint = 11;
		label.setLayoutData(gd_label);
	}

	public void setInput(ExchangeTable table) {
		this.tableModel = table;
		initTable(null);
	}

	private void initTable(String filter) {
		if (tableModel != null) {
			List<ExchangeEntity> entites = tableModel.getEntites();
			Collections.sort(entites);
			for (ExchangeEntity e : entites) {
				if (checkFilter(filter, e)) {
					TableItem item = new TableItem(inputTable, SWT.NONE);
					item.setText(e.getFixedCurrencyName());
					item.setData(e);
				}
			}
		}
	}

	private boolean checkFilter(String filter, ExchangeEntity e) {
		if (filter == null || (filter.equals(""))) {
			return true;
		}
		filter = filter.trim().toLowerCase();
		if (e.getFixedCurrencyName().startsWith(filter)) {
			return true;
		}
		return false;
	}

	private void clearGUI() {
		inputTable.removeAll();
		initTable("");
		resultTable.removeAll();
		filterText.setText("");
	}
}
