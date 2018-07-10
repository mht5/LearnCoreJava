package volume02.chapter07.number_format;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test number format
 * @author mhts
 * @date 2018Äê7ÔÂ10ÈÕ
 */
public class NumberFormatTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new NumberFormatFrame();
			frame.setTitle("NumberFormatTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
