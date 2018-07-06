package volume02.chapter04.url_connection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * test URL connection
 * @author mhts
 * @date 2018Äê7ÔÂ6ÈÕ
 */
public class URLConnectionTest {

	public static void main(String[] args) {
		try {
			String urlName = "http://horstmann.com";
			
			URL url = new URL(urlName);
			URLConnection connection = url.openConnection();

//			String username = "test";
//			String password = "test";
//			String userCredential = username + ":" + password;
//			Base64.Encoder encoder = Base64.getEncoder();
//			userCredential = encoder.encodeToString(userCredential.getBytes(StandardCharsets.UTF_8));
//			connection.setRequestProperty("Autorization", "Basic " + userCredential);
			
			connection.connect();
			
			Map<String, List<String>> headers = connection.getHeaderFields();
			headers.forEach((k, v) -> System.out.println(k + ": " + v));
			
			System.out.println("-----------------");
			System.out.println("getContentType: " + connection.getContentType());
			System.out.println("getContentLength: " + connection.getContentLength());
			System.out.println("getContentEncoding: " + connection.getContentEncoding());
			System.out.println("getDate: " + new Date(connection.getDate()));
			System.out.println("getExpiration: " + connection.getExpiration());
			System.out.println("getLastModified: " + new Date(connection.getLastModified()));
			System.out.println("-----------------");
			
			String encoding = connection.getContentEncoding();
			if (encoding == null) {
				encoding = "UTF-8";
			}
			try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
				for (int n = 1; in.hasNextLine() && n <= 10; n++) {
					System.out.println(in.nextLine());
				}
				if (in.hasNextLine()) {
					System.out.println("...");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
