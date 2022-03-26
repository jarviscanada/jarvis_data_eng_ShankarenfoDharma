package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-056f86fbcae948a5ae933d81c928f2ad
 */
public class FindLargestSmallest {
    //solution 1: single loop
    public int[] findLargestSmallestLoop(int[] base){
        int largest = 0;
        int smallest = base[0];
        for (int num:base) {
            if(num > largest)
                largest = num;
            if(num < smallest)
                smallest = num;
        }
        return new int[] {largest,smallest};
    }

    //solution 2: Stream API
    public int[] findLargestSmallestStream(int[] base){
        int max = Arrays.stream(base).max().getAsInt();
        int min = Arrays.stream(base).min().getAsInt();
        return new int[]{max,min};
    }

    //solution 3: Collections.Max
    public int[] findLargestSmallestAPI(int[] base){
        int max = Collections.max(Arrays.stream(base).boxed().collect(Collectors.toList()));
        int min = Collections.min(Arrays.stream(base).boxed().collect(Collectors.toList()));
        return new int[]{max,min};
    }
}
