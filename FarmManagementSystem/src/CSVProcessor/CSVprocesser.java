package CSVProcessor;

import animals.Chicken;
import animals.Rabbit;
import barns.Barn;
import budget.Wallet;
import datesAndReports.*;
import main.Farm;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVprocesser {
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

    public static void csvAppendListOfStrings(String csvFile, List<String> list_of_strings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(convertStringListToCSVLineString(list_of_strings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void csvUpdateFile(String dataFileName, BuildHeader<T> headerBuilder, List<T> list) {
        Farm.clearFile(dataFileName);
        Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());

        for (T object : list) {
            Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(object));
        }
    }

    public static List<List<String>> csvLoadFile(String dataFileName) {
        String line;
        String csvLineSplitBy = ",";
        List<List<String>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Farm.getDatesAndReportsPackagePath() + dataFileName))) {
            List<String> fieldList;
            if ((line = br.readLine()) != null) {
                //  ignore first line
            }
            while ((line = br.readLine()) != null) {
                // split line by line separator
                fieldList = Arrays.asList(line.split(csvLineSplitBy));

                for (int index = 0; index < fieldList.size(); index++) {
                    String field = fieldList.get(index);
                    // remove first and last " symbol
                    fieldList.set(index, field.substring(1, field.length() - 1));
                }

                list.add(fieldList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Wallet> getObjectListFromCSV(String dataFileName) {
        List<List<String>> list = csvLoadFile(dataFileName);
        List<Wallet> objectList = new ArrayList<>();
        for (List<String> fieldList : list) {
            Wallet object = new Wallet(fieldList.get(0), Integer.parseInt(fieldList.get(1)), fieldList.get(2), Integer.parseInt(fieldList.get(3)));
            objectList.add(object);
        }
        return objectList;
    }

    public static List<Barn> getObjectListFromCSVBarn(String dataFileName) {
        List<List<String>> list = csvLoadFile(dataFileName);
        List<Barn> objectList = new ArrayList<>();
        for (List<String> fieldList : list) {
            Barn object = new Barn(Integer.parseInt(fieldList.get(0)), Integer.parseInt(fieldList.get(1)), Integer.parseInt(fieldList.get(2)),
                    Integer.parseInt(fieldList.get(3)), Integer.parseInt(fieldList.get(4)), Integer.parseInt(fieldList.get(5)));
            objectList.add(object);
        }
        return objectList;
    }

    public static List<Rabbit> getObjectListFromCSVRabbit(String dataFileName) {
        List<List<String>> list = csvLoadFile(dataFileName);
        List<Rabbit> objectList = new ArrayList<>();
        for (List<String> fieldList : list) {
            Rabbit object = new Rabbit(fieldList.get(0), LocalDate.parse(fieldList.get(1)), Boolean.parseBoolean(fieldList.get(2)));
            objectList.add(object);
        }
        return objectList;
    }

    public static List<Chicken> getObjectListFromCSVChicken(String dataFileName) {
        List<List<String>> list = csvLoadFile(dataFileName);
        List<Chicken> objectList = new ArrayList<>();
        for (List<String> fieldList : list) {
            Chicken object = new Chicken(fieldList.get(0), LocalDate.parse(fieldList.get(1)), Boolean.parseBoolean(fieldList.get(2)));
            objectList.add(object);
        }
        return objectList;
    }

}
