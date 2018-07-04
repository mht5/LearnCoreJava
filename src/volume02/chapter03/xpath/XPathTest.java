package volume02.chapter03.xpath;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * test XPath
 * @author mhts
 * @date 2018Äê7ÔÂ4ÈÕ
 */
public class XPathTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new XPathFrame();
			frame.setTitle("XPathTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
