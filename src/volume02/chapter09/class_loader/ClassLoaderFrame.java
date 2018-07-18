package volume02.chapter09.class_loader;

import java.awt.GridBagLayout;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import volume02.chapter07.util.GBC;

/**
 * class loader frame
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class ClassLoaderFrame extends JFrame {
	private static final long serialVersionUID = 7644209932061231517L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JTextField keyField = new JTextField("3", 4);
	private JTextField nameField = new JTextField("Calculator", 30);
	
	public ClassLoaderFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new GridBagLayout());
		add(new JLabel("Class"), new GBC(0, 0).setAnchor(GBC.EAST));
		add(nameField, new GBC(1, 0).setWeight(100,  0).setAnchor(GBC.WEST));
		add(new JLabel("Key"), new GBC(0, 1).setAnchor(GBC.EAST));
		add(keyField, new GBC(1, 1).setWeight(100, 0).setAnchor(GBC.WEST));
		JButton loadButton = new JButton("Load");
		add(loadButton, new GBC(0, 2, 2, 1));
		loadButton.addActionListener(event -> {
			runClass(nameField.getText(), keyField.getText());
		});
		pack();
	}
	
	public void runClass(String name, String key) {
		try {
			ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
			Class<?> c = loader.loadClass(name);
			Method m = c.getMethod("main", String[].class);
			m.invoke(null, (Object) new String[] {});
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(this, t);
		}
	}
}
