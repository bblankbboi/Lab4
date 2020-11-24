import Lab4.Backup;
import Lab4.File;
import Lab4.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTest {
    @Test
    void Test1() {

        File kek = new File("Kek", 10);
        Backup.addFile( kek );
        File lul = new File("lul", 12);
        Backup.addFile( lul );

        String expected =  Backup.listOfFiles();

        Backup.createRestorePoint( Type.FULL);

        File shit = new File( "SHIT", 100);
        Backup.addFile( shit );

        Backup.backupToPoint( 1 );
        String actual =  Backup.listOfFiles();

        Assertions.assertEquals( expected, actual );

    }
    @Test
    void Test2() {

        File kek = new File("Kek", 10);
        Backup.addFile( kek );
        File lul = new File("lul", 12);
        Backup.addFile( lul );

        Backup.createRestorePoint( Type.FULL);

        File shit = new File( "SHIT", 100);
        Backup.addFile( shit );

        String expected =  Backup.listOfFiles();

        Backup.createRestorePoint( Type.INCREMENT);

        Backup.backupToPoint( 1 );

        Backup.backupToPoint( 2 );

        String actual =  Backup.listOfFiles();

        Assertions.assertEquals( expected, actual );

    }
}
