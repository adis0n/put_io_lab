package put.io.testing.junit;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest extends Calculator{

    private Calculator calculator;

    @BeforeEach
    private void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void addtest(){
        Assertions.assertEquals(4,calculator.add(2,2));
    }

    @Test
    public void multiplytest(){
        Assertions.assertEquals(6,calculator.multiply(2,3));
    }

    @Test
    public void positivetest(){
         assertThrows(IllegalArgumentException.class,()->{calculator.addPositiveNumbers(-1,2);});
    }




}