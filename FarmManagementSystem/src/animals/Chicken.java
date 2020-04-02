package animals;

import java.util.HashMap;
import java.util.Map;

public class Chicken extends Animal {
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
    }

    public Chicken(String dateOfBirth, boolean isMale) {
        super("chicken", index, dateOfBirth, isMale);
        noChickens++;
        index++;
        if (isMale) {
            noMales++;
        }
    }

    public static void removeChicken(boolean isMale) {
        noChickens--;
        if (isMale) {
            noMales--;
        }
    }

    @Override
    public void displayInfo() {
        System.out.print("ID: " + getId() + ", gender: ");
        if (isMale()) {
            System.out.print("male");
        } else {
            System.out.print("female");
        }
        System.out.println(", age: " + getAnimalAge());
    }

    public static void addFood(String food, Float quantity) {
        food = food.toLowerCase();
        if (foodAndQuantity.containsKey(food)) {
            foodAndQuantity.replace(food, foodAndQuantity.get(food), quantity);
        } else {
            foodAndQuantity.put(food, quantity);
        }
    }

    public static Integer getNoChickens() {
        return noChickens;
    }

    public static Integer getNoMales() {
        return noMales;
    }

    public static void displayFoodStatus() {
        System.out.println("\nChicken food status:");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", quantity: " + entry.getValue());
        }
    }

    public static void displayWeeklyEstimatedFoodNeeds() {
        System.out.println("\nChicken weekly estimated food needs:");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", quantity: " + (getNoChickens() * entry.getValue()));
        }
    }

}
