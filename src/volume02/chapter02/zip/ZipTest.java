package volume02.chapter02.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * test zip input stream and zip file system
 * @author mhts
 * @date 2018Äê7ÔÂ2ÈÕ
 */
public class ZipTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String zipname = System.getProperty("user.dir") + "\\resources\\test.zip";
//		String directory = System.getProperty("user.dir") + "\\resources\\settings.properties";
//		createZip(directory, zipname);
//		System.out.println("------------------");
		showContents(zipname);
		System.out.println("------------------");
		showContents1(zipname);
	}
	
	/**
	 * using zip output stream
	 * @param directory
	 * @param zipname
	 * @throws IOException
	 */
	public static void createZip(String directory, String zipname) throws IOException {
		InputStream in = new FileInputStream(directory);
		FileOutputStream fout = new FileOutputStream(zipname);
		ZipOutputStream zout = new ZipOutputStream(fout);
		ZipEntry entry = new ZipEntry(directory);
		zout.putNextEntry(entry);
		int tmp = 0;
		while ((tmp = in.read()) != -1) {
			zout.write(tmp);
		}
		zout.close();
		in.close();
	}
	
	/**
	 * using zip input stream
	 * @param zipname
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void showContents(String zipname) throws FileNotFoundException, IOException {
		try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname))) {
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				System.out.println(entry.getName());
				@SuppressWarnings("resource")
				Scanner in = new Scanner(zin, "UTF-8");
				while (in.hasNextLine()) {
					System.out.println("    " + in.nextLine());
				}
				zin.closeEntry();
			}
		}
	}
	
	/**
	 * using zip file system
	 * @param zipname
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void showContents1(String zipname) throws FileNotFoundException, IOException {
		FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
		Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println(path);
				for (String line : Files.readAllLines(path, Charset.forName("UTF-8"))) {
					System.out.println("    " + line);
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
