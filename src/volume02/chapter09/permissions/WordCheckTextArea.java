package volume02.chapter09.permissions;

import javax.swing.JTextArea;

/**
 * a self defined text area which checks for permission every time you add something
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class WordCheckTextArea extends JTextArea {
	private static final long serialVersionUID = -1494250254487381861L;

	public void append(String text) {
		WordCheckPermission p = new WordCheckPermission(text, "insert");
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) {
			manager.checkPermission(p);
		}
		super.append(text);
	}
}
