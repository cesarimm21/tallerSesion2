package basicJunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicJunit {

    @BeforeEach
    public void setup(){
        System.out.println("Before each");
    }

    @AfterEach
    public void cleanup(){
        System.out.println("After each");
    }


    @Test
    public void testSomething(){

        System.out.println("test");
    }

    @Test
    public void testSomething1(){
        System.out.println("test2");

    }

}
