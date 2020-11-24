package Lab4;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public abstract class Backup {

    private static List<File> filesNow = new ArrayList<>();
    private static final List<RestorePoint> points = new ArrayList<>();
    private static int countOfPoints = 0;

    static{
        System.out.println("Backup system was created :)");
    }

    public int getCountOfPoints(){ return countOfPoints; }
    public static int getIndex( RestorePoint p ){

        for( int i = 0; i < points.size(); i++){

            RestorePoint template = points.get( i );
            if( p.equals( template ) ){ return i; }

        }

        throw new NullPointerException("Can't find Restore Point");

    }

    private static RestorePoint getLastFullPoint( int INDEX ){

        for(int i = INDEX; i >= 0; i--){

            RestorePoint template = points.get( INDEX );

            if( template.getType().equals(Type.FULL) ){ return template; }

        }

        throw new NullPointerException("Last Full Point doesn't exist");

    }

    private static int findIndexOfLastFullBackupPoint( int ID ){

        int indexOfEnd = Backup.iDtoIndex( ID );

        for( int i = 0; i < indexOfEnd; i++ ){
            RestorePoint p = points.get( i );
            if( p.getClass() == FullPoint.class ){ return i; }
        }

        throw new NullPointerException("Last Full Point doesn't exist");
    }

    public static void createRestorePoint( Type type ){

        countOfPoints++;

        if( type.equals(Type.FULL) ) {
            FullPoint restorePoint = new FullPoint( countOfPoints, filesNow );
            points.add( restorePoint );
        } else if( type.equals(Type.INCREMENT) ){
            FullPoint lastFullPoint = (FullPoint) Backup.getLastFullPoint( points.size() - 1 );
            DeltaPoint restorePoint = new DeltaPoint( countOfPoints, filesNow, lastFullPoint );
            points.add( restorePoint );
        }

        System.out.println( "\nBackup point was created\n" );

    }

    public static void addFile( File _file){ filesNow.add( _file ); }

    public static String listOfFiles(){

        String line = "\nФайлы в системе:\n";
        for( File file : filesNow ){
            line += format("    - \"%s\" (Date of create: %s)\n"
                    , file.getName(), file.getDateOfCreate().toString());
        }

        line += '\n';

        return line;
    }

    public static String listOfPoints(){

        String line = "\nТочки восстановления:\n";
        for( RestorePoint point : points ){
            line += format("    - [%d] (Date of create: %s, type: %s)\n"
                    , point.getID(), point.getDateOfCreate().toString(), point.getType());
        }

        line += '\n';

        return line;
    }

    private static RestorePoint findThePoint( int _ID ){

        int INDEX = Backup.iDtoIndex( _ID );

        return points.get( INDEX );

    }

    private static int iDtoIndex(int ID ){

        for(int i = 0; i < points.size(); i++){
            RestorePoint p = points.get( i );
            if( p.getID() == ID ){ return i; }
        }

        throw new NullPointerException("Can't find Restore Point with this ID");
    }

    public static void printFiles( List<File> files){

        for( File f : files ){
            System.out.println(f.getName());
        }

    }

    private static List<File> deltaChange(DeltaPoint p, List<File> filesNow ){

        List<File> add = p.getAdded();
        List<File> del = p.getDeleted();

        System.out.println("Файлы на добавление");
        Backup.printFiles( add );
        System.out.println("Файлы на удаление");
        Backup.printFiles( del );

        filesNow.addAll( add );

        List<File> t = new ArrayList<>();

        for( File f : del ){

            for(int i = 0; i < filesNow.size(); i++ ){
                File file = filesNow.get(i);
                if( !f.equals( file ) ){ t.add( file ); }
            }

        }
        if( t.size() == 0 ){ throw new NullPointerException("Delta change return empty arraylist"); }
        return t;

    }

    private static void backupToFull( RestorePoint backupTo ){
        filesNow = backupTo.getRestorePointFiles();
    }

    private static void backupToDelta( RestorePoint backupTo ){

        int _ID = backupTo.getID();

        int start = Backup.findIndexOfLastFullBackupPoint( _ID );// index Of Last Full Backup Point

        filesNow = points.get( start ).getRestorePointFiles();

        int end = Backup.iDtoIndex( _ID );// index Of Last Delta Backup Point

        for( int i = start + 1; i <= end; i++ ){// changes of deltapoint

            DeltaPoint p = (DeltaPoint) points.get(i);
            filesNow = new ArrayList<>( Backup.deltaChange( p, filesNow ) );

        }

    }

    public static void backupToPoint( int _ID ){

        RestorePoint template = Backup.findThePoint( _ID );

        if( template.getType() == Type.FULL) { Backup.backupToFull( template ); }

        else if( template.getType() == Type.INCREMENT ){ Backup.backupToDelta( template ); }

        System.out.println( "\nBackup was finished\n" );

    }

}
