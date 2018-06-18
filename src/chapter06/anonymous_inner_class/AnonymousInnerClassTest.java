package chapter06.anonymous_inner_class;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * test anonymous inner class
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class AnonymousInnerClassTest {

	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock();
		clock.start(1000, true);
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
	}

}

class TalkingClock {
	public void start(int interval, boolean beep) {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("At this tone, the time is " + new Date());
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
}
