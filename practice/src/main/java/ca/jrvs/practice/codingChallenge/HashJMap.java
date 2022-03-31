package ca.jrvs.practice.codingChallenge;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HashJMap<K, V> extends HashMap implements JMap {
    @Override
    public boolean equals(Object o){
        //Compare key-value pairs of self and o (only if o is a hashmap)
        if(o instanceof HashMap) {
            AtomicBoolean match = new AtomicBoolean(true);
            //foreach item in o, find if there is a match in self
            ((HashMap<?, ?>) o).forEach((key, value) -> {
                if(!this.containsKey(key) || this.get(key) != value){
                    match.set(false);
                }
            });
            return match.get();
        }
        return false;
    }
}
