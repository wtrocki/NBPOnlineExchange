package pl.streamsoft.exchange.gui.export;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class FileDialogUtil {

	public static void openTxtFileDialog(Shell parent, Table resultTable) {
		FileDialog dialog = new FileDialog(parent, SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.txt" });
		dialog.setText("Zapisz wybrane kursy walut");
		dialog.setFilterPath("C:/");

		String path = dialog.open();
		if (path != null) {
			File file = new File(path);
			FileStore fs = new FileStore(resultTable, file);
			try {
				fs.export();
			} catch (IOException e1) {
				MessageBox mb = new MessageBox(parent, SWT.ERROR);
				mb.setMessage("B³¹d przy zapisie pliku.");
				mb.setText("Nie uda³o siê zapisaæ danych");
				mb.open();
			}
		}
	}

}
