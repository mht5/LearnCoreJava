package volume02.chapter05.exec;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import volume02.chapter05.util.DBUtil;

/**
 * a tool to run SQL in a given file
 * @author mhts
 * @date 2018Äê7ÔÂ6ÈÕ
 */
public class ExecSQL {

	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir") + "\\resources\\Publishers.sql";
		try (Scanner in = new Scanner(Paths.get(path))) {
			try (Connection conn = DBUtil.getConnection();
					Statement stat = conn.createStatement()) {
				
				while (true) {
					if (!in.hasNextLine()) {
						return;
					}
					String line = in.nextLine().trim();
					if (line.equalsIgnoreCase("EXIT")) {
						return;
					}
					if (line.endsWith(";")) {
						line = line.substring(0, line.length() - 1);
					}
					try {
						boolean isResult = stat.execute(line);
						if (isResult) {
							try (ResultSet rs = stat.getResultSet()) {
								showResultSet(rs);
							}
						} else {
							int updateCount = stat.getUpdateCount();
							System.out.println(updateCount + " rows updated.");
						}
					} catch (SQLException e) {
						for (Throwable t : e) {
							t.printStackTrace();
						}
					}
				}
			} catch (SQLException e) {
				for (Throwable t : e) {
					t.printStackTrace();
				}
			}
		}
	}

	public static void showResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		for (int i = 1; i < columnCount; i++) {
			if (i > 1) {
				System.out.print(", ");
			}
			System.out.print(metadata.getColumnLabel(i));
		}
		System.out.println();
		while (rs.next()) {
			for (int i = 1; i < columnCount; i++) {
				if (i > 1) {
					System.out.print(", ");
				}
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
	}

}
