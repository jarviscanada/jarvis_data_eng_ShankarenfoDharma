package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestQueueWithStack {
    @Test
    public void testStacks(){
        QueueWithStack.queueHeavyPush qHPush = new QueueWithStack().qHPush;
        assertEquals(true,qHPush.empty());
        qHPush.push(1);
        assertEquals(1,qHPush.peek());
        qHPush.push(2);
        qHPush.push(3);
        qHPush.push(4);
        qHPush.push(5);
        assertEquals(1,qHPush.peek());
        qHPush.pop();
        assertEquals(2,qHPush.pop());
        assertEquals(false,qHPush.empty());
        assertEquals(3,qHPush.peek());

        QueueWithStack.queueHeavyPop qHPop = new QueueWithStack().qHPop;
        assertEquals(true,qHPop.empty());
        qHPop.push(1);
        assertEquals(1,qHPop.peek());
        qHPop.push(2);
        qHPop.push(3);
        qHPop.push(4);
        qHPop.push(5);
        assertEquals(1,qHPop.peek());
        qHPop.pop();
        assertEquals(2,qHPop.pop());
        assertEquals(false,qHPop.empty());
        assertEquals(3,qHPop.peek());
    }
}
