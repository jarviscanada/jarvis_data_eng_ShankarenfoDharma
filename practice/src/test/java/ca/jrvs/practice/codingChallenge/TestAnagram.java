package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAnagram {
    @Test
    public void testAnagrams(){
        ValidAnagram  vA = new ValidAnagram();
        assertEquals(true,vA.isAnagramSort("ABBA","BBAA"));
        assertEquals(false,vA.isAnagramSort("rat","car"));
        assertEquals(true,vA.isAnagramSort("anagram","nagaram"));
        assertEquals(true,vA.isAnagramSort("Soleil","loliSe"));

        assertEquals(true,vA.isAnagramStack("ABBA","BBAA"));
        assertEquals(false,vA.isAnagramStack("rat","car"));
        assertEquals(true,vA.isAnagramStack("anagram","nagaram"));
        assertEquals(true,vA.isAnagramStack("Soleil","loliSe"));
    }
}
