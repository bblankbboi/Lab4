package Lab4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static void print(){
        List<String> commands = new ArrayList<>();
        commands.add( "Exit" );
        commands.add( "Change time" );
        commands.add( "Files in the system" );
        commands.add( "Create file" );
        commands.add( "Delete file" );
        commands.add( "Create full point" );
        commands.add( "Create delta point" );
        commands.add( "Do backup" );
        commands.add( "Choose backup points clearing" );
        commands.add( "Get info of point" );

        for( int i = 0; i < commands.size(); i++ ){
            System.out.format( "%d.%s.\n", i, commands.get( i ) );
        }

        System.out.println();
    }
}
