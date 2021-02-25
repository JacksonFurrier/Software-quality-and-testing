package course_3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class HelloLinkAdvancedTests{

    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void IndexOutOfBoundsTest()
    {
        HelloLink vHelloLink = new HelloLink(5);
        vHelloLink.mVector.get( 6 );
    }

    @Ignore("Test is ignored as a demonstration")
    @Test
    public void testSame() {
        assertEquals(1, 1);
    }

    @Test(timeout=1000)
    public void testWithTimeout() {
        //
    }

}