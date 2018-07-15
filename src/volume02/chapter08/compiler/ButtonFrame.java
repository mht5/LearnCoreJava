package volume02.chapter08.compiler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * a frame with a button panel
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public abstract class ButtonFrame extends JFrame {
	private static final long serialVersionUID = -3200002725884993747L;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	protected JPanel panel;
	protected JButton yellowButton;
	protected JButton blueButton;
	protected JButton redButton;
	
	protected abstract void addEventHandler();
	
	public ButtonFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		add(panel);
		
		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		addEventHandler();
	}
}
