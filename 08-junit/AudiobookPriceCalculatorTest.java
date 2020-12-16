package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest extends AudiobookPriceCalculator{
    @Test
    public void test(){
        Customer customer;
        customer = new Customer("Adrian", Customer.LoyaltyLevel.STANDARD,true);
        Audiobook audiobook=new Audiobook("Audio",10);
        AudiobookPriceCalculator calc=new AudiobookPriceCalculator();
        assertEquals(0,calc.calculate(customer,audiobook));
    }

    @Test
    public void test1(){
        Customer customer;
        customer = new Customer("Adrian", Customer.LoyaltyLevel.STANDARD,false);
        Audiobook audiobook=new Audiobook("Audio",10);
        AudiobookPriceCalculator calc=new AudiobookPriceCalculator();
        assertEquals(10,calc.calculate(customer,audiobook));
    }

    @Test
    public void test2(){
        Customer customer;
        customer = new Customer("Adrian", Customer.LoyaltyLevel.SILVER,false);
        Audiobook audiobook=new Audiobook("Audio",10);
        AudiobookPriceCalculator calc=new AudiobookPriceCalculator();
        assertEquals(9,calc.calculate(customer,audiobook));
    }

    @Test
    public void test3(){
        Customer customer;
        customer = new Customer("Adrian", Customer.LoyaltyLevel.GOLD,false);
        Audiobook audiobook=new Audiobook("Audio",10);
        AudiobookPriceCalculator calc=new AudiobookPriceCalculator();
        assertEquals(8,calc.calculate(customer,audiobook));
    }

}