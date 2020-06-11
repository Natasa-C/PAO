package DatabaseSetups;

import DatabaseHelpers.DatabaseConfiguration;
import DatabaseHelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BarnDatabaseSetup {
    private static String constraints =
            "  (id int PRIMARY KEY AUTO_INCREMENT," +
                    "  length int NOT NULL," +
                    "  width int NOT NULL," +
                    "  height int NOT NULL," +
                    "  totalCapacity int NOT NULL," +
                    "  occupiedCapacity int DEFAULT 0," +
                    "  CHECK(occupiedCapacity <= totalCapacity))";

    private static String tableName = "barns";

    public static void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
            System.out.println("Barn table ready to be used.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "created table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void dropTable() {
        String createTableSql = "DROP TABLE IF EXISTS " + tableName;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
            System.out.println("Barn table deleted.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "dropped table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void insertBarn(int length, int width, int height, int totalCapacity, int occupiedCapacity) {
        String constraints = "(length, width, height, totalCapacity, occupiedCapacity) values('"
                + length + "', '" + width + "', '" + height + "', '" + totalCapacity + "', '" + occupiedCapacity + "')";
        String insertPersonSql = "INSERT INTO " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
            System.out.println("One line inserted in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "inserted line in table " + tableName);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Can't insert line in " + tableName + " table.");
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void insertBarn(int length, int width, int height, int totalCapacity) {
        String constraints = "(length, width, height, totalCapacity) values('"
                + length + "', '" + width + "', '" + height + "', '" + totalCapacity + "')";
        String insertPersonSql = "INSERT INTO " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
            System.out.println("One line inserted in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "inserted line in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void deleteById(int id) {
        String selectSql = "DELETE FROM " + tableName + " WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("One line deleted from " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "deleted line from table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
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
                System.out.println("Length:" + resultSet.getInt(2));
                System.out.println("Width:" + resultSet.getInt(3));
                System.out.println("Height:" + resultSet.getInt(4));
                System.out.println("TotalCapacity:" + resultSet.getInt(5));
                System.out.println("OccupiedCapacity:" + resultSet.getInt(6));
            }

            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "display entries from table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void updateById(int id, int length, int width, int height, int totalCapacity, int occupiedCapacity) {
        String updateNameSql = "UPDATE " + tableName + " SET length=?, width=?, height=?," +
                "totalCapacity=?, occupiedCapacity=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, length);
            preparedStatement.setInt(2, width);
            preparedStatement.setInt(3, height);
            preparedStatement.setInt(4, totalCapacity);
            preparedStatement.setInt(5, occupiedCapacity);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();

            System.out.println("One line updated in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "updated line in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
