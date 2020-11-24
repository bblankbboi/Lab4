package Lab4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeltaPoint extends RestorePoint{

    private final List<File> added;
    private final List<File> deleted;

    public List<File> getAdded(){ return added; }
    public List<File> getDeleted(){ return deleted; }

    public DeltaPoint( int _count, List<File> _files, RestorePoint lastFullPoint ){

        ID = _count;
        type = Type.INCREMENT;
        dateOfCreate = new Date();

        this.added = new ArrayList<>( lastFullPoint.getAddedFiles( _files ) );
        this.deleted = new ArrayList<>( lastFullPoint.getDeletedFiles( _files ) );

    }

}
