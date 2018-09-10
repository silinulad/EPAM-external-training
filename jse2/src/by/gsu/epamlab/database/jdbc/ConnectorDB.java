package by.gsu.epamlab.database.jdbc;

import java.sql.*;

import by.gsu.epamlab.constants.Constants;

public enum ConnectorDB {
	INSTANCE;
	private Connection connect;

	public Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connect = DriverManager.getConnection(Constants.DB_URI, Constants.DB_LOGIN, Constants.DB_PASSWORD);
		return connect;
	}

	public void closeConnection() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.err.println("Connection close error: " + e);
			}
		}
	}

	public void closeStatement(Statement... statements) {
		for (final Statement statement : statements) {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("Statement close error: " + e);
				}
			}
		}
	}
}
