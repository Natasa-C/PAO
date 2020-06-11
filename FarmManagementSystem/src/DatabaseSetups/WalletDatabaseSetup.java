package DatabaseSetups;

import DatabaseHelpers.DatabaseConfiguration;
import DatabaseHelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class WalletDatabaseSetup {
    private  static String constraints =
            "  (id int PRIMARY KEY AUTO_INCREMENT," +
                    "  currency varchar(20) NOT NULL," +
                    "  moneyInWallet int NOT NULL DEFAULT 0," +
                    "  maxWalletLimit int NOT NULL," +
                    "  CHECK (moneyInWallet <= maxWalletLimit))";

    private static String tableName = "wallets";

    public static void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName + constraints;

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        DatabaseHelper repositoryHelper = DatabaseHelper.getDatabaseHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
            System.out.println("Wallet table ready to be used.");
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
            System.out.println("Wallet table deleted.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "dropped table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        System.out.println();
    }

    public static void insertWallet(String currency, int maxWalletLimit, int moneyInWallet) {
        String constraints = "(currency, moneyInWallet, maxWalletLimit) values('"
                + currency + "', '" +  moneyInWallet + "', '" + maxWalletLimit + "')";
        String insertPersonSql = "INSERT INTO " + tableName  + constraints;

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

    public static void insertWallet(String currency, int maxWalletLimit) {
        String constraints = "(currency, maxWalletLimit) values('"
                + currency + "', '" + maxWalletLimit + "')";
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
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Currency:" + resultSet.getString(2));
                System.out.println("MaxWalletLimit:" + resultSet.getInt(3));
                System.out.println("MoneyInWallet:" + resultSet.getInt(4));
            }

            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "display entries from table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public static void updateById(int id, String currency, int maxWalletLimit, int moneyInWallet){
        String updateNameSql = "UPDATE " +  tableName + " SET currency=?, maxWalletLimit=?, moneyInWallet=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, currency);
            preparedStatement.setInt(2, maxWalletLimit);
            preparedStatement.setInt(3, moneyInWallet);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("One line updated in " + tableName + " table.");
            AuditReportDatabaseSetup.insertInTable(LocalDate.now().toString(), "updated line in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
