package volume02.chapter05.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import volume02.chapter05.util.DBUtil;

/**
 * a frame to do CRUD to records in a database
 * @author mhts
 * @date 2018Äê7ÔÂ9ÈÕ
 */
public class ViewDBFrame extends JFrame {
	private static final long serialVersionUID = -674179485894300048L;
	private JButton previousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton saveButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox<String> tableNames;
	private CachedRowSet crs;
	private Connection conn;
	
	public ViewDBFrame() {
		tableNames = new JComboBox<String>();
		try {
			conn = DBUtil.getConnection();
			/**
			 * set auto commit to false, use RowSet.acceptChanges() to update the record
			 */
			conn.setAutoCommit(false);
			/**
			 * get the meta-data of a connection
			 */
			DatabaseMetaData meta = conn.getMetaData();
			try (ResultSet mrs = meta.getTables(null, null, "%", new String[] {"TABLE"})) {
				while (mrs.next()) {
					tableNames.addItem(mrs.getString(3));
				}
			}
		} catch (SQLException e) {
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
		
		tableNames.addActionListener(event -> showTable((String) tableNames.getSelectedItem(), conn));
		add(tableNames, BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					for (Throwable t : e) {
						t.printStackTrace();
					}
				}
			}
		});
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		previousButton = new JButton("Previous");
		previousButton.addActionListener(event -> showPreviousRow());
		buttonPanel.add(previousButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(event -> showNextRow());
		buttonPanel.add(nextButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(event -> deleteRow());
		buttonPanel.add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(event -> saveChanges());
		buttonPanel.add(saveButton);
		
		if (tableNames.getItemCount() > 0) {
			showTable(tableNames.getItemAt(0), conn);
		}
	}
	
	public void showTable(String tableName, Connection conn) {
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM " + tableName)) {
			
			RowSetFactory factory = RowSetProvider.newFactory();
			crs = factory.createCachedRowSet();
			crs.setTableName(tableName);
			crs.populate(rs);
			
			if (scrollPane != null) {
				remove(scrollPane);
			}
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane, BorderLayout.CENTER);
			pack();
			showNextRow();
		} catch (SQLException e) {
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
	}
	
	public void showPreviousRow() {
		try {
			if (crs == null || crs.isFirst()) {
				return;
			}
			crs.previous();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
	}
	
	public void showNextRow() {
		try {
			if (crs == null || crs.isLast()) {
				return;
			}
			crs.next();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
	}
	
	public void deleteRow() {
		if (crs == null) {
			return;
		}
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				/**
				 * use acceptChanges() to make the change in DB
				 */
				crs.deleteRow();
				crs.acceptChanges(conn);
				if (crs.isAfterLast() && !crs.last()) {
					crs = null;
				}
				return null;
			}
			public void done() {
				dataPanel.showRow(crs);
			}
		}.execute();
	}
	
	public void saveChanges() {
		if (crs == null) {
			return;
		}
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				/**
				 * use acceptChanges() to make the change in DB
				 */
				dataPanel.setRow(crs);
				crs.acceptChanges(conn);
				return null;
			}
		}.execute();
	}
	
}
