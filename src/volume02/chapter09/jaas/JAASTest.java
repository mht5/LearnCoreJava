package volume02.chapter09.jaas;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test JAAS
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class JAASTest {
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		EventQueue.invokeLater(() -> {
			JFrame frame = new JAASFrame();
			frame.setTitle("JAASTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
