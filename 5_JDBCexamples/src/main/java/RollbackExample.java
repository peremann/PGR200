import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * OBS! Forutsetter:
 * - at du har databasen: world
 * - at du har en DB-engine som tilbyr rollback (ref DB1100 i fjor).
 *
 * @author lauper
 */
public class RollbackExample {
    public static void main(String[] args) {
        // etablering av kontakt med databasen
        try (Connection con = getConnection();) {
            try {
                System.out.println("Setting initial state...");
                updateNorway(con, "Norway");
                printNorway(con);
                System.out.println("Setting autocommit=false");
                con.setAutoCommit(false);
                String dummyName = "XXXXXXXXX";
                System.out.println("Setting dummy name");
                updateNorway(con, dummyName);
                printNorway(con);
                System.out.println("About to throw exception");
                if (("per").length() == 3) {
                    throw new SQLException(
                            "Her kaster jeg et flott exception...");
                }
                con.commit();
            } catch (Exception e) {
                System.out.println("Handling exception, rolling back transaction");
                con.rollback();
                printNorway(con);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Forbindelse lukket!");
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

    private static void updateNorway(Connection con, String newName) throws Exception {
        try (Statement stmt = con.createStatement()) {
            int res = stmt.executeUpdate("UPDATE country_copy SET name = '"
                    + newName + "' WHERE code =  'NOR'");
            System.out.println("Oppdaterte " + res + " byer.");
        }
    }

    private static void printNorway(Connection con)
            throws Exception {
        try (Statement stmt = con.createStatement();
             ResultSet res = stmt
                     .executeQuery("select code, name, population from country_copy"
                             + " where code= 'NOR'")) {
            System.out
                    .println("------------------------------------------------------------------------");
            if(!res.next()) {
                throw new SQLException("Norway not found for code='NOR'");
            }
            System.out.printf("%-20S", res.getString("code"));
            System.out.printf("%-20S", res.getString("name"));
            System.out.printf("%-20S", res.getInt("population"));
            System.out
                    .println("------------------------------------------------------------------------");
            if(res.next()) {
                throw new SQLException("Multiple countries found with code='NOR'");
            }
        }
    }
}
