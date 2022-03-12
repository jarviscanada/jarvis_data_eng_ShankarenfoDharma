package ca.jrvs.practice.codingChallenge;

import java.util.Locale;

/**
 * ticket:https://www.notion.so/jarvisdev/Valid-Palindrome-8c27f34f50924634ba1269cb6170d204
 */
public class ValidPalindrome {
    /**
     * Solution 1: two pointers
     * Big-O: O(N)
     * Justification: has to go through the string entirely
     */
    public boolean palindromePointers(String s){
        s = s.toLowerCase(Locale.ROOT).replaceAll("[^A-Za-z0-9]","");
        int leftPointer = 0;
        int rghtPointer = s.length()-1;
        while(leftPointer < rghtPointer){
            if(s.charAt(leftPointer) != s.charAt(rghtPointer))
                return false;
            leftPointer++;
            rghtPointer--;
        }
        return true;
    }

    /**
     * Solution 2: Recursion
     * Big-O: O(N)
     * Justification: has to go through the string entirely
     */
    public boolean palindromeRecursive(String s){
        s = s.toLowerCase(Locale.ROOT).replaceAll("[^A-Za-z0-9]","");
        return recursivePalindromeFunction(s,0,s.length()-1);
    }
    private boolean recursivePalindromeFunction(String s, int left, int right){
        //basecase //odd length       //even length
        if(left == right || right-left == -1){
            return true;
        } else {
            boolean bl = s.charAt(left) == s.charAt(right);
            if(s.charAt(left) == s.charAt(right)){
               return recursivePalindromeFunction(s, left+1,right-1);
            } else {
                return false;
            }
        }
    }

    /**
     * Solution 3: My own
     * Big-O: O(N)
     * Justification: stringbuilder reverse has O(n) complexity
     */
    public boolean palindromePersonal(String s){
        //clean up test
        s = s.toLowerCase(Locale.ROOT).replaceAll("[^A-Za-z0-9]","");
        //emmpty string is palindrome
        if(s.isEmpty())
            return true;
        //reverse s
        String reverseS = new StringBuilder(s).reverse().toString();
        return s.equals(reverseS);
    }
}
