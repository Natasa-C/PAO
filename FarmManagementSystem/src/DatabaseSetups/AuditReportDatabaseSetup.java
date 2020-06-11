package DatabaseSetups;

import DatabaseHelpers.DatabaseConfiguration;
import DatabaseHelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditReportDatabaseSetup {
    private static String constraints =
            "  (id int PRIMARY KEY AUTO_INCREMENT," +
                    "  date varchar(20) NOT NULL," +
                    "  action varchar(100) NOT NULL)";

    private static String tableName = "auditReport";

    public static void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Audit Report table ready to be used.");
        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void dropTable() {
        String createTableSql = "DROP TABLE IF EXISTS " + tableName;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Audit Report table deleted.");
        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void insertInTable(String date, String action) {
        String constraints = "(date, action) values('" + date + "', '" + action + "')";
        String insertPersonSql = "INSERT INTO " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
//            System.out.println("One line inserted in " + tableName + " table.");
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Can't insert line in " + tableName + " table.");
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void displayEntries() {
        String selectSql = "SELECT * FROM " + tableName;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        System.out.println("\nAll entries from " + tableName + " table.");
        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println();
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Date:" + resultSet.getString(2));
                System.out.println("Action:" + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }
}
