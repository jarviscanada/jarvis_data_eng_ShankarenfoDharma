package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * Ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-0fecc6d3dae747ceb04c0b75c5bb3b95
 */
public class CompareTwoMaps {

    /**
     * Use .equals API
     * Big-O: O(n)
     * Justification: comparison of hashes in each entry
     * @param m1 first map
     * @param m2 second map
     * @param <K> key datatype
     * @param <V> value datatype
     * @return boolean True/False
     */
    public <K,V> boolean compareMapsEquals(Map<K,V> m1, Map<K,V> m2){
        return m1.equals(m2);
    }

    /**
     * Implement equals in HashJMap
     * Big-O: O(n)
     * Justification: comparison of hashes in each entry
     * @param m1 first map
     * @param m2 second map
     * @param <K> key datatype
     * @param <V> value datatype
     * @return boolean True/False
     */
    public <K,V> boolean compareMapsHashJMapEquals(HashJMap<K,V> m1, HashJMap<K,V> m2){
        return m1.equals(m2);
    }
}
