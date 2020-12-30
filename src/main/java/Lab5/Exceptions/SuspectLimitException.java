package Lab5.Exceptions;

public class SuspectLimitException extends Exception{
    public SuspectLimitException() {
        super("You can't transit this amount, because your account is suspect.");
    }
}
