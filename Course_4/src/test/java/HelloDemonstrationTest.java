import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atLeastOnce;

import static org.mockito.BDDMockito.*;

import java.awt.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import java.net.URL;
import java.net.URLConnection;

public class HelloDemonstrationTest {

    HelloDemonstration mHelloLink;

    //Annotaion demonstraion
    @Test
    public void SimpleAssertion()
    {
        assert( true );
    }
    
    @Test
    public void FailingTest()
    {
        fail(" Failing for a reason ");
    }

    @Ignore("This test is ignored")    
    public void SkippedTest(){
        //not executed
    }

    @Test
    public void SimpleAssertExpression()
    {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void InstantiateClassInTest()
    {
        HelloDemonstration vHelloDemo = new HelloDemonstration();
        assertEquals(2, vHelloDemo.AddNumbers(1, 1));
    }

    @Test
    public void MockListSimple()
    {
        List vMockedList = mock(List.class);
        vMockedList.add("one");
        vMockedList.clear();

        // Mockito can ensure whether a mock method is being called
        // with reequired arguments or not. It is done using the verify() method.
        // Take a look at the following code snippet.

        verify(vMockedList).add("one");
        verify(vMockedList).clear();
    }

    @Test
    public void MockLinkedListTest()
    {    
        LinkedList vMockedList = mock(LinkedList.class);
        
        when(vMockedList.get(0)).thenReturn("first");
        when(vMockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(vMockedList.get(0));
        System.out.println(vMockedList.get(1));
        System.out.println(vMockedList.get(999));
    
        verify(vMockedList).get(0);
    }

    @Test
    public void MockAdditionFunction()
    {
        HelloDemonstration vMockedDemo = mock( HelloDemonstration.class );
        when( vMockedDemo.AddNumbers( 0, 0 )).thenReturn( 2 );
        assertEquals(2, vMockedDemo.AddNumbers( 0, 0 ));
    }

    @Test
    public void MockVerifyExactNumberOfInvocations()
    {
        LinkedList vMockedList = mock(LinkedList.class);
        vMockedList.add("once");

        vMockedList.add("twice");
        vMockedList.add("twice");
       
        vMockedList.add("three times");
        vMockedList.add("three times");
        vMockedList.add("three times");
               
        verify(vMockedList).add("once");
        verify(vMockedList, times(1)).add("once");
               
        verify(vMockedList, times(2)).add("twice");
        verify(vMockedList, times(3)).add("three times");
              
        verify(vMockedList, never()).add("never happened");
       
        verify(vMockedList, atLeastOnce()).add("three times");
        verify(vMockedList, atLeast(2)).add("three times");
        verify(vMockedList, atMost(5)).add("three times");
    }

    @Test
    public void MockStubbingVoidMethodWithException()
    {
        LinkedList vMockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(vMockedList).clear();        
        vMockedList.clear();
    }

    @Test
    public void MockCallRealMethod()
    {
        LinkedList vMockedList = mock(LinkedList.class);
        doCallRealMethod().when( vMockedList ).clear();
        vMockedList.clear();
    }

    @Test //calls original function by default
    public void SpyOnSomething()
    {
        LinkedList vMockedList =  new LinkedList();
        LinkedList vSpy = spy(vMockedList);
        when(vSpy.size()).thenReturn(100); //vSpy

        vSpy.add("one");
        vSpy.add("two");
     
        assertEquals( 100, vSpy.size() );
    }

    @Test
    public void MoreOnSpying()
    {
        LinkedList vMockedList =  new LinkedList();
        LinkedList vSpy = spy(vMockedList);
     
        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when( vSpy.get(0) ).thenReturn("foo");
     
        //You have to use doReturn() for stubbing
        doReturn("foo").when( vSpy ).get(0);
    }

    @Test
    public void DefaultReturnValuesUnstubbed()
    {        
        HelloDemonstration mock = mock(HelloDemonstration.class, Mockito.RETURNS_SMART_NULLS);
        //Answer can be used to define the return values of unstubbed invocations. 
        //HelloDemonstration mockTwo = mock(HelloDemonstration.class, new YourOwnAnswer());
    }

    @Test
    public void ResettingMocks()
    {
        HelloDemonstration vDemo = mock( HelloDemonstration.class );
        when( vDemo.AddNumbers(0, 0) ).thenReturn(2);
        vDemo.AddNumbers(0, 0);
        
        reset(vDemo);
    }

    @Test
    public void LastCourseExampleWorking() throws MalformedURLException, IOException
    {
        NetworkConnection vConnection = mock( NetworkConnection.class );
        when(vConnection.GetHttpRequest() ).thenReturn(" ");
        assertEquals(" ", vConnection.GetHttpRequest() );
    }

    @Test
    public void LastCourseExample() throws MalformedURLException, IOException
    {
        NetworkConnection vConnection = new NetworkConnection();
        URL vUrl = mock(URL.class);
        URLConnection vURLConnection = mock( URLConnection.class );

        
        assertEquals(" ", vConnection.GetHttpRequestParameterized( vUrl ) );
    }

    //Behavoiur driven development, only for cherry picking
    @Test
    public void Behaviour()
    {
        // HelloDemonstration vDemo = mock( HelloDemonstration.class );
        // given( vDemo.AddNumbers(1, 1)).willReturn(4);
        // then( vDemo ).should( times(2) ).AddNumbers(1, 1);
    }

}
