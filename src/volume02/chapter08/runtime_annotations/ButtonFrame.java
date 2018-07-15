package volume02.chapter08.runtime_annotations;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class ButtonFrame extends JFrame {
	private static final long serialVersionUID = 1648940484484299374L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton redButton;
	
	public ButtonFrame() throws IllegalArgumentException, Throwable {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		add(panel);
		
		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		ActionListenerInstaller.processAnnotations(this);
	}
	
	@ActionListenerFor(source="yellowButton")
	public void yellowBackground() {
		panel.setBackground(Color.YELLOW);
	}
	
	@ActionListenerFor(source="blueButton")
	public void blueBackground() {
		panel.setBackground(Color.BLUE);
	}
	
	@ActionListenerFor(source="redButton")
	public void redBackground() {
		panel.setBackground(Color.RED);
	}
}
