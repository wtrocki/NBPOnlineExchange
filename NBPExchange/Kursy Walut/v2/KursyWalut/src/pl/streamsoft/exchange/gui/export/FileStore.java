package pl.streamsoft.exchange.gui.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class FileStore {

	private final Table listOfElements;
	private final File file;

	public FileStore(Table listOfElements, File file) {
		this.listOfElements = listOfElements;
		this.file = file;
	}

	public void export() throws IOException {
		FileWriter outFile = new FileWriter(file);
		PrintWriter out = new PrintWriter(outFile);
		out.println("#Kalkulator walut. Eksport wyników " + new Date());
		out.println();
		for (int i = 0; i < listOfElements.getItemCount(); i++) {
			TableItem item = listOfElements.getItem(i);
			for (int j = 0; j < listOfElements.getColumnCount(); j++) {
				out.print(item.getText(j) + "  ");
				// System.out.print(item.getText(j) + "  ");
			}
			out.println();
		}
		out.close();
	}
}
