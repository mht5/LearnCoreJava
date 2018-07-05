package volume02.chapter04.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * test socket
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class SocketTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket s = new Socket("time-a.nist.gov", 13);
				Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		}
	}

}
