package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStackWithQueue {
    @Test
    public void testStacks(){
        StackWithQueue.StackWith2Queue sW2Q = new StackWithQueue().sW2Q;
        assertEquals(true,sW2Q.empty());
        sW2Q.push(1);
        assertEquals(1,sW2Q.top());
        sW2Q.push(2);
        sW2Q.push(3);
        sW2Q.push(4);
        sW2Q.push(5);
        assertEquals(5,sW2Q.top());
        sW2Q.pop();
        assertEquals(4,sW2Q.pop());
        assertEquals(false,sW2Q.empty());
        assertEquals(3,sW2Q.top());
        
        StackWithQueue.StackWith1Queue sW1Q = new StackWithQueue().sW1Q;
        assertEquals(true,sW1Q.empty());
        sW1Q.push(1);
        assertEquals(1,sW1Q.top());
        sW1Q.push(2);
        sW1Q.push(3);
        sW1Q.push(4);
        sW1Q.push(5);
        assertEquals(5,sW1Q.top());
        sW1Q.pop();
        assertEquals(4,sW1Q.pop());
        assertEquals(false,sW1Q.empty());
        assertEquals(3,sW1Q.top());
    }
}
