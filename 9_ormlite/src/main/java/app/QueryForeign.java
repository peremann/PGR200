package app;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import dto.Account;
import dto.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class QueryForeign {

	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/orm";

	private Dao<Account, Integer> accountDao;
	private Dao<Order, Integer> orderDao;

	public static void main(String[] args) throws Exception {
		// turn our static method into an instance of Main
		new QueryForeign().doMain(args);
	}

	private void doMain(String[] args) throws SQLException, IOException {
		JdbcConnectionSource connectionSource = null;
		try {
			// create our data source
			connectionSource = new JdbcConnectionSource(DATABASE_URL,
					"student", "student");
			queryTables(connectionSource);
			System.out.println("\n\nIt seems to have worked\n\n");
		} finally {
			// destroy the data source which should close underlying connections
			if (connectionSource != null) {
				connectionSource.close();
			}
		}
	}

	private void queryTables(JdbcConnectionSource connectionSource) throws SQLException {
		accountDao = DaoManager.createDao(connectionSource, Account.class);
		orderDao = DaoManager.createDao(connectionSource, Order.class);
		Account account = accountDao.queryForId(1);
		List<Order> results =
				orderDao.queryBuilder().where().
				eq("account_id", account.getId()).query();
		System.out.println("Antall:"+results.size());
		for (Order order : results) {
			System.out.println("Order:"+order.toString());
		}
	}
}
