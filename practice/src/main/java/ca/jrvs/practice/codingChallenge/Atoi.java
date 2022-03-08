package ca.jrvs.practice.codingChallenge;
/**
 * Ticket: https://www.notion.so/jarvisdev/Two-Sum-067fee9f084e437caaf80d01c7078695
 */
public class Atoi {
    /**
     * with java parsing
     * Big-O: O(1)
     * Justification: Use of integer.parseInt function
     * @param s is the string fed
     * @return int signed integer equivalent
     */
    public int parseAtoi(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e){
            if (s.trim().charAt(0) == '-'){
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public int manualAtoi(String s){
        String sTrimd = s.trim();
        int index = 0;
        int result = 0;
        boolean signedNegative = false;
        if (sTrimd.charAt(0) == '-'){
            signedNegative = true;
            index++;
        }
        for(; index < sTrimd.length(); index++){
            result = (result*10) + sTrimd.charAt(index)-'0';
        }
        if(signedNegative){
            //catch maxvalue
            if(result == Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
            return result*(-1);
        }
        //catch maxvalue
        if(result == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        return result;
    }
}
