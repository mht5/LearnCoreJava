package volume02.chapter03.read;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * this panel uses an XML file to describe its components and their grid bag layout positions
 * @author mhts
 * @date 2018Äê7ÔÂ4ÈÕ
 */
public class GridBagPane extends JPanel{
	private static final long serialVersionUID = 7662062439377516725L;
	private GridBagConstraints constraint;

	public GridBagPane(File file) {
		setLayout(new GridBagLayout());
		constraint = new GridBagConstraints();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			
			if (file.toString().contains("-schema")) {
				factory.setNamespaceAware(true);
				final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
				final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
				factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			}
			
			factory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			parseGridbag(doc.getDocumentElement());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Component get(String name) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i].getName().equals(name)) {
				return components[i];
			}
		}
		return null;
	}
	
	private void parseGridbag(Element element) {
		NodeList rows = element.getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Element row = (Element) rows.item(i);
			NodeList cells = row.getChildNodes();
			for (int j = 0; j < cells.getLength(); j++) {
				Element cell = (Element) cells.item(j);
				parseCell(cell, i, j);
			}
		}
	}
	
	private void parseCell(Element e, int r, int c) {
		String value = e.getAttribute("gridx");
		if (value.length() == 0) {
			if (c == 0) {
				constraint.gridx = 0;
			} else {
				constraint.gridx += constraint.gridwidth;
			}
		} else {
			constraint.gridx = Integer.parseInt(value);
		}
		
		value = e.getAttribute("gridy");
		if (value.length() == 0) {
			constraint.gridy = r;
		} else {
			constraint.gridy = Integer.parseInt(value);
		}

		constraint.gridwidth = Integer.parseInt(e.getAttribute("gridwidth"));
		constraint.gridheight = Integer.parseInt(e.getAttribute("gridheight"));
		constraint.weightx = Integer.parseInt(e.getAttribute("weightx"));
		constraint.weighty = Integer.parseInt(e.getAttribute("weighty"));
		constraint.ipadx = Integer.parseInt(e.getAttribute("ipadx"));
		constraint.ipady = Integer.parseInt(e.getAttribute("ipady"));
		
		Class<GridBagConstraints> cl = GridBagConstraints.class;
		
		try {
			String name = e.getAttribute("fill");
			Field f = cl.getField(name);
			constraint.fill = f.getInt(cl);
			name = e.getAttribute("anchor");
			f = cl.getField(name);
			constraint.anchor = f.getInt(cl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Component comp = (Component) parseBean((Element) e.getFirstChild());
		add(comp, constraint);
	}
	
	private Object parseBean(Element e) {
		try {
			NodeList children = e.getChildNodes();
			Element classElement = (Element) children.item(0);
			String className = ((Text) classElement.getFirstChild()).getData();
			
			Class<?> cl = Class.forName(className);
			Object obj = cl.newInstance();
			if (obj instanceof Component) {
				((Component) obj).setName(e.getAttribute("id"));
			}
			
			for (int i = 1; i < children.getLength(); i++) {
				Node propertyElement = children.item(i);
				Element nameElement = (Element) propertyElement.getFirstChild();
				String propertyName = ((Text) nameElement.getFirstChild()).getData();
				
				Element valueElement = (Element) propertyElement.getLastChild();
				Object value = parseValue(valueElement);
				
				BeanInfo beanInfo = Introspector.getBeanInfo(cl);
				PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
				boolean done = false;
				for (int j = 0; !done && j < descriptors.length; j++) {
					if (descriptors[j].getName().equals(propertyName)) {
						descriptors[j].getWriteMethod().invoke(obj, value);
						done = true;
					}
				}
			}
			return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private Object parseValue(Element e) {
		Element child = (Element) e.getFirstChild();
		if (child.getTagName().equals("bean")) {
			return parseBean(child);
		}
		String text = ((Text) child.getFirstChild()).getData();
		if (child.getTagName().equals("int")) {
			return new Integer(text);
		} else if (child.getTagName().equals("boolean")) {
			return new Boolean(text);
		} else if (child.getTagName().equals("string")) {
			return text;
		} else {
			return null;
		}
	}
}
