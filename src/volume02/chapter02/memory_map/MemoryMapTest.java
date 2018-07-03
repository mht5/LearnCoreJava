package volume02.chapter02.memory_map;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * test memory map
 * @author mhts
 * @date 2018Äê7ÔÂ2ÈÕ
 */
public class MemoryMapTest {

	public static void main(String[] args) throws IOException {
		Path filename = Paths.get(System.getProperty("user.dir") + "\\resources\\The Old Man And the Sea.txt");
		
		System.out.print("Input Stream:  ");
		long start = System.currentTimeMillis();
		long crcValue = checksumInputStream(filename);
		long end = System.currentTimeMillis();
		System.out.print(Long.toHexString(crcValue) + "  ");
		System.out.print((end - start) + "milliseconds");
		System.out.println();
		
		System.out.print("Buffered Input Stream:  ");
		start = System.currentTimeMillis();
		crcValue = checksumBufferedInputStream(filename);
		end = System.currentTimeMillis();
		System.out.print(Long.toHexString(crcValue) + "  ");
		System.out.print((end - start) + "milliseconds");
		System.out.println();
		
		System.out.print("Random Access File:  ");
		start = System.currentTimeMillis();
		crcValue = checksumRandomAccessFile(filename);
		end = System.currentTimeMillis();
		System.out.print(Long.toHexString(crcValue) + "  ");
		System.out.print((end - start) + "milliseconds");
		System.out.println();
		
		System.out.print("Mapped File:  ");
		start = System.currentTimeMillis();
		crcValue = checksumMappedFile(filename);
		end = System.currentTimeMillis();
		System.out.print(Long.toHexString(crcValue) + "  ");
		System.out.print((end - start) + "milliseconds");
	}
	
	public static long checksumInputStream(Path filename) throws IOException {
		try (InputStream in = Files.newInputStream(filename)) {
			CRC32 crc = new CRC32();
			int c;
			while ((c = in.read()) != -1) {
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumBufferedInputStream(Path filename) throws IOException {
		try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
			CRC32 crc = new CRC32();
			int c;
			while ((c = in.read()) != -1) {
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumRandomAccessFile(Path filename) throws FileNotFoundException, IOException {
		try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
			long length = file.length();
			CRC32 crc = new CRC32();
			for (long p = 0; p < length; p++) {
				file.seek(p);
				int c = file.readByte();
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumMappedFile(Path filename) throws IOException {
		try (FileChannel channel = FileChannel.open(filename)) {
			CRC32 crc = new CRC32();
			int length = (int) channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
			for (int p = 0; p < length; p++) {
				int c = buffer.get(p);
				crc.update(c);
			}
			return crc.getValue();
		}
	}

}
