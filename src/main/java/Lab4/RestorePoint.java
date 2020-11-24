package Lab4;

import java.util.ArrayList;
import java.util.List;

public abstract class RestorePoint extends BackupObject{

    protected int ID;
    protected List<File> files;
    protected Type type;

    public RestorePoint(){}

    public int getID(){ return ID; }
    public Type getType(){ return type; }

    public List<File> getRestorePointFiles(){ return new ArrayList<>( files ); }

    protected List<File> getAddedFiles( List<File> filesInSystem ){

        List<File> incrementedFiles = new ArrayList<>();

        for( File file : filesInSystem ){
            if( !file.isFileExistIn( this.files ) ){ incrementedFiles.add( file ); }
        }

        return incrementedFiles;

    }

    protected List<File> getDeletedFiles( List<File> filesNow ){

        List<File> deletedFiles = new ArrayList<>();

        for( File file : files ){
            if( !file.isFileExistIn( filesNow ) ){ deletedFiles.add( file ); }
        }

        return deletedFiles;

    }

}
