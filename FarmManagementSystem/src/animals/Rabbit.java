package animals;

import barns.Barn;
import datesAndReports.BuildHeader;
import datesAndReports.BuildHeaderForBarn;
import datesAndReports.BuildHeaderForRabbit;
import main.CSVprocesser;
import main.Farm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rabbit extends Animal {
    private static String dataFileName = "dataRabbit.csv";
    private static List<Rabbit> rabbitList = new ArrayList<>();
    private static Integer noRabbits = 0;
    private static Integer index = 0;
    private static Integer noMales = 0;
    private static HashMap<String, Float> foodAndQuantity = new HashMap<>();

    public Rabbit(boolean isMale) {
        super("rabbit", index, isMale);
        noRabbits++;
        index++;
        if (isMale) {
            noMales++;
        }

        BuildHeader<Rabbit> headerBuilder = new BuildHeaderForRabbit();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        }

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create rabbit, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        rabbitList.add(this);
    }

    public Rabbit(String dateOfBirth, boolean isMale) {
        super("rabbit", index, dateOfBirth, isMale);
        noRabbits++;
        index++;
        if (isMale) {
            noMales++;
        }

        BuildHeader<Rabbit> headerBuilder = new BuildHeaderForRabbit();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        }

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create rabbit, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        rabbitList.add(this);
    }

    public static List<Rabbit> getRabbitList() {
        return rabbitList;
    }

    @Override
    public void removeAnimal() {
        noRabbits--;
        if (this.isMale()) {
            noMales--;
        }
        rabbitList.remove(this);
        CSVprocesser.csvUpdateRabbitFile(dataFileName);
    }

    @Override
    public void displayInfo() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display rabbit info, status: success, ");
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
            transactionMessage.append("operation: replace rabbit food quantity, status: success, food: ").append(food).append(", quantity: ").append(quantity);
        } else {
            foodAndQuantity.put(food, quantity);
            transactionMessage.append("operation: add rabbit food quantity, status: success, food: ").append(food).append(", quantity: ").append(quantity);
        }

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public static Integer getNoRabbits() {
        return noRabbits;
    }

    public static Integer getNoMales() {
        return noMales;
    }

    public static void displayFoodStatus() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display rabbit food status, status: success, ");
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
        transactionMessage.append("operation: display rabbit weekly estimated food needs, status: success, ");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            transactionMessage.append("\n              food: ").append(entry.getKey()).append(", quantity: ").append(getNoRabbits() * entry.getValue());
        }
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }
}