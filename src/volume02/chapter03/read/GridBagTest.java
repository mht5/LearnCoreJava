package volume02.chapter03.read;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * test to show how to use an XML file to describe a grid bag layout
 * @author mhts
 * @date 2018Äê7ÔÂ4ÈÕ
 */
public class GridBagTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFileChooser chooser = new JFileChooser(".");
			chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			JFrame frame = new FontFrame(file);
			frame.setTitle("GridBagTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
