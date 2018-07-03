package volume02.chapter02.files;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * test Files.walk()
 * @author mhts
 * @date 2018Äê7ÔÂ2ÈÕ
 */
public class FilesTest {

	public static void main(String[] args) throws IOException {
		String sysPath = System.getProperty("user.dir");
		Path source = Paths.get(sysPath + "\\src\\volume02\\chapter02");
		Path target = Paths.get(sysPath + "\\resources\\test");
		Files.walk(source).forEach(p -> {
			try {
				Path q = target.resolve(source.relativize(p));
				if (Files.isDirectory(p)) {
					Files.createDirectory(q);
				} else {
					Files.copy(p, q);
				}
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		});
	}

}
