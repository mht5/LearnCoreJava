package volume01.chapter14.bounce;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * bounce the ball
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class BallComponent extends JPanel {
	private static final long serialVersionUID = 2806933616676081905L;
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 250;
	
	private List<Ball> balls = new ArrayList<>();
	
	public void add(Ball b) {
		balls.add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
