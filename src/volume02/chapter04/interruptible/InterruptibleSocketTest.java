package volume02.chapter04.interruptible;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test interruptible socket channel
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class InterruptibleSocketTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new InterruptibleSocketFrame();
			frame.setTitle("InterruptibleSocketTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
