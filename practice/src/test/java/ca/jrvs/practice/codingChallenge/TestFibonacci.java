package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestFibonacci {
    @Test
    public void fibonacciTest(){
        Fibonacci fib = new Fibonacci();
        assertEquals(fib.fibo(2),1);
        assertEquals(fib.fibo(3),2);
        assertEquals(fib.fibo(4),3);
        assertEquals(fib.fibo(30),832040);
        assertEquals(fib.fibo(10),55);

        assertEquals(fib.fiboDynamic(2),1);
        assertEquals(fib.fiboDynamic(3),2);
        assertEquals(fib.fiboDynamic(4),3);
        assertEquals(fib.fiboDynamic(30),832040);
        assertEquals(fib.fiboDynamic(10),55);
    }
}
