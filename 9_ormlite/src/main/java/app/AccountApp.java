package app;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dto.Account;
import dto.Order;

import java.util.List;

public class AccountApp {

	public static void main(String[] args) throws Exception {
		// this uses mysql, db named orm
		String databaseUrl = "jdbc:mysql://localhost:3306/orm";
		// create a connection source to our database
		ConnectionSource connectionSource = new JdbcConnectionSource(
				databaseUrl, "student", "student");

		// instantiate the dao
		Dao<Account, String> accountDao = DaoManager.createDao(
				connectionSource, Account.class);

		// if you need to create the 'accounts' table make this call
		TableUtils.createTableIfNotExists(connectionSource, Account.class);
		TableUtils.createTableIfNotExists(connectionSource, Order.class);

		/*
		 * Once we have configured our database objects, we can use them to
		 * persist an Account to the database and query for it from the database
		 * by its ID:
		 */

		// create an instance of Account
		Account account = new Account();
		account.setName("Jim Coakley");

		// persist the account object to the database
		accountDao.createIfNotExists(account);

		// retrieve the account from the database by its field (name)
		List<Account> accounts = accountDao.queryForEq(Account.NAME_FIELD_NAME, "Jim Coakley");
		System.out.println("Account: " + accounts.get(0).getName());
		
		Order order = new Order("Jim Sanders", 12.34);
		order.setAccount(account);
		// instantiate the dao
				Dao<Order, String> orderDao = DaoManager.createDao(
						connectionSource, Order.class);
		orderDao.create(order);
		
		// close the connection source
		connectionSource.close();
	}
}