package volume02.chapter03.sax;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * test SAX parser
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class SAXTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		String url = "http://www.w3c.org";
		
		DefaultHandler handler = new DefaultHandler() {
			public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) {
				if (lname.equals("a") && attrs != null) {
					for (int i = 0; i < attrs.getLength(); i++) {
						String aname = attrs.getLocalName(i);
						if (aname.equals("href")) {
							System.out.println(attrs.getValue(i));
						}
					}
				}
			}
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		SAXParser saxParser = factory.newSAXParser();
		InputStream in = new URL(url).openStream();
		saxParser.parse(in, handler);
	}

}
