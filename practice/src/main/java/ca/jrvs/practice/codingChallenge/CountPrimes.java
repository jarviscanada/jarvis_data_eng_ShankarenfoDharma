package ca.jrvs.practice.codingChallenge;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CountPrimes {
    //solution 1: check prime for every element
    public int primeCount(int range){
        AtomicInteger primeCounter = new AtomicInteger();
        IntStream.rangeClosed(0,range).forEach(element -> {
            if(isPrime(element)){
                primeCounter.getAndIncrement();
            }
        });
        return primeCounter.get();
    }

    private boolean isPrime(int target){
        if (target < 2)
            return false;
        //divisible by 2/lesser thans?
        for (int i = 2; i < target; i++)
        {
            if (target % i == 0)
                return false;
        }

        return true;
    }
}
