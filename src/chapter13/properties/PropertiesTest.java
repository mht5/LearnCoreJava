package chapter13.properties;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * test properties
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class PropertiesTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new PropertiesFrame();
			frame.setVisible(true);
		});
	}

}

class PropertiesFrame extends JFrame {
	private static final long serialVersionUID = 4342983968776206317L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private File propertiesFile;
	private Properties settings;
	
	public PropertiesFrame() {
		String userDir = System.getProperty("user.dir");
		File propertiesDir = new File(userDir, "resources");
		if (!propertiesDir.exists()) {
			propertiesDir.mkdir();
		}
		propertiesFile = new File(propertiesDir, "settings.properties");
		
		Properties defaultSettings = new Properties();
		defaultSettings.setProperty("left", "0");
		defaultSettings.setProperty("top", "0");
		defaultSettings.setProperty("width", DEFAULT_WIDTH + "");
		defaultSettings.setProperty("height", DEFAULT_HEIGHT + "");
		defaultSettings.setProperty("title", "");
		
		settings = new Properties(defaultSettings);
		
		if (propertiesFile.exists()) {
			try (InputStream in = new FileInputStream(propertiesFile)) {
				settings.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int left = Integer.parseInt(settings.getProperty("left"));
			int top = Integer.parseInt(settings.getProperty("top"));
			int width = Integer.parseInt(settings.getProperty("width"));
			int height = Integer.parseInt(settings.getProperty("height"));
			setBounds(left, top, width, height);
			
			String title = settings.getProperty("title");
			if (title.equals("")) {
				title = JOptionPane.showInputDialog("Please supply a frame title: ");
			}
			if (title == null) {
				title = "";
			}
			setTitle(title);
			
			String content = settings.getProperty("content", "not a word");
			JTextArea text = new JTextArea();
			text.append(content);;
			add(text);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					settings.setProperty("left", getX() + "");
					settings.setProperty("top", getY() + "");
					settings.setProperty("width", getWidth() + "");
					settings.setProperty("height", getHeight() + "");
					settings.setProperty("title", getTitle());
					
					try (OutputStream out = new FileOutputStream(propertiesFile)) {
						settings.store(out, "update based closed state");
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			});
		}
	}
}
