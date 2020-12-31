package Lab5;

import Lab5.BankAccounts.Account;
import Lab5.Exceptions.DepositNotDateException;
import Lab5.Exceptions.EnoughMoneyException;

import java.util.UUID;

public class SuspectTransit {
    public final String ID;
    private final Account from;
    private final Account to;
    private final double amount;

    public SuspectTransit(Account _from, Account _to, double _amount) {
        from = _from;
        to = _to;
        amount = _amount;
        this.ID = UUID.randomUUID().toString();
    }

    public void cancel() throws EnoughMoneyException, DepositNotDateException {
        to.getCash(amount);
        from.addMoney(amount);
    }
}
