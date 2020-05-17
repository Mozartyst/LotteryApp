package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String url = "jdbc:mysql://mozartyst.mysql.dhosting.pl/oofi3u_lotto ? sessionVariables = default_storage_engine = InnoDB & serverTimezone = UTC";
    private static String user = "mae7oo_lotto";
    private static String password = "Lotto1981";



    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
