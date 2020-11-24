package Lab2;

import java.util.List;

public abstract class ManagerObject {
    protected int code;
    protected String name;

    public String getName(){ return name; }// +
    public int getCode(){ return code; }// +
    protected int generateCode( List<Integer> codes ){
        int temp = (int) ( Math.random() * Integer.MAX_VALUE );
        for( int x : codes ){
            if( x == temp ){ temp = generateCode( codes ); }
        }
        return temp;
    }// +

}
