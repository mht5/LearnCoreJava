package volume02.chapter08.compiler;

import java.util.Map;

/**
 *  a class loader which loads classes from a map
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class MapClassLoader extends ClassLoader {
	private Map<String, byte[]> classes;
	
	public MapClassLoader(Map<String, byte[]> classes) {
		this.classes = classes;
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = classes.get(name);
		if (classBytes == null) {
			throw new ClassNotFoundException(name);
		}
		Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
		if (cl == null) {
			throw new ClassNotFoundException(name);
		}
		return cl;
	}
}
