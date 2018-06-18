package chapter06.inner_class;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * test inner class
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class InnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		JOptionPane.showMessageDialog(null, "Quie Program?");
		System.exit(0);
	}
}

class TalkingClock {
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep) {
		super();
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start() {
		ActionListener listener = new TimePrinter();
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
	
	public class TimePrinter implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("At this tone, the time is " + new Date());
			if (beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}
