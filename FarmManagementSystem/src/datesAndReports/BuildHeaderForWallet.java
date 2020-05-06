package datesAndReports;

import budget.Wallet;

import java.util.ArrayList;
import java.util.List;

public class BuildHeaderForWallet implements BuildHeader<Wallet>{
    @Override
    public List<String> getHeaderLine() {
        List<String> list = new ArrayList<>();
        list.add("walletIndex");
        list.add("moneyInWallet");
        list.add("currency");
        list.add("maxWalletLimit");
        return list;
    }

    @Override
    public List<String> getEntryLine(Wallet wallet) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(wallet.getmaxWalletIndex()));
        list.add(String.valueOf(wallet.getMoneyInWallet()));
        list.add(wallet.getCurrency());
        list.add(String.valueOf(wallet.getmaxWalletLimit()));
        return list;
    }
}
