package main;

import animals.Chicken;
import animals.Rabbit;
import barns.Barn;
import budget.Wallet;
import datesAndReports.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVprocesser {
    public static void csvRead(String csvFile) {
        String line;
        String csvLineSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String> fieldList;
            while ((line = br.readLine()) != null) {
                // split line by line separator
                fieldList = Arrays.asList(line.split(csvLineSplitBy));

                for (int index = 0; index < fieldList.size(); index++) {
                    String field = fieldList.get(index);
                    // remove first and last " symbol
                    fieldList.set(index, field.substring(1, field.length() - 1));
                }
                System.out.println(fieldList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertStringListToCSVLineString(List<String> string_to_be_converted) {
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < string_to_be_converted.size(); index++) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append("\"" + string_to_be_converted.get(index) + "\"");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void csvAppendLine(String csvFile, String lineToAppend) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(lineToAppend);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void csvAppendListOfStrings(String csvFile, List<String> list_of_strings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(convertStringListToCSVLineString(list_of_strings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void csvUpdateWalletFile(String dataFileName) {
        BuildHeader<Wallet> headerBuilder = new BuildHeaderForWallet();

        Farm.clearFile(dataFileName);
        Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());

        List<Wallet> list = Wallet.getWalletList();
        for (Wallet wallet : list) {
            Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(wallet));
        }
    }

    public static List<Wallet> csvLoadWalletFile(String dataFileName) {
        String line;
        String csvLineSplitBy = ",";
        List<Wallet> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Farm.getDatesAndReportsPackagePath() +  dataFileName))) {
            List<String> fieldList;
            if ((line = br.readLine()) != null){
//            ignore first line
            }
            while ((line = br.readLine()) != null) {
                // split line by line separator
                fieldList = Arrays.asList(line.split(csvLineSplitBy));

                for (int index = 0; index < fieldList.size(); index++) {
                    String field = fieldList.get(index);
                    // remove first and last " symbol
                    fieldList.set(index, field.substring(1, field.length() - 1));
                }
                Wallet wallet = new Wallet(fieldList.get(0), Integer.parseInt(fieldList.get(1)), fieldList.get(2), Integer.parseInt(fieldList.get(3)));
                list.add(wallet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void csvUpdateBarnFile(String dataFileName) {
        BuildHeader<Barn> headerBuilder = new BuildHeaderForBarn();

        Farm.clearFile(dataFileName);
        Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());

        List<Barn> list = Barn.getBarnList();
        for (Barn barn : list) {
            Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(barn));
        }
    }

    public static void csvUpdateRabbitFile(String dataFileName) {
        BuildHeader<Rabbit> headerBuilder = new BuildHeaderForRabbit();

        Farm.clearFile(dataFileName);
        Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());

        List<Rabbit> list = Rabbit.getRabbitList();
        for (Rabbit rabbit : list) {
            Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(rabbit));
        }
    }

    public static void csvUpdateChickenFile(String dataFileName) {
        BuildHeader<Chicken> headerBuilder = new BuildHeaderForChicken();

        Farm.clearFile(dataFileName);
        Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());

        List<Chicken> list = Chicken.getChickenListList();
        for (Chicken chicken : list) {
            Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(chicken));
        }
    }
}
