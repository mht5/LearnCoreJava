package volume01.chapter07.logging;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

/**
 * test logging
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class LoggingImageViewer {
	private static Logger logger = Logger.getLogger("ImageViewerFrame");

	public static void main(String[] args) {
		
		if (System.getProperty("java.util.logging.config.class") == null
				&& System.getProperty("java.util.logging.config.file") == null) {
			
			try {
				logger.setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%hLoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
				logger.addHandler(handler);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "can't create log file handler", e);
			}
		}
		
		EventQueue.invokeLater(() -> {
			Handler handler = new WindowHandler();
			handler.setLevel(Level.ALL);
			logger.addHandler(handler);
			JFrame frame = new ImageViewerFrame();
			frame.setTitle("LoggingImageViewer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			logger.fine("showing frame");
			frame.setVisible(true);
		});
	}

}
