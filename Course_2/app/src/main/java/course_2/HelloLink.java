import java.util.Vector;

public class HelloLink{

    public Vector mVector;

    HelloLink(){
    }

    HelloLink(int aNumber){
        mNumber = aNumber;
        mVector = new Vector( aNumber );
    }

    HelloLink(boolean aWait) throws InterruptedException{ //this can happen!
        if( aWait )
        {
            wait( 3000 );
        }
    }

    public static void main(String[] aArgs){
        callNavi(" This is a Java app built with VSCode! ");
    }

    public static void callNavi(String aMessage) {
        System.out.println( "LISTEN! " + aMessage );
    }

    public int multiply(int aLeft, int aRight)
    {
        return mNumber * aLeft;
    }

    static public int compute( int aLeft )
    {
        return aLeft * 0;
    }
    

    int mNumber;
}