import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HelloLinkParameterizedTests{
    
    public HelloLinkParameterizedTests(int aInput, int aExpected) {
        mInput = aInput;
        mExpected = aExpected;
    }

    @Parameters(name= "{index}: fib[{0}]={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 },
                { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
    }

    private int mInput;
    private int mExpected;

    @Test //Task 1st
    public void test() {
        assertEquals( mExpected, HelloLink.compute( mInput ));
    }

}