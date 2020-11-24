package Lab4;

public class Main {

    public static void main(String[] args) {

        System.out.println();

        File kek = new File("Kek", 10);
        Backup.addFile( kek );
        File lul = new File("lul", 12);
        Backup.addFile( lul );
        System.out.println( Backup.listOfFiles() );

        Backup.createRestorePoint( Type.FULL);
        System.out.println( Backup.listOfPoints() );

        File shit = new File( "SHIT", 100);
        Backup.addFile( shit );
        System.out.println( Backup.listOfFiles() );

        Backup.createRestorePoint( Type.INCREMENT);
        System.out.println( Backup.listOfPoints() );

        Backup.backupToPoint( 1 );

        System.out.println( Backup.listOfFiles() );

        Backup.backupToPoint( 2 );
        System.out.println( Backup.listOfFiles() );

        File shit2 = new File( "SHIT2", 101);
        Backup.addFile( shit );

        System.out.println( Backup.listOfPoints() );
        Backup.createRestorePoint( Type.INCREMENT);
        System.out.println( Backup.listOfPoints() );

        Backup.backupToPoint( 1 );

        System.out.println( Backup.listOfFiles() );

        Backup.backupToPoint( 3 );

        System.out.println( Backup.listOfFiles() );

    }

}
