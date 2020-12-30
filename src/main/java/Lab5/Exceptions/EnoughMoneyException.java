package Lab5.Exceptions;

public class EnoughMoneyException extends Exception{
    public EnoughMoneyException() {
        super("Too big amount for this account");
    }
}
