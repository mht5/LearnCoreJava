package chapter07.logging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * a JFrame to select and display image
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class ImageViewerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private JLabel label;
	private static Logger logger = Logger.getLogger("ImageViewerFrame");
	
	public ImageViewerFrame() {
		logger.entering("ImageViewerFrame", "<init>");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				logger.fine("Exiting...");
				System.exit(0);
			}
		});
		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");
	}
	
	private class FileOpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
				}
				@Override
				public String getDescription() {
					return "GIF Images";
				}
			});
			int r = chooser.showOpenDialog(ImageViewerFrame.this);
			if (r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file {0}", name);
				label.setIcon(new ImageIcon(name));
			} else {
				logger.fine("File open dialog canceled.");
			}
			logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
		}
	}
}
