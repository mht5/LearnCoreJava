package volume02.chapter09.aes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import volume02.chapter09.util.CipherUtil;

/**
 * test AES cipher
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class AESTest {

	public static void main(String[] args) throws IOException, GeneralSecurityException, ClassNotFoundException {
		
		if ("-genkey".equals(args[0])) {
			String path = System.getProperty("user.dir") + "\\resources\\" + args[1];
			System.out.println(path);
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
				out.writeObject(key);
			}
			System.out.println("end.");
		} else {
			String resourcePath = System.getProperty("user.dir") + "\\resources\\";
			String inPath = resourcePath + args[1];
			String outPath = resourcePath + args[2];
			String keyPath = resourcePath + args[3];
			System.out.println(inPath);
			int mode;
			if ("-encrypt".equals(args[0])) {
				mode = Cipher.ENCRYPT_MODE;
			} else {
				mode = Cipher.DECRYPT_MODE;
			}
			try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(keyPath));
					InputStream in = new FileInputStream(inPath);
					OutputStream out = new FileOutputStream(outPath)) {
				
				Key key = (Key) keyIn.readObject();
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(mode, key);
				CipherUtil.crypt(in, out, cipher);
			}
			System.out.println("finished");
		}
	}

}
