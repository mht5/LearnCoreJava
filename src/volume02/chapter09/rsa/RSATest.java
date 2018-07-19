package volume02.chapter09.rsa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import volume02.chapter09.util.CipherUtil;

/**
 * test RSA, wrap a key with a public key and unwrap it with the corresponding private key
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class RSATest {

	private static final int KEY_SIZE = 512;
	
	public static void main(String[] args) throws IOException, GeneralSecurityException, ClassNotFoundException {
		if ("-genkey".equals(args[0])) {
			String publicPath = System.getProperty("user.dir") + "\\resources\\" + args[1];
			String privatePath = System.getProperty("user.dir") + "\\resources\\" + args[2];
			System.out.println(publicPath);
			System.out.println(privatePath);
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			pairgen.initialize(KEY_SIZE, random);
			KeyPair keyPair = pairgen.generateKeyPair();
			
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(publicPath))) {
				out.writeObject(keyPair.getPublic());
			}
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(privatePath))) {
				out.writeObject(keyPair.getPrivate());
			}
			System.out.println("end.");
		} else if ("-encrypt".equals(args[0])) {
			String resourcePath = System.getProperty("user.dir") + "\\resources\\";
			String inPath = resourcePath + args[1];
			String outPath = resourcePath + args[2];
			String keyPath = resourcePath + args[3];
			System.out.println(inPath);
			
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			
			try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(keyPath));
					DataOutputStream out = new DataOutputStream(new FileOutputStream(outPath));
					InputStream in = new FileInputStream(inPath)) {
				
				Key publicKey = (Key) keyIn.readObject();
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.WRAP_MODE, publicKey);
				byte[] wrappedKey = cipher.wrap(key);
				out.writeInt(wrappedKey.length);
				out.write(wrappedKey);
				
				cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				CipherUtil.crypt(in, out, cipher);
			}
			System.out.println("finished");
		} else {
			String resourcePath = System.getProperty("user.dir") + "\\resources\\";
			String inPath = resourcePath + args[1];
			String outPath = resourcePath + args[2];
			String keyPath = resourcePath + args[3];
			System.out.println(inPath);
			
			try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(keyPath));
					OutputStream out = new FileOutputStream(outPath);
					DataInputStream in = new DataInputStream(new FileInputStream(inPath))) {
				
				int length = in.readInt();
				byte[] wrappedKey = new byte[length];
				in.read(wrappedKey, 0, length);
				
				Key privateKey = (Key) keyIn.readObject();
				
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.UNWRAP_MODE, privateKey);
				Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
				
				cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, key);
				
				CipherUtil.crypt(in, out, cipher);
			}
			System.out.println("done.");
		}
	}

}
