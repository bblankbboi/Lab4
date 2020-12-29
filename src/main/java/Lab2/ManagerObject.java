package Lab2;
import java.util.List;
import java.util.UUID;

public abstract class ManagerObject {
    protected String code;
    protected String name;
    public String getName(){ return name; }// +
    public String getCode(){ return code ; }// +

    protected String generateCode() { return UUID.randomUUID().toString(); }
}