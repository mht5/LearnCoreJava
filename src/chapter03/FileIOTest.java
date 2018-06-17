package chapter03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * test File I/O
 * @author mhts
 * @date 2018��6��15��
 */
public class FileIOTest {
	public static void main(String[] args) throws FileNotFoundException {
		try {
//			ʹ��Scanner�����Ļ�������
//			Scanner in = new Scanner(Paths.get("README.md"), "UTF-8");
			String resourcePath = System.getProperty("user.dir") + "\\resources";
			BufferedReader bf = new BufferedReader(new FileReader(resourcePath + "\\�ⶼ����ׯ.txt"));
			PrintWriter out = new PrintWriter(resourcePath + "\\output.txt");
			String line;
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
				out.println(line);
			}
//			while (in.hasNext()) {
//				line = in.nextLine();
//				System.out.println(line);
//				out.println(line);
//			}
			out.close();
			bf.close();
//			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
