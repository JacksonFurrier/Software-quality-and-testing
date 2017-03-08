package ucoach.data.ws;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Authorization.class)
public class AuthorizationTest {

    @Test
    public void validateRequest() throws Exception {
        PowerMockito.mockStatic(System.class);
        Mockito.when(System.getenv("INTERNAL_DATA_AUTH_KEY")).thenReturn("default_key");
        assertEquals("default_key", System.getenv("INTERNAL_DATA_AUTH_KEY"));
    }

}