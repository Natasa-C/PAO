import animals.*;
import barns.*;
import budget.Wallet;

public class Farm {
    public static void main(String[] args) {
//        ANIMALS test
        AnimalsManager am = new AnimalsManager();
        am.addRabbit("2018-08-19", true);
        am.addRabbit("2019-10-05", false);
        am.addRabbit(true);
        am.addRabbit("2018-01-02", false);
        am.addRabbit(true);
        am.addRabbit(false);

        am.displayInfo("rabbit0");
        am.displayRabbitInfo();
        am.removeAnimal("rabbit3");
        am.displayRabbitInfo();

        am.addRabbitFood("wheat", 0.75F);
        am.addRabbitFood("corn", 0.3F);
        am.addRabbitFood("hay", 2F);
        am.displayRabbitFoodStatus();
        am.displayRabbitWeeklyEstimatedFoodNeeds();

//

        am.addChicken("2019-08-19", true);
        am.addChicken(false);
        am.addChicken("2018-01-02", false);
        am.addChicken(false);

        am.displayInfo("chicken0");
        am.displayChickenInfo();
        am.removeAnimal("chicken4");
        am.displayChickenInfo();

        am.addChickenFood("corn", 1F);
        am.addChickenFood("oat", 0.5F);
        am.displayChickenFoodStatus();
        am.displayChickenWeeklyEstimatedFoodNeeds();


//        BARN MANAGER test
        System.out.println("\n\n\nBARN MANAGER test\n ");
        BarnManager bm = new BarnManager();
        bm.addBarn(10, 20, 4);
        bm.addInBarn(1, "wheat", 20);
        bm.addInBarn(1, "corn", 16);
        bm.addInBarn(1, "wheat", 3);

        bm.addBarn(1, 3, 3, 7);

        bm.addBarn(2, 10, 2);
        bm.addInBarn(3, "Corn", 3);
        bm.addInBarn(3, "corn", 1);
        bm.addInBarn(3, "corn", 3);

        bm.displayBarnStatus(1);
        bm.displayTransactionHistory(1);

        bm.displayBarnStatus();
        bm.displayTransactionHistory();

//        WALLET TEST
        System.out.println("\n\n\nWALLET test\n ");
        Wallet wa = new Wallet("euro");
        wa.addMoney(100);
        wa.withdrawMoney(25);
        wa.addMoney(2530);
//        wa.addMoney(Integer.MAX_VALUE);
        wa.withdrawMoney(10);
        wa.withdrawMoney(3000);
        wa.displayWalletStatus();
        wa.displayTransactionHistory();

    }
}
