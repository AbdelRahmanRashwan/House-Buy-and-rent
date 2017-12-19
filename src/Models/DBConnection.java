package Models;

import java.sql.*;

public class DBConnection {
	private static DBConnection dbConn;
	private static final String dbName = "house_rental";
	private static final String username = "root";
	private static final String password = "56426093";
	private static Connection conn;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		if (dbConn == null) {
			dbConn = new DBConnection();
		}
		return DBConnection.conn;
	}
}
