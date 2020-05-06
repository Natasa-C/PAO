package barns;

import budget.Wallet;
import datesAndReports.BuildHeader;
import datesAndReports.BuildHeaderForBarn;
import CSVProcessor.CSVprocesser;
import datesAndReports.BuildHeaderForWallet;
import main.Farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class Barn extends Building {
    private static String dataFileName = "dataBarn.csv";
    private static List<Barn> barnList = null;
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

        BuildHeader<Barn> headerBuilder = new BuildHeaderForBarn();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            barnList = new ArrayList<>();
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        }
        else if (barnList == null) {
            barnList = CSVprocesser.getObjectListFromCSVBarn(dataFileName);
        }
        barnList.add(this);

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create barn, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public Barn(Integer length, Integer width, Integer height, Integer barnIndex, Integer totalCapacity, Integer occupiedCapacity){
        super(length, width, height);
        this.barnIndex = barnIndex;
        this.totalCapacity = totalCapacity;
        this.occupiedCapacity = occupiedCapacity;
        noBarns++;
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

    public static List<Barn> getBarnList() {
        return barnList;
    }

    public void addToOccupiedCapacity(Integer capacity) {
        this.occupiedCapacity += capacity;
    }

    public void addInBarn(String produce, Integer quantity) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: addition, status: ");

        if (getOccupiedCapacity() + quantity > getTotalCapacity()) {
            transactionMessage.append("failure,").append(" message: The quantity ").append(quantity).append(" cannot be stored in the barn. Only: ").append(getTotalCapacity() - getOccupiedCapacity()).append(" free capacity");
        } else {
            String produceName = produce.toLowerCase();
            if (produceAndQuantities.containsKey(produceName)) {
                produceAndQuantities.replace(produceName, produceAndQuantities.get(produceName), produceAndQuantities.get(produceName) + quantity);
            } else {
                produceAndQuantities.put(produceName, quantity);
            }
            addToOccupiedCapacity(quantity);
            transactionMessage.append("success, produce: ").append(produceName).append(", quantity: ").append(quantity);
        }
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        CSVprocesser.<Barn>csvUpdateFile(dataFileName, new BuildHeaderForBarn(), barnList);
    }

    public void displayBarnStatus() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display barn info, status: success\n              Barn index: ").append(barnIndex);
        transactionMessage.append("\n              Total capacity: ").append(getTotalCapacity()).append(", occupied capacity: ").append(getOccupiedCapacity()).append(", free capacity: ").append(getTotalCapacity() - getOccupiedCapacity());

        for (Map.Entry<String, Integer> entry : produceAndQuantities.entrySet()) {
            transactionMessage.append("\n              Produce: ").append(entry.getKey()).append(", quantity: ").append(entry.getValue());
        }
        list.add(transactionMessage.toString());
        Farm.appendToReport(list);

        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public void displayTransactionHistory() {
//        StringBuffer transactionMessage = new StringBuffer();
//        List<String> list = new ArrayList<>();
//        list.add(LocalDate.now().toString());
//        transactionMessage.append("operation: display barn, status: success")
//        System.out.println("\nBarn index: " + barnIndex + " - transaction history:");
//        System.out.println(transactionHistory);
//        list.add(transactionMessage.toString());
//        Farm.appendToReport(list);
    }
}