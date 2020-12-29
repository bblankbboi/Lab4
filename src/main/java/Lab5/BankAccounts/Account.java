package Lab5.BankAccounts;

import Lab5.Customer;
import Lab5.Exceptions.MyException;

public abstract class Account {
    //fields
    protected double amount = 0;
    protected boolean suspect = false;
    protected String ID;
    protected int daysAfterClear = 0;
    protected int amountThatTopUpInTheEndOfTheMonth = 0;
    protected String customerID;

    //setters getters
    public double getAmount(){ return amount; }
    public void setAmount( double _amount ){ this.amount = _amount; }
    public void setSuspect(boolean flag){ this.suspect = flag; }
    public boolean getSuspect(){ return suspect; }
    public void setCustomerID( Customer customer ){
        this.customerID = customer.getID();
    }
    public void setCustomerID( String _ID ){
        this.customerID = _ID;
    }
    public String getCustomerID(){ return this.customerID;}

    //capabilities
    public void transit( Account bankAccount, int value ) throws MyException{
        if( suspect == true && value > 10_000 ){
            throw new MyException("Suspect. Try again with <= 10000 amount"); }
        this.debit( value );
        bankAccount.topUp( value );
    }
    abstract public void debit( double x ) throws MyException;
    abstract public void topUp( double x );
    public void nextDay( double everyYearBonus ){
        double everyDayBonus = everyYearBonus / 365;
        amountThatTopUpInTheEndOfTheMonth += this.getAmount() * (1 + everyDayBonus);
        if( daysAfterClear == 31 ){
            this.topUp( amountThatTopUpInTheEndOfTheMonth );
            daysAfterClear = 0;
            amountThatTopUpInTheEndOfTheMonth = 0;
            return;
        }
        daysAfterClear++;
    }
}
