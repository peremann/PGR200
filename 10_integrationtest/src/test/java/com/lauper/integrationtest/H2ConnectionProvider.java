package com.lauper.integrationtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionProvider implements ConnectionProvider {

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("Unable to find H2 driver");
		}
		return DriverManager.getConnection("jdbc:h2:~/things", "sa", "");
	}

}
