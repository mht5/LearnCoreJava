package volume02.chapter01.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * test Collection.stream()
 * @author mhts
 * @date 2018Äê6ÔÂ25ÈÕ
 */
public class CountLongWords {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(System.getProperty("user.dir") + "\\resources\\The Old Man And the Sea.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\PL+"));
		
		long count = 0;
		for (String w : words) {
			if (w.length() > 12) {
				count++;
			}
		}
		System.out.println(count);
		
		count = words.stream().filter(w -> w.length() > 12).count();
		System.out.println(count);
		
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		System.out.println(count);
	}

}
