package volume02.chapter05.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * a tool to get DB connection
 * @author mhts
 * @date 2018Äê7ÔÂ6ÈÕ
 */
public class DBUtil {
	
	public static Connection getConnection() throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/corejava";
		String username = "root";
		String password = "passw0rd";
		
		System.setProperty("jdbc.drivers", driver);
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
