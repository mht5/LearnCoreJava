package volume02.chapter01.optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * test optional
 * @author mhts
 * @date 2018年6月26日
 */
public class OptionalTest {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(System.getProperty("user.dir") + "\\resources\\The Old Man And the Sea.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\PL+"));
		
		Optional<String> optionalValue = words.stream()
				.filter(w -> w.contains("for"))
				.findFirst();
		System.out.println(optionalValue.orElse("No word") + " contains \"for\"");
		
		Optional<String> optionalString = Optional.empty();
		String result = optionalString.orElse("N/A");
		System.out.println("result: " + result);
		result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
		System.out.println("result: " + result);
		try {
			result = optionalString.orElseThrow(IllegalStateException::new);
			System.out.println("result: " + result);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		optionalValue = words.stream()
				.filter(w -> w.contains("at"))
				.findFirst();
		System.out.println(optionalValue.orElse("No word") + " contains \"at\"");
		
		Set<String> results = new HashSet<>();
		optionalValue.ifPresent(results::add);
//		如果optionalValue有值，会返回true || false，如果没有值，会返回空的Optional
		Optional<Boolean> added = optionalValue.map(results::add);
		System.out.println(added);
		
		System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
		System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
		System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));
		Optional<Double> result2 = Optional.of(-4.0)
				.flatMap(OptionalTest::inverse)
				.flatMap(OptionalTest::squareRoot);
		System.out.println(result2);
	}
	
	public static Optional<Double> inverse(Double x) {
		return x == 0 ? Optional.empty() : Optional.of(1 / x);
	}
	
	public static Optional<Double> squareRoot(Double x) {
		return x > 0 ? Optional.empty() : Optional.ofNullable(Math.sqrt(x));
	}

}
