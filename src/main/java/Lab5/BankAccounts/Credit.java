package Lab5.BankAccounts;

import Lab5.Exceptions.MyException;

import java.util.UUID;

public class Credit extends Account{
    //fields
    final int MINUS_LIMIT;
    final double MINUS_TAX;

    //
    public Credit( int _MINUS_LIMIT, double _MINUS_TAX, double _amount ){
        this.ID =  UUID.randomUUID().toString();
        this.MINUS_LIMIT = _MINUS_LIMIT;
        this.MINUS_TAX = _MINUS_TAX;
        this.setAmount(_amount);
    }

    //stuff
    private double tax( double value ){
        if( this.amount < 0 ){ return value * this.MINUS_TAX; }
        return 0;
    }

    //capabilities
    public void debit( double x ) throws MyException{
        double temp = this.getAmount() - x - tax(x);
        if( temp < MINUS_LIMIT ){
            throw new MyException("Enought money.");
        }
        this.setAmount( temp );
    }
    public void topUp(double x ){
        double temp = this.getAmount() + x - tax(x);
        this.setAmount( temp );
    }
}