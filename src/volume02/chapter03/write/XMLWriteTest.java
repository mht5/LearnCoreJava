package volume02.chapter03.write;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test write XML
 * @author mhts
 * @date 2018��7��5��
 */
public class XMLWriteTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new XMLWriteFrame();
			frame.setTitle("XMLWriteTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
