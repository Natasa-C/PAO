package animals;

import java.util.ArrayList;
import java.util.List;

public class AnimalsManager {
    private List<Animal> animalsList = new ArrayList<>();

    public void addRabbit(boolean isMale) {
        animalsList.add(new Rabbit(isMale));
    }

    public void addRabbit(String dateOfBirth, boolean isMale) {
        animalsList.add(new Rabbit(dateOfBirth, isMale));
    }

    public void addRabbitFood(String food, Float quantity) {
        Rabbit.addFood(food, quantity);
    }

    public void displayRabbitFoodStatus() {
        Rabbit.displayFoodStatus();
    }

    public void displayRabbitWeeklyEstimatedFoodNeeds() {
        Rabbit.displayWeeklyEstimatedFoodNeeds();
    }


    public void addChicken(boolean isMale) {
        animalsList.add(new Chicken(isMale));
    }

    public void addChicken(String dateOfBirth, boolean isMale) {
        animalsList.add(new Chicken(dateOfBirth, isMale));
    }

    public void addChickenFood(String food, Float quantity) {
        Chicken.addFood(food, quantity);
    }

    public void displayChickenFoodStatus() {
        Chicken.displayFoodStatus();
    }

    public void displayChickenWeeklyEstimatedFoodNeeds() {
        Chicken.displayWeeklyEstimatedFoodNeeds();
    }

    public void removeAnimal(String animalId) {
        for (Animal animal : animalsList) {
            if (animal.getId().equals(animalId)) {
                if (animalId.contains("rabbit")) {
                    Rabbit.removeRabbit(animal.isMale());
                }
                else if (animalId.contains("chicken")) {
                    Chicken.removeChicken(animal.isMale());
                }
                animalsList.remove(animal);
                break;
            }
        }
    }

    public void displayInfo(String animalId) {
        for (Animal animal : animalsList) {
            if (animal.getId().equals(animalId)) {
                animal.displayInfo();
                break;
            }
        }
    }

    public void displayRabbitInfo() {
        System.out.println("\n******************************************** RABBIT INFO ********************************************");
        System.out.println("Total number: " + Rabbit.getNoRabbits() + ", males: " + Rabbit.getNoMales() + ", females: " + (Rabbit.getNoRabbits() - Rabbit.getNoMales()));
        for (Animal animal : animalsList) {
            if (animal instanceof Rabbit){
                animal.displayInfo();
            }

        }
    }

    public void displayChickenInfo() {
        System.out.println("\n******************************************** CHICKEN INFO ********************************************");
        System.out.println("Total number: " + Chicken.getNoChickens() + ", males: " + Chicken.getNoMales() + ", females: " + (Chicken.getNoChickens() - Chicken.getNoMales()));
        for (Animal animal : animalsList) {
            if (animal instanceof Chicken){
                animal.displayInfo();
            }
        }
    }

}
