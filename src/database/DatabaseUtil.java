package database;

import java.sql.*;

public class DatabaseUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/db";

	private static final String USER = "USERNAME";
	private static final String PASSWORD = "PASSWORD";

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
