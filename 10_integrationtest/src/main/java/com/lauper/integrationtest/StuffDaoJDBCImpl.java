package com.lauper.integrationtest;

import java.sql.*;

public class StuffDaoJDBCImpl implements StuffDao {

	private ConnectionProvider connectionProvider;

	public StuffDaoJDBCImpl() {
		connectionProvider = new MySQLConnectionProvider();
	}

	@Override
	public Stuff getStuff(int stuffId) throws SQLException {
		String query = "SELECT pk, name, value FROM stuff WHERE pk = ?";
		try (Connection connection = connectionProvider.getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query)) {
			pStmt.setInt(1, stuffId);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next()) {
					return new Stuff(rs.getInt(1), rs.getString(2), rs.getInt(3));
				} else {
					return null;
				}
			}
		}
	}

	public void setConnectionProvider(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}



}
