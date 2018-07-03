package volume02.chapter02.simple_file_visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * test simple file visitor
 * @author mhts
 * @date 2018Äê7ÔÂ2ÈÕ
 */
public class SimpleFileVisitorTest {

	public static void main(String[] args) throws IOException {
		Path filePath = Paths.get(System.getProperty("user.dir") + "\\resources\\test");
		listDirectories(filePath);
		deletePath(filePath);
	}

	/**
	 * list the directories under given path
	 * @param filePath
	 * @throws IOException
	 */
	private static void listDirectories(Path filePath) throws IOException {
		Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
			
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
				System.out.println(path);
				return FileVisitResult.CONTINUE;
			}
			
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				System.out.println(dir);
				return FileVisitResult.CONTINUE;
			}
			
			public FileVisitResult postVisitDirectory(Path dir, IOException e) {
				return FileVisitResult.CONTINUE;
			}
			
			public FileVisitResult visitFileFailed(Path path, IOException e) {
				return FileVisitResult.SKIP_SUBTREE;
			}
		});
	}

	/**
	 * delete given path and all subdirectories
	 * @param filePath
	 * @throws IOException
	 */
	private static void deletePath(Path filePath) throws IOException {
		Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}
			
			public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				if (e != null) {
					throw e;
				}
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

}
