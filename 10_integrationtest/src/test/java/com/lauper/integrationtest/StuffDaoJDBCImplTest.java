package com.lauper.integrationtest;

import org.h2.tools.Server;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class StuffDaoJDBCImplTest {

	StuffDaoJDBCImpl stuffDaoJDBCImpl;
	static Server server;

	@BeforeClass
	public static void setUp() throws SQLException {
		System.out.println("Starting H2 server");
		server = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers").start();
	}

	@AfterClass
	public static void tearDown() {
		System.out.println("Stopping H2 server");
		server.stop();
	}

	@Before
	public void setup() throws SQLException {
		stuffDaoJDBCImpl = new StuffDaoJDBCImpl();
		stuffDaoJDBCImpl.setConnectionProvider(new H2ConnectionProvider());
		Statement stat = getConnection().createStatement();
		stat.execute("create table IF NOT EXISTS stuff(pk int AUTO_INCREMENT primary key, "
				+ "name varchar(255), value int)");
	}

	@After
	public void after() throws SQLException {
		Statement stat = getConnection().createStatement();
		stat.execute("DELETE FROM Stuff");
	}

	@Test
	public void testGetStuff() throws SQLException {
		populateStuffTable();
		Stuff stuff = stuffDaoJDBCImpl.getStuff(3);
		assertEquals(stuff.getPk(), 3);
		assertEquals(stuff.getName(), "testName3");
		assertEquals(stuff.getValue(), 7);
	}

	private void populateStuffTable() throws SQLException {
		Statement stat = getConnection().createStatement();
		stat.execute("insert into stuff values(1, 'testName', 5)");
		stat.execute("insert into stuff values(2, 'testName2', 6)");
		stat.execute("insert into stuff values(3, 'testName3', 7)");
	}

	private Connection getConnection() throws SQLException {
		return stuffDaoJDBCImpl.getConnectionProvider().getConnection();

	}

}
