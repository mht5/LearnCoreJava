package volume02.chapter05.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import volume02.chapter05.util.DBUtil;

/**
 * test JDBC
 * @author mhts
 * @date 2018Äê7ÔÂ6ÈÕ
 */
public class DBTest {
	
	public static void main(String[] args) {
		try (Connection conn = DBUtil.getConnection();
				Statement stat = conn.createStatement()) {
			
			stat.executeUpdate("CREATE TABLE test (name char(20))");
			stat.executeUpdate("INSERT INTO test values('jack')");
			try (ResultSet rs = stat.executeQuery("SELECT * FROM test")) {
				if (rs.next()) {
					System.out.println(rs.getString(1));
				}
			}
			stat.execute("DROP TABLE test");
		} catch (SQLException e) {
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
	}
	
}
