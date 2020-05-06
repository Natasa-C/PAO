package budget;

import datesAndReports.BuildHeader;
import datesAndReports.BuildHeaderForWallet;
import main.CSVprocesser;
import main.Farm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet {
    private static List<Wallet> walletList;
    private static String dataFileName;
    private static Integer noWallets = 0;
    private final String walletIndex;
    private final Integer maxWalletLimit;
    private Integer moneyInWallet = 0;
    private final String currency;
    private StringBuffer transactionHistory = new StringBuffer("\nTransaction history:\n");

    public Wallet(String currency, String dataFileName) {
        noWallets++;
        walletIndex = UUID.randomUUID().toString();
        this.maxWalletLimit = Integer.MAX_VALUE - 1;
        this.currency = currency.toLowerCase();
        Wallet.dataFileName = dataFileName;

        BuildHeader<Wallet> headerBuilder = new BuildHeaderForWallet();
        if (!Farm.checkIfCSVFileExists(dataFileName)) {
            walletList = new ArrayList<>();
            Farm.clearFile(dataFileName);
            Farm.appendToFile(dataFileName, headerBuilder.getHeaderLine());
        }
        else{
            walletList = CSVprocesser.csvLoadWalletFile(dataFileName);
        }
        walletList.add(this);

        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: create wallet, status: success");

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        Farm.appendToFile(dataFileName, headerBuilder.getEntryLine(this));
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public Wallet(String walletIndex, Integer moneyInWallet, String currency, Integer maxWalletLimit){
        this.walletIndex = walletIndex;
        this.moneyInWallet = moneyInWallet;
        this.maxWalletLimit = maxWalletLimit;
        this.currency = currency;
    }
    public void setSumInWallet(Integer sumInWallet) {
        this.moneyInWallet = sumInWallet;
    }

    public static Integer getNoWallets() {
        return noWallets;
    }

    public Integer getMoneyInWallet() {
        return moneyInWallet;
    }

    public Integer getmaxWalletLimit() {
        return maxWalletLimit;
    }
    public String getmaxWalletIndex() {
        return walletIndex;
    }

    public static List<Wallet> getWalletList() {
        return walletList;
    }

    public String getCurrency() {
        return currency;
    }

    public void addMoney(Integer sum) {
//        overflow must be handled
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: addition, status: ");
        if (sum + moneyInWallet <= maxWalletLimit) {
            transactionMessage.append("success, amount: ").append(sum).append(" ").append(currency);
            this.moneyInWallet += sum;
        } else {
            transactionMessage.append("failure, message: Wallet limit exceeded. Impossible to add ").append(sum).append(" ").append(currency);
        }

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        CSVprocesser.csvUpdateWalletFile(dataFileName);
    }

    public void withdrawMoney(Integer sum) {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: withdraw, status: ");
        if (sum > moneyInWallet) {
            transactionMessage.append("failure, message: Insufficient funds. Impossible to withdraw ").append(sum).append(" ").append(currency);
        } else {
            transactionMessage.append("success, amount: ").append(sum).append(" ").append(currency);
            this.moneyInWallet -= sum;
        }

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
        CSVprocesser.csvUpdateWalletFile(dataFileName);
    }

    public void displayWalletStatus() {
        StringBuffer transactionMessage = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add(LocalDate.now().toString());
        transactionMessage.append("operation: display wallet info, status: success");
        transactionMessage.append(", wallet info: ").append("currency: ").append(this.currency).append(", sold: ").append(moneyInWallet);

        list.add(transactionMessage.toString());
        Farm.appendToReport(list);
        System.out.println(LocalDate.now().toString() + " " + transactionMessage);
    }

    public void displayTransactionHistory() {

    }
}