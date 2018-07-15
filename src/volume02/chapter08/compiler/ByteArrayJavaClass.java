package volume02.chapter08.compiler;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * a java source which holds the byte codes in a byte array
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject {
	private ByteArrayOutputStream stream;
	
	protected ByteArrayJavaClass(String name) {
		super(URI.create("bytes:///" + name), Kind.CLASS);
		stream = new ByteArrayOutputStream();
	}
	
	public OutputStream openOutputStream() {
		return stream;
	}
	
	public byte[] getBytes() {
		return stream.toByteArray();
	}

}
