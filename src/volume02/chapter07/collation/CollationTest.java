package volume02.chapter07.collation;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test collation
 * @author mhts
 * @date 2018Äê7ÔÂ10ÈÕ
 */
public class CollationTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new CollationFrame();
			frame.setTitle("CollationTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
