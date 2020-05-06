package animals;

import barns.Barn;
import datesAndReports.BuildHeader;
import datesAndReports.BuildHeaderForBarn;
import datesAndReports.BuildHeaderForChicken;
import CSVProcessor.CSVprocesser;
import datesAndReports.BuildHeaderForRabbit;
import main.Farm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chicken extends Animal {
    private static String dataFileName = "dataChicken.csv";
    private static List<Chicken> chickenList = null;
    private static Integer noChickens = 0;
    private static Integer index = 0;
    private static Integer noMales = 0;
    private static HashMap<String, Float> foodAndQuantity = new HashMap<>();

    public Chicken(boolean isMale) {
        super("chicken", index, isMale);
        noChickens++;
        index++;
        if (isMale) {
            noMales++;
        }

        BuildHeader<Chicken> headerBuilder = new BuildHeaderForChicken();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            chickenList = new ArrayList<>();
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        } else if (chickenList == null) {
            chickenList = CSVprocesser.getObjectListFromCSVChicken(dataFileName);
        }
        chickenList.add(this);

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create chicken, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public Chicken(String dateOfBirth, boolean isMale) {
        super("chicken", index, dateOfBirth, isMale);
        noChickens++;
        index++;
        if (isMale) {
            noMales++;
        }

        BuildHeader<Chicken> headerBuilder = new BuildHeaderForChicken();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            chickenList = new ArrayList<>();
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        } else if (chickenList == null) {
            chickenList = CSVprocesser.getObjectListFromCSVChicken(dataFileName);
        }
        chickenList.add(this);

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create chicken, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public Chicken(String id, LocalDate dateOfBirth, boolean isMale) {
        super("rabbit", index, isMale);
        noChickens++;
        index++;
        if (isMale) {
            noMales++;
        }
    }

    public static List<Chicken> getChickenListList() {
        return chickenList;
    }

    @Override
    public void removeAnimal() {
        noChickens--;
        if (this.isMale()) {
            noMales--;
        }
        chickenList.remove(this);
        CSVprocesser.<Chicken>csvUpdateFile(dataFileName, new BuildHeaderForChicken(), chickenList);
    }

    @Override
    public void displayInfo() {
        System.out.println(", age: " + getAnimalAge());
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display chicken info, status: success, ");
        transactionMessage.append("ID: ").append(getId()).append(", gender: ");
        if (isMale()) {
            transactionMessage.append("male");
        } else {
            transactionMessage.append("female");
        }
        transactionMessage.append(", age: ").append(getAnimalAge());

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public static void addFood(String food, Float quantity) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());

        food = food.toLowerCase();
        if (foodAndQuantity.containsKey(food)) {
            foodAndQuantity.replace(food, foodAndQuantity.get(food), quantity);
            transactionMessage.append("operation: replace chicken food quantity, status: success, food: ").append(food).append(", quantity: ").append(quantity);
        } else {
            foodAndQuantity.put(food, quantity);
            transactionMessage.append("operation: add chicken food quantity, status: success, food: ").append(food).append(", quantity: ").append(quantity);
        }

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public static Integer getNoChickens() {
        return noChickens;
    }

    public static Integer getNoMales() {
        return noMales;
    }

    public static void displayFoodStatus() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display chicken food status, status: success, ");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            transactionMessage.append("\n              food: ").append(entry.getKey()).append(", quantity: ").append(entry.getValue());
        }
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public static void displayWeeklyEstimatedFoodNeeds() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display chicken weekly estimated food needs, status: success, ");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            transactionMessage.append("\n              food: ").append(entry.getKey()).append(", quantity: ").append(getNoChickens() * entry.getValue());
        }
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

}