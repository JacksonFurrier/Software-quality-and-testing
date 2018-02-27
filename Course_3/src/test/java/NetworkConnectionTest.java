import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetworkConnectionTest{

    NetworkConnection mNetworkConnection;

    @Before
    public void BlackMagic() throws UnsupportedEncodingException
    {
        mNetworkConnection = new NetworkConnection();
    }

    @Test
    public void HttpRequestReturnsNotNull() throws MalformedURLException, IOException
    {                
        assertNotEquals( " " , mNetworkConnection.GetHttpRequest() );
    }

    
    @Test
    public void HttpRequestReturnsNull() throws MalformedURLException, IOException
    {
        mNetworkConnection = mock( NetworkConnection.class );
        when( mNetworkConnection.GetHttpRequest()).thenReturn(" ");
        assertEquals(" ", mNetworkConnection.GetHttpRequest() );        
    }

    @Test //2nd task    
    public void HttpRequestReturnsNullWithFakeUrl()
    {
        // implement me
    }

    @Test //3rd task
    public void HttpPostTestWithFakeObject()
    {
        // implement me
    }

    
}