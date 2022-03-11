package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRotateString {
    @Test
    public void rotateString(){
        RotateString rS = new RotateString();
        assertEquals(true,rS.rotateString("12345","23451"));
        assertEquals(false,rS.rotateString("abcd","dcba"));
        assertEquals(true,rS.rotateString("abcd","dabc"));
        assertEquals(false,rS.rotateString("shen","nehs"));
        assertEquals(true,rS.rotateString("Shun","unsh"));
        assertEquals(false,rS.rotateString("Type","typhoid"));
    }
}
