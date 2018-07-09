package volume02.chapter05.view;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

/**
 * test meta data and row set
 * @author mhts
 * @date 2018Äê7ÔÂ9ÈÕ
 */
public class ViewDB {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(() -> {
			JFrame frame = new ViewDBFrame();
			frame.setTitle("ViewDB");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
