package volume01.chapter06.timer;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * test callack
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class TimerTest {
	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		Timer timer = new Timer(1000, listener);
		timer.start();
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
	}
}
