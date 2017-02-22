package ucoach.external;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by szucs on 2/3/2017.
 */
public class RandomJokesClientTestNG_ThreadPool {

    @Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void testRandomJokes()
    {
        Long id = Thread.currentThread().getId();
        System.out.println("Test method executing on thread with id: " + id);
    }

}