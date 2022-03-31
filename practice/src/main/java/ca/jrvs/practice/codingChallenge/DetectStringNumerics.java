package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-89c449b41aa04589b332c2a1540ae3ee
 */
public class DetectStringNumerics {
    //Solution 1: Use ASCII
    public boolean isStringNumericsASCII(String s){
        char[] stringArray = s.toCharArray();
        for (char c : stringArray) {
            int asciiChar = (int) c;
            if(asciiChar < 48 || asciiChar > 57){
                return false;
            }
        }
        return true;
    }

    //Solution 2: Java API
    public boolean isStringNumericsAPI(String s){
        try{
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    //Solution 3: Regex
    public boolean isStringNumericsRegex(String s){
        return s.matches("[0-9]+");
    }
}
