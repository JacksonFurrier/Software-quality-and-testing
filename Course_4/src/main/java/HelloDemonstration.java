import java.util.Vector;

public class HelloDemonstration{

    HelloDemonstration() {
    }

    public static void main(String[] aArgs){
        callNavi(" This is a Java app built with VSCode! ");
    }

    public static void callNavi(String aMessage) {
        System.out.println( "LISTEN! " + aMessage );
    }

    
    int AddNumbers( int aLeft, int aRight )
    {
        return aLeft + aRight;
    }

    int MultiplyNumbers( int aLeft, int aRight )
    {
        return aLeft * aRight;
    }

    public void AddTerminateChar( String aString )
    {
        aString += "\n";
    }
    
}