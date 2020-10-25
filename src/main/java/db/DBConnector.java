package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://mozartyst.mysql.dhosting.pl/oofi3u_lotto ? sessionVariables = default_storage_engine = InnoDB & serverTimezone = UTC";
            String user = "mae7oo_lotto";
            String password = "Lotto1981";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
