package Lab5;

import Lab5.BankAccounts.*;
import Lab5.Exceptions.MyException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {
    private final int MINUS_LIMIT;
    private final int MINUS_TAX;
    //fields
    private List<Account> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<SuspectTransit> sus = new ArrayList<>();
    private String name;
    private double yearBonus;


    public Bank(String _name, int _MINUS_LIMIT, int _MINUS_TAX) {
        this.setName(_name);
        this.MINUS_LIMIT = _MINUS_LIMIT;
        this.MINUS_TAX = _MINUS_TAX;
    }

    //stufff
    private void susTransit(Account from, Account to, double amount) {
        if (from.getSuspect()) {
            sus.add(new SuspectTransit(from, to, amount));
        }
    }

    public String getName() {
        return this.name;
    }

    //getters setters
    //name
    public void setName(String _name) {
        this.name = _name;
    }

    //accounts
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void setAccount(List<Account> _accounts) {
        accounts.clear();
        accounts.addAll(_accounts);
    }

    //customers
    public List<Customer> getCustomers() {
        return new ArrayList<>(this.customers);
    }

    public void setCustomers(List<Customer> _customers) {
        this.customers.clear();
        this.customers.addAll(_customers);
    }

    //yearbonus
    public double getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(double _yearBonus) {
        this.yearBonus = _yearBonus;
    }

    //main
    public Account createAccount(Customer customer, Account_Type type, double amount, Date _date) {
        Account a;
        switch (type) {
            case DEBET:
                a = new Debet(amount, customer.getID(), customer.isSuspect());
                break;
            case CREDIT:
                a = new Credit(MINUS_LIMIT, MINUS_TAX, amount, customer.getID(), customer.isSuspect());
                break;
            case DEPOSIT:
                a = new Deposit(_date, amount, customer.getID(), customer.isSuspect());
                break;
            default:
                throw new RuntimeException();
        }
        accounts.add(a);
        return a;
    }

    public void transit(Account from, Account to, int amount) {
        try {
            from.transit(to, amount);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        this.susTransit(from, to, amount);
    }

    public void topUp(Account to, double amount) {
        to.topUp(amount);
        this.susTransit(null, to, amount);
    }

    public void debit(Account from, double amount) {
        try {
            from.debit(amount);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return;
        }
        this.susTransit(from, null, amount);
    }
}
