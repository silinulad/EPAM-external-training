package com.example.intouch.model.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.example.intouch.constans.JDBCPath;
import com.example.intouch.exceptions.DAOException;

public class ConnectionDB {

	public Connection getConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		return initConnection();
	}

	public void closeResources(AutoCloseable... resources) throws DAOException {
		try {
			for (AutoCloseable resource : resources) {
				if (resource != null) {
					resource.close();
				}
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	private Connection initConnection()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		final String JDBC_PROPERTY_FILENAME = "jdbc.properties";
		final String JDBC_DRIVER = "jdbc.driver";
		final String JDBC_URL = "jdbc.databaseurl";
		final String JDBC_NAME = "jdbc.username";
		final String JDBC_PASSWORD = "jdbc.password";

		Properties properties = new Properties();
		FileInputStream inputStream = null;

		try {
			inputStream = new FileInputStream(JDBCPath.getPathJDBC() + JDBC_PROPERTY_FILENAME);
			properties.load(inputStream);

			Class.forName(properties.getProperty(JDBC_DRIVER));
			return DriverManager.getConnection(properties.getProperty(JDBC_URL), properties.getProperty(JDBC_NAME),
					properties.getProperty(JDBC_PASSWORD));
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

}
