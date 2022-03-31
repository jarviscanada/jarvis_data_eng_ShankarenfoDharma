package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-9c2df1ff91684394a626e2c4bf6ae0ea
 */
public class RemoveLinkedListDuplicates {
    /**
     * Use hashSet to track uniques -> add into new linkedlist if unique
     * Big-O: O(n)
     * Justification: go through the linkedlist array
     * @param lL
     * @return
     */
    LinkedList<Integer> removeDuplicates(LinkedList<Integer> lL){
        HashSet<Integer> uniqueSet = new HashSet<>();
        LinkedList<Integer> resultList = new LinkedList<>();
        lL.forEach(node -> {
            if(!uniqueSet.contains(node)){
                uniqueSet.add(node);
                resultList.add(node);
            }
        });
        return resultList;
    }
}
