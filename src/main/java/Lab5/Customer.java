package Lab5;

import java.util.UUID;

public class Customer {
    private String name;
    private String surname;
    private String address = null;
    private Integer passportID = null;
    public String ID;

    public Customer(String _name, String _surname) {
        this.name = _name;
        this.surname = _surname;
        this.ID = UUID.randomUUID().toString();
    }

    //stuff
    public boolean isSuspect() {
        if (this.address == null && this.passportID == null) {
            return true;
        }
        return false;
    }

    //getters setters
    //address
    public void setAddress(String _address) {
        this.address = _address;
    }

    //passport
    public void setPassportID(int _passportID) {
        this.passportID = _passportID;
    }

    //name and surname
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

}
