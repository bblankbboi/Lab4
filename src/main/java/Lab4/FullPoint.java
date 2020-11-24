package Lab4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullPoint extends RestorePoint{

    public FullPoint( int _count, List<File> _files ){

        this.ID = _count;
        this.type = Type.FULL;
        this.files = new ArrayList<>( _files );
        dateOfCreate = new Date();

    }

}
