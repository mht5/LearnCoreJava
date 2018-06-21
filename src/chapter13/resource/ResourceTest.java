package chapter13.resource;

import java.awt.EventQueue;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * test resource
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class ResourceTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ResourceTestFrame();
			frame.setTitle("ResourceTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class ResourceTestFrame extends JFrame {
	private static final long serialVersionUID = 3702310480765342018L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	public ResourceTestFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JTextArea textArea = new JTextArea();

		InputStream stream = getClass().getResourceAsStream("The Old Man And the Sea.txt");
		if (stream == null) {
			System.out.println("null");
		}
		try (Scanner in = new Scanner(stream, "UTF-8")) {
			while (in.hasNext()) {
				textArea.append(in.nextLine() + "\n");
			}
		}
		add(textArea);
	}
}
