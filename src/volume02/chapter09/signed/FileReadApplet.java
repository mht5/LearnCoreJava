package volume02.chapter09.signed;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class FileReadApplet extends JApplet {
	private static final long serialVersionUID = 7712053100915824202L;
	private JTextField fileNameField;
	private JTextArea fileText;
	
	public void init() {
		EventQueue.invokeLater(() -> {
			fileNameField = new JTextField(20);
			JPanel panel = new JPanel();
			panel.add(new JLabel("File name: "));
			JButton openButton = new JButton("Open");
			panel.add(openButton);
			ActionListener listener = event -> loadFile(fileNameField.getText());
			fileNameField.addActionListener(listener);
			openButton.addActionListener(listener);
			add(panel, "North");
			fileText = new JTextArea();
			add(new JScrollPane(fileText), "Center");
		});
	}
	
	public void loadFile(String filename) {
		fileText.setText("");
		try {
			fileText.append(new String(Files.readAllBytes(Paths.get(filename))));
		} catch (IOException e) {
			fileText.append(e + "\n");
		} catch (SecurityException e) {
			fileText.append("I am sorry, but I can not do that.\n");
			fileText.append(e + "\n");
			e.printStackTrace();
		}
	}
	
}
