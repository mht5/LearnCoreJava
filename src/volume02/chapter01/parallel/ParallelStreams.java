package volume02.chapter01.parallel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * test parallel streams
 * @author mhts
 * @date 2018Äê6ÔÂ30ÈÕ
 */
public class ParallelStreams {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(System.getProperty("user.dir") + "\\resources\\The Old Man And the Sea.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("\\PL+"));
		
//		not recommended
		int[] shortWords = new int[10];
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) {
				shortWords[s.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWords));
		
//		the result may be different if you try again, both results would very likely be wrong
		Arrays.fill(shortWords, 0);
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) {
				shortWords[s.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWords));
		
//		try this one
		Map<Integer, Long> shortWordCounts = wordList.parallelStream()
				.filter(s -> s.length() < 10)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(shortWordCounts);
		
//		downstream order not deterministic
		Map<Integer, List<String>> result = wordList.parallelStream()
				.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println(result.get(15));
		Map<Integer, Long> wordCounts = wordList.parallelStream()
				.collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
		System.out.println(wordCounts);
	}

}
