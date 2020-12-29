package Lab5;

import Lab5.BankAccounts.Account;
import Lab5.BankAccounts.Account_Type;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager {
    //fields
    private List<Customer> customers = new ArrayList<>();
    private List<Bank> banks = new ArrayList<>();

    public Manager(String _today) {
        this.setDate(_today);
    }

    //getters setters
        //date
    public static Date getDate() {
        return TimeMachine.today;
    }

    public void setDate(Date _today) {
        TimeMachine.today = _today;
    }

    public void setDate(String _today) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            TimeMachine.today = format.parse(_today);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //main abilities
    public void nextDay() {
        for (Bank b : banks) {
            for (Account a : b.getAccounts()) {
                a.nextDay(b.getYearBonus());
            }
        }
    }

    public void changeTime(String _date) {
        this.setDate(_date);
    }

    public Customer createCustomer(String _name, String _surname) {
        Customer myCustomer = new Customer(_name, _surname);
        this.customers.add(myCustomer);
        return myCustomer;
    }

    public void setCustomerAddress(String _name, String _surname, String _address) {
        for (int i = customers.size() - 1; i >= 0; i--) {
            Customer c = customers.get(i);
            if (c.getName().equals(_name) && c.getSurname().equals(_surname)) {
                c.setAddress(_address);
            }
        }
    }

    public void setCustomerPassportID(String _name, String _surname, int _passportID) {
        for (int i = customers.size() - 1; i >= 0; i--) {
            Customer c = customers.get(i);
            if (c.getName().equals(_name) && c.getSurname().equals(_surname)) {
                c.setPassportID(_passportID);
            }
        }
    }

    public Bank createBank(String name, int MINUS_LIMIT, int MINUS_TAX) {
        Bank temp = new Bank(name, MINUS_LIMIT, MINUS_TAX);
        banks.add(temp);
        return temp;
    }

    public Account createAccount(Customer customer, Bank bank, Account_Type type, double amount, String date) {
        return bank.createAccount(customer, type, amount, date);
    }

}
