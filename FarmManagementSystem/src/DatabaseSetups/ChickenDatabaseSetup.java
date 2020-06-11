package DatabaseSetups;

import DatabaseHelpers.DatabaseConfiguration;
import DatabaseHelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ChickenDatabaseSetup {
    private  static String constraints =
            "  (id int PRIMARY KEY AUTO_INCREMENT," +
                    "  gender VARCHAR(10) NULL," +
                    "  dateofbirth DATETIME NULL)";

    private static String tableName = "chickens";

    public static void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
            System.out.println("Chicken table ready to be used.");
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
            System.out.println("Chicken table deleted.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "dropped table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void insertChicken(String gender) {
        String constraints = "(gender, dateofbirth) values('" + gender + "', '" +  LocalDate.now().toString() + "')";
        String insertPersonSql = "INSERT INTO " + tableName  + constraints;

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

    public static void insertChicken(String gender, String date) {
        String constraints = "(gender, dateofbirth) values('" + gender + "', '" +  date + "')";
        String insertPersonSql = "INSERT INTO " + tableName  + constraints;

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

    public static void deleteById( int id) {
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
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("Gender:" + resultSet.getString(2));
                System.out.println("DateOfBirth:" + resultSet.getString(3));
            }

            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "display entries from table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void updateById(int id, String gender, String date){
        String updateNameSql = "UPDATE " +  tableName + " SET gender=?, dateofbirth=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, gender);
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

            System.out.println("One line updated in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "updated line in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
