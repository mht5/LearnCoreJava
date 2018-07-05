package volume02.chapter03.transform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

/**
 * transform an XML file based on a given style
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class TransformTest {

	public static void main(String[] args) throws IOException, TransformerFactoryConfigurationError, TransformerException {
		String homeDir = System.getProperty("user.dir");
		Path path = Paths.get(homeDir + "\\src\\volume02\\chapter03\\transform", "makehtml.xsl");
		try (InputStream styleIn = Files.newInputStream(path)) {
			StreamSource styleSource = new StreamSource(styleIn);
			Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty("{http:\\xml.apache.org/xslt}indent-amount", "2");
			
			try (InputStream docIn = Files.newInputStream(Paths.get(homeDir + "\\resources", "employee_1.dat"))) {
				t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)), new StreamResult(System.out));
			}
		}
	}

}
