package Lab5;

import Lab5.BankAccounts.Account;

public class SuspectTransit {
    private final Account from;
    private final Account to;
    private final double amount;

    public SuspectTransit( Account _from, Account _to, double _amount ){
        from = _from;
        to = _to;
        amount = _amount;
    }
}
