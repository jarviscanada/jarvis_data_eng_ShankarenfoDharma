package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-2091268ea9274d1a9dc0ff35341e8e8d
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> parenthStck = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char character = s.charAt(i);
            if(character == '{' || character == '[' || character == '('){
                parenthStck.push(character);
            } else if (character == '}'){
                if(parenthStck.empty()){
                    return false;
                }
                if(parenthStck.pop() != '{'){
                    return false;
                }
            } else if (character == ']'){
                if(parenthStck.empty()){
                    return false;
                }
                if(parenthStck.pop() != '['){
                    return false;
                }
            } else if (character == ')'){
                if(parenthStck.empty()){
                    return false;
                }
                if(parenthStck.pop() != '('){
                    return false;
                }
            }
        }
        return parenthStck.empty();
    }
}
