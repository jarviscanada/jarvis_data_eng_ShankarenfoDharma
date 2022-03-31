package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-c24a1c3d35ef4182a544ff800401b356
 */
public class SwapTwoNumbers {
    //Solution 1: Bitwise
    public int[] swapNumbersBits(int[] base){
        base[0] = base[0]^base[1];
        base[1] = base[0]^base[1];
        base[0] = base[0]^base[1];
        return base;
    }

    //Solution 2: Arithmetic
    public int[] swapNumbersArithmetic(int[] base){
        base[0] = base[0]+base[1];
        base[1] = base[0]-base[1];
        base[0] = base[0]-base[1];
        return base;
    }
}
