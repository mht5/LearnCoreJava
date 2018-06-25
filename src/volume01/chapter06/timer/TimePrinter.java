package volume01.chapter06.timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * a class with callback
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class TimePrinter implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("At this tone, the time is " + new Date());
		Toolkit.getDefaultToolkit().beep();
	}
}
