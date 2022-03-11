package ca.jrvs.practice.codingChallenge;

import java.util.Locale;
import java.util.stream.Stream;

/**
 * ticket:https://www.notion.so/jarvisdev/Rotate-String-6fe3d40588214ef6b40aa81a9d6be99d
 */
public class RotateString {
    /**
     * Check with contains
     * Big-O: O(n)
     * justification: foreach char in goal, check existence in s
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString(String s, String goal) {
        //if length != match, not rotation
        if(s.length() != goal.length())
            return false;
        String targetString = s+s;
        if(targetString.toLowerCase(Locale.ROOT).contains(goal))
            return true;
        else
            return false;
    }
}
