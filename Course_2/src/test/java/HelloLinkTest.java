import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

public class HelloLinkTest {

    HelloLink mHelloLink;

    @Before
    public void Setup()
    {
       mHelloLink = new HelloLink( 0 ); 
    }

    @Test
    public void multiplicationOfZeroIntegereShouldReturnZero() {
        HelloLink vHelloLink = new HelloLink(); //can be heavyweight!

        assertEquals(  "10 x 0 must be 0", 0, vHelloLink.multiply(10, 0) );
        assertEquals(  "0 x 10 must be 0", 0, vHelloLink.multiply(0, 10) );
        assertEquals( "0 x 0 must be 0", 0, vHelloLink.multiply(0, 0) );
    }

    @Test
    public void multiplicationOfZeroIntegereShouldReturnZeroWithMember() {
        assertEquals(  "10 x 0 must be 0", 0, mHelloLink.multiply(10, 0) );
        assertEquals(  "0 x 10 must be 0", 0, mHelloLink.multiply(0, 10) );
        assertEquals( "0 x 0 must be 0", 0, mHelloLink.multiply(0, 0) );
    }

    @Test
    public void beforeAffectsTest(){
        assertFalse( true );
    }

    @After
    public void TearDown()
    {
        assert( true );
    }
}