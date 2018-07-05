package volume02.chapter03.stax;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * test StAX parser
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class StAXTest {

	public static void main(String[] args) throws IOException, XMLStreamException {
		String urlString = "http://www.w3c.org";
		URL url = new URL(urlString);
		InputStream in = url.openStream();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory.createXMLStreamReader(in);
		long time = System.currentTimeMillis();
		int count = 0;
		while (parser.hasNext()) {
			int event = parser.next();
			if (event == XMLStreamConstants.START_ELEMENT) {
				if (parser.getLocalName().equals("a")) {
					String href = parser.getAttributeValue(null, "href");
					if (href != null) {
						System.out.println(href);
						count++;
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(System.currentTimeMillis() - time);
	}

}
