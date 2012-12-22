package pl.streamsoft.exchange.gui.init;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class SplashComposite extends Dialog {

	protected Shell shell;
	private ProgressBar progressBar;
	private final int width = 461;
	private final int height = 300;

	private final int PROGRESS_BAR_STEPS = 4;
	private final Color white = SWTResourceManager.getColor(SWT.COLOR_WHITE);
	private final Color blue = SWTResourceManager.getColor(65, 105, 225);
	private Image image;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public SplashComposite(Shell parent, int style) {
		super(parent, style);
		setText("");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return null
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		return null;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.NO_TRIM);
		shell.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shell.setSize(width, height);
		shell.setText(getText());
		Label lblNewLabel = new Label(shell, SWT.NONE);
		image = SWTResourceManager.getImage("images\\splash.jpg");
		lblNewLabel.setImage(image);
		lblNewLabel.setBounds(4, 0, 455, 180);

		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBackground(blue);
		progressBar.setForeground(white);
		progressBar.setMaximum(PROGRESS_BAR_STEPS);
		progressBar.setBounds(PROGRESS_BAR_STEPS, 266, 435, 18);

		Label lblInicjalizacja = new Label(shell, SWT.NONE);
		lblInicjalizacja.setBackground(SWTResourceManager.getColor(255, 255,
				255));
		lblInicjalizacja.setBounds(PROGRESS_BAR_STEPS, 244, 115, 13);
		lblInicjalizacja.setText(" Inicjalizacja...");

		// Get the resolution
		Rectangle pDisplayBounds = shell.getDisplay().getBounds();

		// This formulae calculate the shell's Left ant Top
		int nLeft = (pDisplayBounds.width - width) / 2;
		int nTop = (pDisplayBounds.height - height) / 2;

		// Set shell bounds,
		shell.setBounds(nLeft, nTop, width, height);
	}

	/**
	 * Turns progress bar to the next step.
	 */
	public void stepProgres() {
		progressBar.setSelection(progressBar.getSelection() + 1);
	}

	public void dispose() {
		getParent().dispose();
		blue.dispose();
		white.dispose();
		image.dispose();
	}
}
