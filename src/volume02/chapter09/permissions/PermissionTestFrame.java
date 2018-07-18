package volume02.chapter09.permissions;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * a frame contains a input and text area
 * @author mhts
 * @date 2018Äê7ÔÂ18ÈÕ
 */
public class PermissionTestFrame extends JFrame {
	private static final long serialVersionUID = 3788685638170518946L;
	private static final int TEXT_ROWS = 20;
	private static final int TEXT_COLUMNS = 60;
	private JTextField textField;
	private WordCheckTextArea textArea;
	
	public PermissionTestFrame() {
		textField = new JTextField(20);
		JPanel panel = new JPanel();
		panel.add(textField);
		JButton openButton = new JButton("Insert");
		panel.add(openButton);
		openButton.addActionListener(event -> insertWords(textField.getText()));
		
		add(panel, BorderLayout.NORTH);
		
		textArea = new WordCheckTextArea();
		textArea.setRows(TEXT_ROWS);
		textArea.setColumns(TEXT_COLUMNS);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		pack();
	}
	
	public void insertWords(String words) {
		try {
			textArea.append(words + "\n");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(this, "I'm sorry, but I can not do that");
			e.printStackTrace();
		}
	}
}
