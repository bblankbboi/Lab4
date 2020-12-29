package Lab5.BankAccounts;

import Lab5.Exceptions.MyException;
import Lab5.Manager;

import java.util.Date;
import java.util.UUID;

public class Deposit extends Account {
    //fields
    private Date dateOfWork;
    private boolean work = false;

    public Deposit(Date _dateOfWork, double _amount, String _customerID, boolean isSuspect) {
        this.setAmount(_amount);
        this.dateOfWork = _dateOfWork;
        super.customerID = _customerID;
        super.suspect = isSuspect;
        this.ID = UUID.randomUUID().toString();
    }

    //stuff

    @Override
    public void nextDay(double everyYearBonus) {
        if (Manager.getDate().after(dateOfWork)) {
            double bonus;
            if (this.getAmount() < 50_000) {
                bonus = 0.03;
            } else if (this.getAmount() >= 50_000 && this.getAmount() < 100_000) {
                bonus = 0.035;
            } else {
                bonus = 0.04;
            }
            this.work = true;
            double temp = this.getAmount() * (1 + bonus);
            this.topUp(temp);
        }
        super.nextDay(everyYearBonus);
    }

    //capabilities
    @Override
    public void transit(Account bankAccount, int value) throws MyException {
        if (!this.work) {
            throw new MyException("Deposit account not working yet.");
        }
        this.debit(value);
        bankAccount.topUp(value);
    }

    public void debit(double x) throws MyException {
        if (!this.work) {
            throw new MyException("Deposit account not working yet.");
        }
        double temp = this.getAmount() - x;
        if (temp < 0) {
            throw new MyException("Enough money.");
        }
        this.setAmount(temp);
    }

    public void topUp(double x) {
        double temp = this.getAmount() + x;
        this.setAmount(temp);
    }
}
