package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCompareTwoMaps {

    @Test
    public void compareTwoMapsFunc(){
        Map<Integer,String> m1 = new HashMap<>();
        m1.put(1,"C");
        m1.put(2,"A");
        m1.put(3,"B");
        Map<Integer,String> m2 = new HashMap<>();
        m2.put(1,"C");
        m2.put(2,"A");
        m2.put(3,"B");
        Map<Integer,String> m3 = new HashMap<>();
        m3.put(3,"C");
        m3.put(2,"A");
        m3.put(1,"B");
        Map<Integer,String> m4 = new HashMap<>();
        m4.put(1,"c");
        m4.put(2,"a");
        m4.put(3,"b");

        CompareTwoMaps cTM = new CompareTwoMaps();
        assertTrue(cTM.compareMapsEquals(m1,m2));
        assertFalse(cTM.compareMapsEquals(m1,m3));
        assertFalse(cTM.compareMapsEquals(m1,m4));
    }

//    @Test
//    public void compareTwoHashJMaps(){
//        HashJMap<Integer,String> m1 = new HashJMap<>();
//        m1.put(1,"C");
//        HashJMap<Integer,String> m2 = new HashJMap<>();
//        m2.put(1,"C");
//        HashJMap<Integer,String> m3 = new HashJMap<>();
//        m3.put(1,"B");
//        HashJMap<Integer,String> m4 = new HashJMap<>();
//        m4.put(1,"c");
//
//        CompareTwoMaps cTM = new CompareTwoMaps();
//        assertTrue(cTM.compareMapsHashJMapEquals(m1,m2));
//        assertFalse(cTM.compareMapsHashJMapEquals(m1,m3));
//        assertFalse(cTM.compareMapsHashJMapEquals(m1,m4));
//    }
}
