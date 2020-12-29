package Lab5.BankAccounts;

import Lab5.Exceptions.MyException;

import java.util.UUID;

public class Debet extends Account {
    public Debet(double _amount, String _customerID, boolean isSuspect) {
        this.ID = UUID.randomUUID().toString();
        super.customerID = _customerID;
        super.suspect = isSuspect;
        this.setAmount(_amount);
    }

    //capabilities
    public void debit(double x) throws MyException {
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
