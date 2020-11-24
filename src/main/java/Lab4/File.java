package Lab4;

import java.util.Date;
import java.util.List;

public class File extends BackupObject{

    private String name;
    private String information = "information";

    public String getName(){ return new String( name ); }
    public Date getDateOfCreate(){ return dateOfCreate; }
    public String getInformation(){ return new String( information ); }

    public File( String _name, int _size){

        this.name = _name;
        this.size = _size;
        this.dateOfCreate = new Date();

        System.out.format("File \"%s\" was created on %s\n"
                , name, dateOfCreate.toString());

    }

    public boolean isFileExistIn( List<File> files ){

        for( File file : files ){

//            String name = this.getName();
//            int size = this.size();
//            String information = this.getInformation();
//            Date creatingDate = this.getDateOfCreate();
//
//            String _name = file.getName();
//            int _size = file.size();
//            String _information = file.getInformation();
//            Date _creatingDate = file.getDateOfCreate();

            if( this.equals( file ) ){ return true; }

//            if( name.equals( _name )
//                    &&  size == _size
//                    && creatingDate.equals( _creatingDate )
//                    && information.equals( _information ) ){ return true; }

        }

        return false;

    }

}
