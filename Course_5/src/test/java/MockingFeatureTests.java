import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.withSettings;

class MockingFeatureTest{

    @Test
    public void MockingAbstractClass()
    {
        FooAbstract vAbstract =  spy(FooAbstract.class);
    }

    @Test
    public void MoreAdvancedMockingAbstractClass()
    {
        FooAbstract vAbstract = mock(FooAbstract.class, withSettings()
        .useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }


}