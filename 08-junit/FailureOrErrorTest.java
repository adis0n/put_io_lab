package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {
    @Test
    public void test1 (){
        assertEquals(1,2);
    }

    @Test
    public void test2 (){
            throw new NullPointerException();
    }

    @Test
    public void test3(){
        try {
            assertEquals(1,2);
        } catch (Throwable x){
            x.printStackTrace();
        }

    }

}