package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPalindrome {
    @Test
    public void testPalindrome(){
        ValidPalindrome vp = new ValidPalindrome();
        assertEquals(true,vp.palindromePersonal("rotor"));
        assertEquals(false,vp.palindromePersonal("motor"));
        assertEquals(true,vp.palindromePersonal("A man, a plan, a canal: Panama"));
        assertEquals(false,vp.palindromePersonal("race a car"));
        assertEquals(true, vp.palindromePersonal(" "));

        assertEquals(true,vp.palindromePointers("rotor"));
        assertEquals(false,vp.palindromePointers("motor"));
        assertEquals(true,vp.palindromePointers("A man, a plan, a canal: Panama"));
        assertEquals(false,vp.palindromePointers("race a car"));
        assertEquals(true, vp.palindromePointers(" "));

        assertEquals(true,vp.palindromeRecursive("rotor"));
        assertEquals(false,vp.palindromeRecursive("motor"));
        assertEquals(true,vp.palindromeRecursive("A man, a plan, a canal: Panama"));
        assertEquals(false,vp.palindromeRecursive("race a car"));
        assertEquals(true, vp.palindromeRecursive(" "));
    }
}
