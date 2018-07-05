package volume02.chapter03.transform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * this class reads a flat text file and reports SAX parser event to act like an XML file
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class EmployeeReader implements XMLReader {
	private ContentHandler handler;
	
	public void parse(InputSource source) throws SAXException, IOException {
		InputStream stream = source.getByteStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String rootElement = "staff";
		AttributesImpl attrs = new AttributesImpl();
		
		if (handler == null) {
			throw new SAXException("No content handler");
		}
		
		handler.startDocument();
		handler.startElement("", rootElement, rootElement, attrs);
		String line;
		while ((line = in.readLine()) != null) {
			handler.startElement("", "employee", "employee", attrs);
			StringTokenizer t = new StringTokenizer(line, "|");
			
			handler.startElement("", "name", "name", attrs);
			String s = t.nextToken();
			handler.characters(s.toCharArray(), 0, s.length());
			handler.endElement("", "name", "name");
			
			handler.startElement("", "salary", "salary", attrs);
			s = t.nextToken();
			handler.characters(s.toCharArray(), 0, s.length());
			handler.endElement("", "salary", "salary");

			attrs.addAttribute("", "year", "year", "CDATA", t.nextToken());
			attrs.addAttribute("", "month", "month", "CDATA", t.nextToken());
			attrs.addAttribute("", "day", "day", "CDATA", t.nextToken());
			handler.startElement("", "hiredate", "hiredate", attrs);
			handler.endElement("", "hiredate", "hiredate");
			attrs.clear();
			
			handler.endElement("", "employee", "employee");
		}
		handler.endElement("", rootElement, rootElement);
		handler.endDocument();
	}

	@Override
	public void setContentHandler(ContentHandler newValue) {
		handler = newValue;
	}

	@Override
	public ContentHandler getContentHandler() {
		return handler;
	}

	@Override
	public DTDHandler getDTDHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityResolver getEntityResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFeature(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getProperty(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(String arg0) throws IOException, SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDTDHandler(DTDHandler arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityResolver(EntityResolver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setErrorHandler(ErrorHandler arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFeature(String arg0, boolean arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String arg0, Object arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}
}
