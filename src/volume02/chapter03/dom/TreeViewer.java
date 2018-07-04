package volume02.chapter03.dom;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * display an XML document as a tree
 * @author mhts
 * @date 2018��7��3��
 */
public class TreeViewer {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new DOMTreeFrame();
			frame.setTitle("TreeViewer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
