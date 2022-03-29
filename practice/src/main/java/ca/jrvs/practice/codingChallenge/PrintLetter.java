package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-682fec2c125f4a3fa02e4fcafb2d3de0
 */
public class PrintLetter {
    public String printLetterNumber(String s){
        String ans = "";
        for (char c:s.toCharArray()) {
            int charASCII = c;
            int index = 0;
            //uppercase range
            if(c > 64 && c < 91){
                index = c-64+26;
            }
            //lowercase range
            else if (c > 96 && c < 123){
                index = c-96;
            }

            //add into answer string
            ans+=c;
            ans+=index;
        }
        return ans;
    }
}
