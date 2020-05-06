package main;

import CSVProcessor.CSVprocesser;
import animals.*;
import barns.*;
import budget.Wallet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Farm {
    private static String reportFileName;
    private static String datesAndReportsPackagePath = null;
    private static Farm farm_instance = null;

    private Farm() {
        //  get the path to the current working directory
        File currentDir = new File("").getAbsoluteFile();

        datesAndReportsPackagePath = currentDir + "/src/datesAndReports/";

        // set the path to the csv file in the current directory
        reportFileName = "farmReport.csv";
    }

    public static Farm getFarm() {
        if (farm_instance == null)
            farm_instance = new Farm();
        return farm_instance;
    }

    public static String getDatesAndReportsPackagePath() {
        return datesAndReportsPackagePath;
    }

    public static void appendToReport(List<String> lineToAppend) {
        CSVprocesser.csvAppendListOfStrings(datesAndReportsPackagePath + reportFileName, lineToAppend);
    }

    public static void appendToFile(String fileName, List<String> lineToAppend) {
        CSVprocesser.csvAppendListOfStrings(datesAndReportsPackagePath + fileName, lineToAppend);
    }

    public static boolean checkIfCSVFileExists(String fileName) {
        File tmpDir = new File(datesAndReportsPackagePath + fileName);
        return tmpDir.exists();
    }

    public static void clearFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datesAndReportsPackagePath + fileName, false))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Farm AliFarm = Farm.getFarm();
//        clearFile(Farm.reportFileName);


//        ANIMALS test
        AnimalsManager am = new AnimalsManager();
        am.addRabbit("2018-08-19", true);
        am.addRabbit("2019-10-05", false);
        am.addRabbit(true);
        am.addRabbit("2018-01-02", false);
        am.addRabbit(true);
        am.addRabbit(false);

        am.displayInfo("rabbit0");
        am.displayRabbitInfo();
        am.removeAnimal("rabbit5");
        am.addRabbit("1000-01-10", false);
        am.displayRabbitInfo();

        am.addRabbitFood("wheat", 0.75F);
        am.addRabbitFood("corn", 0.3F);
        am.addRabbitFood("hay", 2F);
        am.addRabbitFood("corn", 0.5F);
        am.displayRabbitFoodStatus();
        am.displayRabbitWeeklyEstimatedFoodNeeds();

//

        am.addChicken("2019-08-19", true);
        am.addChicken(false);
        am.addChicken("2018-01-02", false);
        am.addChicken(false);

        am.displayInfo("chicken0");
        am.displayChickenInfo();
        am.removeAnimal("chicken0");
        am.removeAnimal("chicken3");
        am.addChicken("1000-01-02", false);
        am.addChicken("1001-01-02", false);
        am.displayChickenInfo();

        am.addChickenFood("corn", 1F);
        am.addChickenFood("oat", 0.5F);
        am.displayChickenFoodStatus();
        am.displayChickenWeeklyEstimatedFoodNeeds();


//        BARN MANAGER test
        System.out.println("\n\n\nBARN MANAGER test\n ");
        BarnManager bm = new BarnManager();
        bm.addBarn(10, 20, 4);
        bm.addInBarn(1, "wheat", 20);
        bm.addInBarn(1, "corn", 16);
        bm.addInBarn(1, "wheat", 3);
        bm.addInBarn(1, "wheat", 798);

        bm.addBarn(1, 3, 3, 7);

        bm.addBarn(2, 10, 2);
        bm.addInBarn(3, "Corn", 3);
        bm.addInBarn(3, "corn", 1);
        bm.addInBarn(3, "corn", 3);

        bm.displayBarnStatus(1);
        bm.displayTransactionHistory(1);

        bm.displayBarnStatus();
        bm.displayTransactionHistory();

//        WALLET TEST
        System.out.println("\n\n\nWALLET test\n ");
        Wallet wa = new Wallet("euro");
        Wallet wl = new Wallet("leu");
        wa.addMoney(100);
        wa.withdrawMoney(25);
        wa.addMoney(2530);
//        wa.addMoney(Integer.MAX_VALUE);
        wa.withdrawMoney(10);
        wa.withdrawMoney(3000);
        wa.displayWalletStatus();
        wa.displayTransactionHistory();

        CSVprocesser.csvLoadFile("dataWallet.csv");

    }
}