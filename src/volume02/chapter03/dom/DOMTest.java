package volume02.chapter03.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * test DOM
 * @author mhts
 * @date 2018Äê7ÔÂ4ÈÕ
 */
public class DOMTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		String path = System.getProperty("user.dir") + "\\resources\\test.xml";
		Document doc = builder.parse(new File(path));
		Element element = doc.getDocumentElement();
		
		System.out.println(element.getTagName());
		
		NodeList list = element.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node instanceof CharacterData) {
				System.out.print(characterString((CharacterData) node));
				System.out.println();
				if (node.hasChildNodes()) {
					displayChildNodes(node);
				}
			} else {
				System.out.print(node.getNodeName() + "=");
				if (node.hasChildNodes()) {
					displayChildNodes(node);
				}
			}
		}
	}
	
	private static void displayChildNodes(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node node1 = list.item(i);
			if (node1 instanceof CharacterData) {
				System.out.print(((CharacterData) node1).getData());
//				System.out.print(characterString((CharacterData) node1));
				System.out.println();
				if (node1.hasChildNodes()) {
					displayChildNodes(node1);
				}
			} else {
				System.out.print(node1.getNodeName() + "=");
				if (node1.hasChildNodes()) {
					displayChildNodes(node1);
				}
			}
		}
	}

	private static String characterString(CharacterData node) {
		StringBuilder builder = new StringBuilder(node.getData());
		for (int i = 0; i < builder.length(); i++) {
//			char c = builder.charAt(i);
//			if (c == '\r' || c == '\n' || c == '\t') {
//				builder.replace(i, i + 1, "");
//				i++;
//			}
			if (builder.charAt(i) == '\r') {
				builder.replace(i, i + 1, "\\r");
				i++;
			} else if (builder.charAt(i) == '\n') {
				builder.replace(i, i + 1, "\\n");
				i++;
			} else if (builder.charAt(i) == '\t') {
				builder.replace(i, i + 1, "\\t");
				i++;
			}
		}
		
//		if (node instanceof CDATASection) {
//			builder.insert(0, "CDATASection: ");
//		} else if (node instanceof Text) {
//			builder.insert(0, "Text: ");
//		} else if (node instanceof Comment) {
//			builder.insert(0, "Comment: ");
//		}
		
		return builder.toString();
	}

}
