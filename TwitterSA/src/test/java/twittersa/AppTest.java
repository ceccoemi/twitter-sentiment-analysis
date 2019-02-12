package twittersa;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import twittersa.App;


public class AppTest 
{

    @Test
    public void shouldAnswerWithTrue()
    {
        App a = new App();
        assertEquals(a.sayHello(), "Hello World!");
    }

}
