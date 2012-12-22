package pl.streamsoft.exchange.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AboutDialog extends Dialog {

	protected Object result;
	protected Shell shlOProgramie;
	private Button btnZamknij;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public AboutDialog(Shell parent, int style) {
		super(parent, style);

	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlOProgramie.open();
		shlOProgramie.layout();
		Display display = getParent().getDisplay();
		btnZamknij.setSelection(true);
		while (!shlOProgramie.isDisposed()) {
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
		shlOProgramie = new Shell(getParent().getDisplay(), SWT.DIALOG_TRIM
				| SWT.RESIZE);
		shlOProgramie.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shlOProgramie.setText("O programie");
		shlOProgramie.setSize(386, 221);
		int x = 386;
		int y = 221;
		// Get the resolution
		Rectangle pDisplayBounds = shlOProgramie.getDisplay().getBounds();

		// This formulae calculate the shell's Left ant Top
		int nLeft = (pDisplayBounds.width - x) / 2;
		int nTop = (pDisplayBounds.height - y) / 2;

		// Set shell bounds,
		shlOProgramie.setBounds(nLeft, nTop, x, y);
		setText("O programie");

		btnZamknij = new Button(shlOProgramie, SWT.PUSH | SWT.BORDER_SOLID);
		btnZamknij.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shlOProgramie.dispose();
			}
		});
		btnZamknij.setBounds(298, 164, 68, 23);
		btnZamknij.setText("Zamknij");

		Text link = new Text(shlOProgramie, SWT.READ_ONLY);
		link.setBackground(SWTResourceManager.getColor(230, 230, 250));
		link.setBounds(121, 127, 178, 13);
		link.setText("Kontakt: wtrocki@gmail.com");

		CLabel lblNewLabel = new CLabel(shlOProgramie, SWT.BORDER
				| SWT.SHADOW_IN | SWT.SHADOW_OUT | SWT.SHADOW_NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(230, 230, 250));
		lblNewLabel.setBounds(118, 20, 248, 138);
		lblNewLabel
				.setText(" Kalkulator walut ver 0.0.2 \r\n -------------------------------\r\n Program umo\u017Cliwiaj\u0105cy pobieranie\r\n aktualnych kurs\u00F3w walut ze strony nbp.pl\r\n\r\n Copyright by Wojciech Trocki.\r\n");

		Label lblNewLabel_1 = new Label(shlOProgramie, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_1.setImage(SWTResourceManager.getImage("images/about.gif"));
		lblNewLabel_1.setBounds(10, 20, 100, 138);
	}
}
