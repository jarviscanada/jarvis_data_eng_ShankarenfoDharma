package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ticket: https://www.notion.so/jarvisdev/Missing-Number-831ed16c07a543d897f1eac8906d8132
 */
public class MissingNumber {
    //solution 1: sum all
    public int missingNumberSum(int[] input){
        int sum = ( input.length * (input.length+1) )/2;
        for(int num : input)
            sum-=num;
        return sum;
    }

    //solution 2: use set
    public int missingNumberSet(int[] input){
        Set setStream = Arrays.stream(input).boxed().collect(Collectors.toSet());
        for(int i =0; i<=input.length;i++){
            if(setStream.add(i)){
                return i;
            }
        }
        return 0;
    }

    //solution 3: Gauss' Formula
    public int missingNumberGauss(int[] input){
        int gaussSum = ( input.length * (input.length+1) )/2;
        int arraySum = Arrays.stream(input).sum();
        return gaussSum-arraySum;
    }
}
