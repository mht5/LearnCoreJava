package volume02.chapter04.inet_address;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * test InetAddress
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
		for (InetAddress a : addresses) {
			System.out.println(a);
		}
		
		InetAddress localAddress = InetAddress.getLocalHost();
		System.out.println("Local host: " + localAddress);
	}

}
