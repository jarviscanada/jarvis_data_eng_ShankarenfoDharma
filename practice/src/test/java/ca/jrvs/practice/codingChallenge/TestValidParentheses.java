package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestValidParentheses {
    @Test
    public void testValidP(){
        ValidParentheses vP = new ValidParentheses();
        assertEquals(false,vP.isValid("}"));
        assertEquals(true,vP.isValid("()[]{}"));
        assertEquals(false,vP.isValid("(]"));
        assertEquals(true,vP.isValid("{[]}()"));
        assertEquals(true,vP.isValid("([][][])"));
        assertEquals(true,vP.isValid("[]{{}}"));
        assertEquals(false,vP.isValid("([)]"));
        assertEquals(false,vP.isValid("(()){[)}"));
    }
}