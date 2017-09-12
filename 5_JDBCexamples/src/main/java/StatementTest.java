import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StatementTest {

	static final int ID_OSLO = 2807;
	
	@Before
	public void setUp() throws Exception {
		updateOslo("Oslo");
	}
	
	@After
	public void tearDown() throws Exception {
		updateOslo("Oslo");
	}

	private void updateOslo(String newName) {
		try (Connection con = getConnection();
				Statement stmt = con.createStatement()) {
			stmt.executeUpdate("UPDATE city SET name = '" + newName + "' WHERE id = " + ID_OSLO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSensitivity() {
		try (Connection con = getConnection();
				Statement firstStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			DatabaseMetaData meta = con.getMetaData();
			boolean res = meta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
			assertTrue(res);
			ResultSet result1 = firstStatement.executeQuery("SELECT name FROM city WHERE id = " + ID_OSLO);
			updateOslo("jalla");
			result1.next();
			assertEquals(result1.getString(1), "jalla");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadOnly() {
		try (Connection con = getConnection();
				Statement firstStatement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_READ_ONLY)) {
			DatabaseMetaData meta = con.getMetaData();
			boolean res = meta.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			assertTrue(res);
			ResultSet result1 = firstStatement.executeQuery("SELECT name FROM city WHERE id = " + ID_OSLO);
			result1.next();
			result1.updateString(1, "jalla");
			fail("update should not be accepted in read only mode");
		} catch (Exception e) {
		}

	}

	private static Connection getConnection() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setDatabaseName("world");
		ds.setServerName("localhost");
		ds.setUser("student");
		ds.setPassword("student");
		Connection con = ds.getConnection();
		return con;
	}

}
