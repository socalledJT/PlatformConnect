package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

    private static Connection sqlConnection;

    public static Connection getSqlConnection() throws SQLException {
        if (sqlConnection != null) {
            return  sqlConnection;
        } else {
            sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/social_platform?user=root&password=<!Admin!>");
            return sqlConnection;
        }
    }
}
