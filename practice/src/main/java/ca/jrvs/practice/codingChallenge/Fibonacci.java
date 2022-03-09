package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-904a34f24ef9460f806b5d95f9494f14
 */
public class Fibonacci {

    /**
     * Fibo with recursion
     * Big-O: O(2^n)
     * Justification: Each fibonacci calculation branches into 2 fibonaccis until the
     * @param n input number (sequence)
     * @return result in sequence
     */
    public int fibo(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fibo(n-1) + fibo(n - 2);
    }

    /**
     * Fibo with bottom-up/dynamic
     * Big-O: O(n)
     * Justification: Essentially building an array as we're going through it
     * @param n input number (sequence)
     * @return result in sequence
     */
    public int fiboDynamic(int n){
        if(n == 1 || n == 2) return 1;
        int fiboSequence[] = new int[n+1];
        fiboSequence[1] = 1;
        fiboSequence[2] = 1;
        for(int index =3; index <= n; index++){
            fiboSequence[index] = fiboSequence[index-1] + fiboSequence[index-2];
        }
        return fiboSequence[n];

    }
}
