package volume01.chapter09.set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * test hash set
 * @author mhts
 * @date 2018Äê6ÔÂ20ÈÕ
 */
public class SetTest {

	public static void main(String[] args) {
		Set<String> words = new HashSet<>();
		
		try {
			String resourcePath = System.getProperty("user.dir") + "\\resources";
			BufferedReader bf = new BufferedReader(new FileReader(resourcePath + "\\The Old Man And the Sea.txt"));
			String line;
			while ((line = bf.readLine()) != null) {
				String[] w = line.split(" ");
				for (String s : w) {
					words.add(s);
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<String> iter = words.iterator();
		for (int i = 0; i < 20 && iter.hasNext(); i++) {
			System.out.println(iter.next());
		}
		
		System.out.println("-------------");
		System.out.println(words.size() + " distinct words found in this file.");
	}

}
