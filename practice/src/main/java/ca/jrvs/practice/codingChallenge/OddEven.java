package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-13d5f1600f064d11be0c4660157e52c1
 */
public class OddEven {

    /**
     * Using modulo operation
     * Big-O: O(1)
     * Justification: it's an arithmetic operation
     */
    public String oddEvenMod(int i){
        return i % 2 == 0 ? "even" : "odd";
    }

    /**
     * With bit operation
     * Big-O: O(1)
     * Justification: Comparison of rightmost bit
     */
    public String oddEvenBit(int i){
        return (i & 1) == 0 ? "even" : "odd";
    }

}
