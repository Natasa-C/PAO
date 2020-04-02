package budget;
import java.time.LocalDate;

public class Wallet {
    private static Integer noWallets = 0;
    private static final Integer maxWalletLimit = Integer.MAX_VALUE - 1;
    private Integer moneyInWallet = 0;
    private final String currency;
    private StringBuffer transactionHistory = new StringBuffer("\nTransaction history:\n");

    public Wallet(String currency) {
        noWallets++;
        this.currency = currency.toLowerCase();
    }

    public void setSumInWallet(Integer sumInWallet) {
        this.moneyInWallet = sumInWallet;
    }

    public static Integer getNoWallets() {
        return noWallets;
    }

    public Integer getSumInWallet() {
        return moneyInWallet;
    }

    public String getCurrency() {
        return currency;
    }

    public void addMoney(Integer sum) {
//        overflow must be handled
        if (sum + moneyInWallet <= maxWalletLimit) {
            this.moneyInWallet += sum;
            transactionHistory.append(LocalDate.now()).append(": added " + sum + " " + currency + "\n");
        } else{
            System.out.println("Wallet limit exceeded");
            transactionHistory.append(LocalDate.now()).append(": Wallet limit exceeded. Impossible to add " + sum + " " + currency + "\n");
        }

    }

    public void withdrawMoney(Integer sum) {
        if (sum > moneyInWallet){
            System.out.println("Insufficient funds");
            transactionHistory.append(LocalDate.now()).append(": Insufficient funds. Impossible to withdraw " + sum + " " + currency + "\n");
        }
        else {
            this.moneyInWallet -= sum;
            transactionHistory.append(LocalDate.now()).append(": withdraw " + sum + " " + currency + "\n");
        }
    }

    public void displayWalletStatus() {
        System.out.println("\nWallet status:\nCurrency: " + this.currency + ", Sold: " + moneyInWallet);
    }

    public void displayTransactionHistory(){
        System.out.println(transactionHistory);
    }
}
