package Music_interface;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

class FileChooser {
	static String file;
	public static String openFile() {
		System.out.println("Getting file");
	JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
	int r = j.showOpenDialog(null);
	if (r == JFileChooser.APPROVE_OPTION) {
			file = j.getSelectedFile().getAbsolutePath();
			System.out.println(file);
			return file;
		    }
		    // if the user cancelled the operation
		    else {
			return "";
		}
	}
}
