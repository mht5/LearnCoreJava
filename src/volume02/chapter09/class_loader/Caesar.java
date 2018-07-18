package volume02.chapter09.class_loader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * encrypt a file with Caesar
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class Caesar {

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Usage: java.classLoader.Caesar in out key");
			return;
		}
		try (FileInputStream in = new FileInputStream(args[0]);
				FileOutputStream out = new FileOutputStream(args[1])) {
			int key= Integer.parseInt(args[2]);
			int ch;
			while ((ch = in.read()) != -1) {
				byte c = (byte) (ch + key);
				out.write(c);
			}
		}
	}

}
