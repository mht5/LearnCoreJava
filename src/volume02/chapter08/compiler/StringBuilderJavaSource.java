package volume02.chapter08.compiler;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * a java source which holds the code in a string builder
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class StringBuilderJavaSource extends SimpleJavaFileObject {
	private StringBuilder code;
	
	public StringBuilderJavaSource(String name) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		code = new StringBuilder();
	}
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	
	public void append(String str) {
		code.append(str + "\n");
	}
	
}
