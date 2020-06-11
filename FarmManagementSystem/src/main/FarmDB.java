package main;

import DatabaseSetups.*;
import animals.Rabbit;
import barns.Barn;

public class FarmDB {
    public static void main(String[] args) {
//        AUDIT REPORT database testing
        AuditReportDatabaseSetup.dropTable();
        AuditReportDatabaseSetup.createTable();
        AuditReportDatabaseSetup.insertInTable("20-06-1999", "inserare linie de test");
        AuditReportDatabaseSetup.displayEntries();

//        RABBIT database testing
        RabbitDatabaseSetup.dropTable();
        RabbitDatabaseSetup.createTable();
        RabbitDatabaseSetup.insertRabbit("Female");
        RabbitDatabaseSetup.insertRabbit("Female", "2018-01-02");
        RabbitDatabaseSetup.insertRabbit("Female");
        RabbitDatabaseSetup.insertRabbit("Male", "2017-05-06");
        RabbitDatabaseSetup.deleteById(5);
        RabbitDatabaseSetup.displayEntries();
        RabbitDatabaseSetup.updateById(1, "Male", "2019-01-02");

//        RABBIT FOOD database testing
        RabbitFoodDatabaseSetup.dropTable();
        RabbitFoodDatabaseSetup.createTable();
        RabbitFoodDatabaseSetup.insertRabbitFood("Corn", "kg", 200);
        RabbitFoodDatabaseSetup.insertRabbitFood("Oat", "kg");
        RabbitFoodDatabaseSetup.insertRabbitFood("Hey", "m2", 2);
        RabbitFoodDatabaseSetup.insertRabbitFood("Oat", "kg", 11);
        RabbitFoodDatabaseSetup.insertRabbitFood("Hey", "m2", 25);
        RabbitFoodDatabaseSetup.updateById(1, "Barley", "kg", 15);
        RabbitFoodDatabaseSetup.deleteById(3);
        RabbitFoodDatabaseSetup.displayEntries();


//        CHICKEN database testing
        ChickenDatabaseSetup.dropTable();
        ChickenDatabaseSetup.createTable();
        ChickenDatabaseSetup.insertChicken("Female");
        ChickenDatabaseSetup.insertChicken("Female", "2018-01-02");
        ChickenDatabaseSetup.insertChicken("Female");
        ChickenDatabaseSetup.insertChicken("Male", "2017-05-06");
        ChickenDatabaseSetup.deleteById(3);
        ChickenDatabaseSetup.updateById(1, "Male", "2019-01-02");
        ChickenDatabaseSetup.displayEntries();

//       BARN database testing
        BarnDatabaseSetup.dropTable();
        BarnDatabaseSetup.createTable();
        BarnDatabaseSetup.insertBarn(5, 2, 3, 20, 5);
        BarnDatabaseSetup.insertBarn(3, 10, 2, 50);
        BarnDatabaseSetup.insertBarn(10, 2, 3, 20);
        BarnDatabaseSetup.insertBarn(3, 10, 2, 50, 3);
        BarnDatabaseSetup.insertBarn(3, 10, 2, 50, 51);
        BarnDatabaseSetup.deleteById(3);
        BarnDatabaseSetup.updateById(1, 100, 10, 3, 200, 3);
        BarnDatabaseSetup.displayEntries();

//        WALLET database testing
        WalletDatabaseSetup.dropTable();
        WalletDatabaseSetup.createTable();
        WalletDatabaseSetup.insertWallet("euro", 10000, 53);
        WalletDatabaseSetup.insertWallet("euro", 1000);
        WalletDatabaseSetup.insertWallet("euro", 150000, 23000);
        WalletDatabaseSetup.insertWallet("euro", 100, 3);
        WalletDatabaseSetup.insertWallet("euro", 100, 101);
        WalletDatabaseSetup.deleteById(4);
        WalletDatabaseSetup.updateById(1, "ron", 2000, 200);
        WalletDatabaseSetup.displayEntries();
    }
}
