package volume02.chapter09.auth;

import java.security.PrivilegedAction;

/**
 * look up system property
 * @author mhts
 * @date 2018��7��18��
 */
public class SysPropAction implements PrivilegedAction<String> {
	
	private String propertyName;
	
	public SysPropAction(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Override
	public String run() {
		return System.getProperty(propertyName);
	}

}
