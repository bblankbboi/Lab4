package Lab5.BankAccounts;

import Lab5.Exceptions.DepositNotDateException;
import Lab5.Exceptions.EnoughMoneyException;
import Lab5.Exceptions.SuspectLimitException;

public abstract class Account {
    public String customerID;
    //fields
    protected double amount = 0;
    protected boolean suspect = false;
    protected String ID;
    protected int daysBonus = 0;
    protected int amountOfBonus = 0;

    //setters getters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double _amount) {
        this.amount = _amount;
    }

    public boolean getSuspect() {
        return suspect;
    }

    public void setSuspect(boolean flag) {
        this.suspect = flag;
    }

    //capabilities
    public void transit(Account bankAccount, int value) throws SuspectLimitException, EnoughMoneyException, DepositNotDateException {
        if (this.suspect == true && value > 10_000) {
            throw new SuspectLimitException();
        }
        this.getCash(value);
        bankAccount.addMoney(value);
    }

    abstract public void getCash(double x) throws EnoughMoneyException, DepositNotDateException;

    abstract public void addMoney(double x);

    public void nextDay(double everyYearBonus) {
        double everyDayBonus = everyYearBonus / 365;
        amountOfBonus += this.getAmount() * (1 + everyDayBonus);
        if (daysBonus == 31) {
            this.addMoney(amountOfBonus);
            daysBonus = 0;
            amountOfBonus = 0;
            return;
        }
        daysBonus++;
    }
}
