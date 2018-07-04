package volume02.chapter03.read;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * a frame contains a font selection dialog that is described by an XML file
 * @author mhts
 * @date 2018Äê7ÔÂ4ÈÕ
 */
public class FontFrame extends JFrame {
	private static final long serialVersionUID = -1743058400407335539L;
	private GridBagPane gridBag;
	private JComboBox<String> face;
	private JComboBox<String> size;
	private JCheckBox bold;
	private JCheckBox italic;
	
	@SuppressWarnings("unchecked")
	public FontFrame(File file) {
		gridBag = new GridBagPane(file);
		add(gridBag);
		
		face = (JComboBox<String>) gridBag.get("face");
		size = (JComboBox<String>) gridBag.get("size");
		bold = (JCheckBox) gridBag.get("bold");
		italic = (JCheckBox) gridBag.get("italic");
		
		face.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"}));
		size.setModel(new DefaultComboBoxModel<String>(new String[] {
				"8", "10", "11", "13", "17", "19", "23"}));
		
		ActionListener listener = event -> setSample();
		
		face.addActionListener(listener);
		size.addActionListener(listener);
		bold.addActionListener(listener);
		italic.addActionListener(listener);
		
		setSample();
		pack();
	}
	
	public void setSample() {
		String fontFace = face.getItemAt(face.getSelectedIndex());
		int fontSize = Integer.parseInt(size.getItemAt(size.getSelectedIndex()));
		JTextArea sample = (JTextArea) gridBag.get("sample");
		int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);
		sample.setFont(new Font(fontFace, fontStyle, fontSize));
		sample.repaint();
	}
}
