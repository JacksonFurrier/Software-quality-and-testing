import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by szucs on 3/1/2017.
 */
class MyArrayTest {

    @Test
    void sum_total() {
        MyArray array = new MyArray( );
        Integer sum = 0;
        assertEquals(sum, array.Sum());
    }

    @Test
    void sum_partial() {
        MyArray array = new MyArray( );
        Integer sum = 0;
        assertEquals(sum, array.Sum(5));
    }

}