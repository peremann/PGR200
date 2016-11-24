package com.lauper.integrationtest;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnectionProvider implements ConnectionProvider {

	private Connection connection;

	/**
	 * Using properties is better...
	 */
	@Override
	public Connection getConnection() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setDatabaseName("things");
		ds.setServerName("localhost");
		ds.setUser("student");
		ds.setPassword("student");
		connection = ds.getConnection();
		return connection;
	}

}
