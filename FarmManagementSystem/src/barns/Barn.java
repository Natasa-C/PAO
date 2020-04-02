package barns;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class Barn extends Building{
    private static Integer noBarns = 0;
    private final Integer barnIndex;
    private final Integer totalCapacity;
    private Integer occupiedCapacity = 0;
    private StringBuffer transactionHistory = new StringBuffer();
    private HashMap<String, Integer> produceAndQuantities = new HashMap<>();

    public Barn(Integer length, Integer width, Integer height) {
        super(length, width, height);
        this.totalCapacity = length * height * width;
        noBarns++;
        this.barnIndex = noBarns;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public Integer getOccupiedCapacity() {
        return occupiedCapacity;
    }

    public Integer getBarnIndex() {
        return barnIndex;
    }

    public void addToOccupiedCapacity(Integer capacity) {
        this.occupiedCapacity += capacity;
    }

    public void addInBarn(String produce, Integer quantity) {
        if (getOccupiedCapacity() + quantity > getTotalCapacity()) {
            System.out.println("Produce quantity too big to be stored in the barn.");
            return;
        }

        String produceName = produce.toLowerCase();
        if (produceAndQuantities.containsKey(produceName)) {
            produceAndQuantities.replace(produceName, produceAndQuantities.get(produceName), produceAndQuantities.get(produceName) + quantity);
        } else {
            produceAndQuantities.put(produceName, quantity);
        }
        addToOccupiedCapacity(quantity);
        transactionHistory.append("Date: ").append(LocalDate.now()).append(", operation: addition, produce: ").append(produceName).append(", quantity: ").append(quantity).append("\n");
    }

    public void displayBarnStatus() {
        System.out.println("\nBarn index: " + barnIndex + " - barn status:");
        System.out.println("Total capacity: " + getTotalCapacity() + ", occupied capacity: " + getOccupiedCapacity() + ", free capacity: " + (getTotalCapacity() - getOccupiedCapacity()));

        for (Map.Entry<String, Integer> entry : produceAndQuantities.entrySet()) {
            System.out.println("Produce: " + entry.getKey() + ", quantity: " + entry.getValue());
        }
    }

    public void displayTransactionHistory() {
        System.out.println("\nBarn index: " + barnIndex + " - transaction history:");
        System.out.println(transactionHistory);
    }
}