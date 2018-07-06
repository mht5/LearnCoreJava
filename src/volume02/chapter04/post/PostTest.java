package volume02.chapter04.post;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * test POST
 * @author mhts
 * @date 2018��7��6��
 */
public class PostTest {
	public static void main(String[] args) throws IOException {
		String propsFilename = System.getProperty("user.dir") + "\\resources\\post.properties";
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get(propsFilename))) {
			props.load(in);
		}
		String urlString = props.remove("url").toString();
		Object userAgent = props.remove("User-Agent");
		Object redirects = props.remove("redirects");
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		String result = doPost(new URL(urlString), props,
				userAgent == null ? null : userAgent.toString(),
				redirects == null ? -1 : Integer.parseInt(redirects.toString()));
		System.out.println(result);
	}
	
	public static String doPost(URL url, Map<Object, Object> nameValuePairs,
			String userAgent, int redirects) throws IOException {
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (userAgent != null) {
			connection.setRequestProperty("User-Agent", userAgent);
		}
		if (redirects >= 0) {
			connection.setInstanceFollowRedirects(false);
		}
		
		connection.setDoOutput(true);
		
		/**
		 * ʹ��OutputStreamWriter��������
		 */
		try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
			boolean first = true;
			for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
				if (first) {
					first = false;
				} else {
					out.print('&');
				}
				String name = pair.getKey().toString();
				String value = pair.getValue().toString();
				out.print(name + '=' + URLEncoder.encode(value, "UTF-8"));
			}
		}
		
		String encoding = connection.getContentEncoding();
		if (encoding == null) {
			encoding = "UTF-8";
		}
		
		if (redirects > 0) {
			int responseCode = connection.getResponseCode();
			/**
			 * �����Ҫ�ض���
			 */
			if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
					|| responseCode == HttpURLConnection.HTTP_MOVED_TEMP
					|| responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				
				String location = connection.getHeaderField("Location");
				if (location != null) {
					URL base = connection.getURL();
					connection.disconnect();
					return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
				}
			}
		} else if (redirects == 0) {
			throw new IOException("Too manay redirects");
		}
		
		/**
		 * ���շ���ֵ
		 */
		StringBuilder response = new StringBuilder();
		try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
			while (in.hasNextLine()) {
				response.append(in.nextLine() + "\n");
			}
		} catch (IOException e) {
			InputStream err = connection.getErrorStream();
			if (err == null) {
				throw e;
			}
			try (Scanner in = new Scanner(err)) {
				response.append(in.nextLine() + "\n");
			}
		}
		return response.toString();
	}
}
