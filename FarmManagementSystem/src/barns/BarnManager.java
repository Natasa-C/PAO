package barns;

import java.util.ArrayList;
import java.util.List;

public class BarnManager {
    private List<Barn> barnsList = new ArrayList<>();

    public void addBarn(Integer length, Integer width, Integer height) {
        barnsList.add(new Barn(length, width, height));
    }

    public void addBarn(Integer listIndex, Integer length, Integer width, Integer height) {
        barnsList.add(listIndex, new Barn(length, width, height));
    }

    public void addInBarn(Integer barnIndex, String produce, Integer quantity){
        for (Barn barn : barnsList){
            if (barnIndex.equals(barn.getBarnIndex())){
                barn.addInBarn(produce, quantity);
                break;
            }
        }
    }

    public void displayBarnStatus(Integer barnIndex){
        for (Barn barn : barnsList){
            if (barnIndex.equals(barn.getBarnIndex())) {
                barn.displayBarnStatus();
                break;
            }
        }
    }

    public void displayBarnStatus(){
        System.out.println("\n******************************************** BARN STATUS ********************************************");
        for (Barn barn : barnsList){
            barn.displayBarnStatus();
        }
    }

    public void displayTransactionHistory(Integer barnIndex){
        for (Barn barn : barnsList){
            if (barnIndex.equals(barn.getBarnIndex())) {
                barn.displayTransactionHistory();
                break;
            }
        }
    }

    public void displayTransactionHistory(){
        System.out.println("\n******************************************** TRANSACTION HISTORY ********************************************");
        for (Barn barn : barnsList){
            barn.displayTransactionHistory();
        }
    }
}