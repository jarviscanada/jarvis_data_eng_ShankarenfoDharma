package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestDuplicateChars {
    @Test
    public void testCharDupes(){
        DuplicateCharacters dc = new DuplicateCharacters();
        assertArrayEquals(new char[]{'a','c'}, dc.detectDuplicate("a black cat"));
    }
}
