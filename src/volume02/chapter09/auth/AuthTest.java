package volume02.chapter09.auth;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import volume02.chapter09.util.SysPropAction;

/**
 * authenticate a user via a custom loign and executes the SysPropAction with the user's privilege
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class AuthTest {

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {
			LoginContext context = new LoginContext("Login1");
			context.login();
			System.out.println("Authentication successfully");
			Subject subject = context.getSubject();
			System.out.println("subject = " + subject);
			PrivilegedAction<String> action = new SysPropAction("user.home");
			String result = Subject.doAsPrivileged(subject, action, null);
			System.out.println(result);
			context.logout();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

}
