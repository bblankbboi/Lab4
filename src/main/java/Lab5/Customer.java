package Lab5;

import Lab5.Exceptions.MyException;

import java.util.UUID;

public class Customer {
    private String name;
    private String surname;
    private String address = null;
    private Integer passportID = null;
    private String ID;

    public Customer( String _name, String _surname ){
        this.name = _name;
        this.surname = _surname;
        this.ID = UUID.randomUUID().toString();
    }
//    public Customer( String _name, String _surname, String _address, int _passportID ){
//        this.name = _name;
//        this.surname = _surname;
//        this.address = _address;
//        this.passportID = _passportID;
//        this.ID = UUID.randomUUID().toString();
//    }

    //getters setters
        //address
    public void setAddress( String _address){
        this.address = _address;
    }
    public String getAddress() throws MyException {
        if( this.address == null ){ throw new MyException("Doesn't have a address");}
        return this.address;
    }
        //passport
    public void setPassportID( int _passportID ){
        this.passportID = _passportID;
    }
    public int getPassportID() throws MyException{
        if( this.passportID == null ){ throw new MyException("Doesn't have a passport"); }
        return this.passportID;
    }
        //name and surname
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
        //ID
    public String getID(){
        return this.ID;
    }
    public void setID( String _ID){ this.ID = _ID; }

}
