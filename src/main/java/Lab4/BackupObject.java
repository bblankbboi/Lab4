package Lab4;

import java.util.Date;

public abstract class BackupObject {

    protected Date dateOfCreate;
    protected int size;

    public Date getDateOfCreate(){ return dateOfCreate; }

    public int size(){ return size; }

}
