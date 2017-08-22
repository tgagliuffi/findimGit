package com.bbva.findim.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String DB_DRIVER_CLASS = "oracle.jdbc.xa.client.OracleXADataSource";
	private static final String DB_URL = "jdbc:oracle:thin:@//118.180.34.232:1521/DEVORA12";
	private static final String DB_USERNAME = "FINDIM";
	private static final String DB_PASSWORD = "SuitnMXhiUQdnOB";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			// load the Driver Class
			Class.forName(DB_DRIVER_CLASS);

			// create the connection now
			con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}