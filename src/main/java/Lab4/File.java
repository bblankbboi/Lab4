package Lab4;

import java.util.Date;
import java.util.UUID;

public class File {
    private final Date date;
    private final String name;
    private final int size;
    private final String ID;

    public File( String _name, int _size, Date _date ){
        date = _date;
        name = _name;
        size = _size;
        ID = UUID.randomUUID().toString();
    }

    public String getName(){ return name;}
    public String getID() {
        return ID;
    }
    public int getSize(){ return size; }
}
