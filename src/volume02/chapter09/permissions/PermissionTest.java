package volume02.chapter09.permissions;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * load the policy file and set security manager
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class PermissionTest {

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "\\resources\\PermissionTest.policy";
		System.setProperty("java.security.policy", path);
		System.setSecurityManager(new SecurityManager());
		EventQueue.invokeLater(() -> {
			JFrame frame = new PermissionTestFrame();
			frame.setTitle("PermissionTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
