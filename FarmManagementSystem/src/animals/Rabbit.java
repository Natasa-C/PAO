package animals;

import java.util.HashMap;
import java.util.Map;

public class Rabbit extends Animal {
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
    }

    public Rabbit(String dateOfBirth, boolean isMale) {
        super("rabbit", index, dateOfBirth, isMale);
        noRabbits++;
        index++;
        if (isMale) {
            noMales++;
        }
    }

    public static void removeRabbit(boolean isMale) {
        noRabbits--;
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

    public static Integer getNoRabbits() {
        return noRabbits;
    }

    public static Integer getNoMales() {
        return noMales;
    }

    public static void displayFoodStatus() {
        System.out.println("\nRabbit food status:");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", quantity: " + entry.getValue());
        }
    }

    public static void displayWeeklyEstimatedFoodNeeds() {
        System.out.println("\nRabbit weekly estimated food needs:");
        for (Map.Entry<String, Float> entry : foodAndQuantity.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", quantity: " + (getNoRabbits() * entry.getValue()));
        }
    }

}
