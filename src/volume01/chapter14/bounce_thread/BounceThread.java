package volume01.chapter14.bounce_thread;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import volume01.chapter14.bounce.Ball;
import volume01.chapter14.bounce.BallComponent;

/**
 * show multiple animated bouncing balls
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class BounceThread {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}

class BounceFrame extends JFrame {
	private static final long serialVersionUID = -9011043054280727915L;
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BounceFrame() {
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "Close", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	public void addBall() {
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = () -> {
			try {
				for (int i = 1; i <= STEPS; i++) {
					ball.move(comp.getBounds());
					comp.paintComponent(comp.getGraphics());
					Thread.sleep(DELAY);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
