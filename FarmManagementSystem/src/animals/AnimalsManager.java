package animals;

import main.Farm;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalsManager {
    private List<Animal> animalsList = new ArrayList<>();

    public void addRabbit(boolean isMale) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: add rabbit, status: success");
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        animalsList.add(new Rabbit(isMale));
    }

    public void addRabbit(String dateOfBirth, boolean isMale) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: add rabbit, status: success");
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
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
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: add chicken, status: success");
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        animalsList.add(new Chicken(isMale));
    }

    public void addChicken(String dateOfBirth, boolean isMale) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: add chicken, status: success");
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
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
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());

        boolean animal_is_in_farm = false;
        for (Animal animal : animalsList) {
            if (animal.getId().equals(animalId)) {
                if (animalId.contains("rabbit")) {
                    animal.removeAnimal();
                    animal_is_in_farm = true;
                    transactionMessage.append("operation: remove rabbit, status: success");
                } else if (animalId.contains("chicken")) {
                    animal_is_in_farm = true;
                    transactionMessage.append("operation: remove chicken, status: success");
                    animal.removeAnimal();
                }
                animalsList.remove(animal);
                break;
            }
        }
        if (!animal_is_in_farm){
            transactionMessage.append("operation: remove chicken, status: failure, message: there is no animal with id ").append(animalId);
        }

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
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
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display general rabbit info, status: success, ");
        transactionMessage.append("total number: ").append(Rabbit.getNoRabbits()).append(", males: ").append(Rabbit.getNoMales()).append(", females: ").append(Rabbit.getNoRabbits() - Rabbit.getNoMales());
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);

        for (Animal animal : animalsList) {
            if (animal instanceof Rabbit){
                animal.displayInfo();
            }
        }
    }

    public void displayChickenInfo() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display general chicken info, status: success, ");
        transactionMessage.append("total number: ").append(Chicken.getNoChickens()).append(", males: ").append(Chicken.getNoMales()).append(", females: ").append(Chicken.getNoChickens() - Chicken.getNoMales());
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);

        for (Animal animal : animalsList) {
            if (animal instanceof Chicken){
                animal.displayInfo();
            }
        }
    }

}