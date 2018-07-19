package volume02.chapter09.hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * test digest, see the SHA-256 fingerprint of a file
 * @author mhts
 * @date 2018年7月19日
 */
public class Digest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		String path = System.getProperty("user.dir") + "\\resources\\题都城南庄.txt";
		String algName = "SHA-256";
		MessageDigest alg = MessageDigest.getInstance(algName);
		byte[] input = Files.readAllBytes(Paths.get(path));
		byte[] hash = alg.digest(input);
		String d = "";
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			if (v < 16) {
				d += "0";
			}
			d += Integer.toString(v, 16).toUpperCase() + " ";
		}
		System.out.println(d);
	}

}
