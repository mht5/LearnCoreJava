package volume01.chapter13.properties;

import java.util.Properties;

/**
 * view system properties
 * @author mhts
 * @date 2018��6��21��
 */
public class SystemProperties {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println(key + "=" + properties.getProperty(key.toString()));
		}
	}

}
