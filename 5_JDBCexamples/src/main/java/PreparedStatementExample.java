import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {
    public static void main(String[] args) {
        try (Connection con = getConnection();
             PreparedStatement ps = con
                     .prepareStatement("SELECT name FROM city WHERE id = ?");
        ) {
            ps.setInt(1, 2807);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("City name: " + rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nice to place this in a seperate class?
     *
     * @return a MySQL connection
     */
    public static Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("world");
        ds.setServerName("localhost");
        ds.setUser("student");
        ds.setPassword("student");
        Connection con = ds.getConnection();
        return con;

    }
}
