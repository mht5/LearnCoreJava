package chapter03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * test File I/O
 * @author mhts
 * @date 2018Äê6ÔÂ15ÈÕ
 */
public class FileIOTest {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner in = new Scanner(Paths.get("README.md"), "UTF-8");
			String file = System.getProperty("user.dir") + "\\resources\\output.txt";
			PrintWriter out = new PrintWriter(file);
			String line;
			while (in.hasNext()) {
				line = in.nextLine();
				System.out.println(line);
				out.println(line);
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
