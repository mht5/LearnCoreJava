package volume02.chapter07.date_time_format;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test date time formatter with locale
 * @author mhts
 * @date 2018Äê7ÔÂ10ÈÕ
 */
public class DateTimeFormatterTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new DateTimeFormatterFrame();
			frame.setTitle("DateTimeFormatTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
