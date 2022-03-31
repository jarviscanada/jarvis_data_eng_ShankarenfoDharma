package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-Characters-f4ea5139a29149fabb52d9d6d53c8129
 */
public class DuplicateCharacters {
    public char[] detectDuplicate(String s){
        s = s.toLowerCase(Locale.ROOT);
        Set<Character> uniqueChars = new HashSet<>();
        Set<Character> dupeChars = new HashSet<>();
        for (char c:s.toCharArray()) {
            if( !(c == '\n' || c == ' ') && !uniqueChars.add(c)){
                dupeChars.add(c);
            }
        }
        char[] ans = new char[dupeChars.size()];
        int index = 0;
        for (char e:dupeChars) {
            ans[index] = e;
            index++;
        }
        return ans;
    }
}
