package volume02.chapter02.random_access_file;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * a tool to write and read fixed length string
 * @author mhts
 * @date 2018��7��1��
 */
public class DataIO {
	
	public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
		for (int i = 0; i < size; i++) {
			char ch = 0;
			if (i < s.length()) {
				ch = s.charAt(i);
			}
			out.writeChar(ch);
		}
	}
	
	public static String readFixedString(int size, DataInput in) throws IOException {
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while (more && i < size) {
			char ch = in.readChar();
			i++;
			if (ch == 0) {
				more = false;
			} else {
				b.append(ch);
			}
		}
		in.skipBytes(2 * (size - i));
		return b.toString();
	}
}
