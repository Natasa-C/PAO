package DatabaseSetups;

import DatabaseHelpers.DatabaseConfiguration;
import DatabaseHelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RabbitFoodDatabaseSetup {
    private  static String constraints =
            "  (id int PRIMARY KEY AUTO_INCREMENT," +
                    "  foodType VARCHAR(20) NOT NULL," +
                    "  measurementUnit VARCHAR(10) NOT NULL," +
                    "  quantity int NULL DEFAULT 0)";

    private static String tableName = "rabbitfood";

    public static void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
            System.out.println("Rabbit Food table ready to be used.");
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
            System.out.println("Rabbit Food table deleted.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "dropped table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void insertRabbitFood(String foodType, String measurementUnit, int quantity) {
        String constraints = "(foodType, measurementUnit, quantity) values('" + foodType + "', '" + measurementUnit +
                "', '" + quantity + "')";
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

    public static void insertRabbitFood(String foodType, String measurementUnit) {
        String constraints = "(foodType, measurementUnit) values('" + foodType + "', '" + measurementUnit + "')";
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
                System.out.println("Food Type:" + resultSet.getString(2));
                System.out.println("Measurement Unit:" + resultSet.getString(3));
                System.out.println("Quantity:" + resultSet.getString(4));
            }

            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "display entries from table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void updateById(int id, String foodType, String measurementUnit, int quantity){
        String updateNameSql = "UPDATE " +  tableName + " SET foodType=?, measurementUnit=?, quantity=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, foodType);
            preparedStatement.setString(2, measurementUnit);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("One line updated in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "updated line in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
