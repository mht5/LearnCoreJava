package volume02.chapter09.class_loader;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test class loader
 * @author mhts
 * @date 2018��7��18��
 */
public class ClassLoaderTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ClassLoaderFrame();
			frame.setTitle("ClassLoaderTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
