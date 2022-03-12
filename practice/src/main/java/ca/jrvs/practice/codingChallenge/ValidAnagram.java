package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * https://www.notion.so/jarvisdev/Valid-Anagram-fb3f9a17bb31485f9601e5a54cc57268
 */
public class ValidAnagram {

    /**
     * Solution 1: Sort and compare
     * Big-O: O(2n)
     * Justification: creates,sorts and compares two arrays
     */
    public boolean isAnagramSort(String s, String t){
        char[] sortedS = s.toCharArray();
        Arrays.sort(sortedS);
        char[] sortedT = t.toCharArray();
        Arrays.sort(sortedT);
        return Arrays.equals(sortedS,sortedT);
    }

    /**
     * Solution 2: Stack and compare
     * Big-O: O(n)
     * Justification: Stores string into hashmap, then compare both
     */
    public boolean isAnagramStack(String s, String t){
        HashMap<Character,Integer> sStack = new HashMap<>();
        HashMap<Character,Integer> tStack = new HashMap<>();
        for(char letter : s.toCharArray()){
            if(sStack.containsKey(letter)){
                sStack.put(letter, (sStack.get(letter)+1) );
            } else {
                sStack.put(letter,1);
            }
        };
        for(char letter : t.toCharArray()){
            if(tStack.containsKey(letter)){
                tStack.put(letter, (tStack.get(letter)+1) );
            } else {
                tStack.put(letter,1);
            }
        };
        return tStack.equals(sStack);
    }
}
