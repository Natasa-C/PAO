package DatabaseHelpers;
import java.sql.*;

public class DatabaseConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/farmdatabase?" + "autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Maguracumieresialune8";

    private static Connection databaseConnection;

    private DatabaseConfiguration() {
    }

    public static Connection getDatabaseConnection() {
        // try co create a connection to the database
        try {
            if (databaseConnection ==  null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println("Connection established successfully");
        return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        // try co close a connection to the database
        try {
            if (databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println("Connection closed successfully");
    }
}
