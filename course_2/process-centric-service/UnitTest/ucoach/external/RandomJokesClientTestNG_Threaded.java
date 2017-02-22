package ucoach.external;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by szucs on 2/2/2017.
 */
public class RandomJokesClientTestNG_Threaded {

    RandomJokesClient m_RandomJokes;

    @BeforeMethod
    public void setUp() {
        // code that will be invoked when this test is instantiated
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
    }

    @Test
    public void testGetJoke() throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method One. Thread id is: " + id);
    }

    @Test
    public void testGetJoke_Overload() throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
    }

    @AfterMethod
    public void tearDown() {
        // code that will be invoked when this test is finished
        long id = Thread.currentThread().getId();
        System.out.println("After test-method. Thread id is: " + id);
    }

}