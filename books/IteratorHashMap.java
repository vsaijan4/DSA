package books;

import java.util.Iterator;
import java.util.Map;

class IteratorHashMap {
    Map<Integer, Integer> hashMap;

    //keys AND values
    public void iterate() {
        for (Map.Entry<Integer, Integer> mapEntry : hashMap.entrySet()) {
            System.out.println(mapEntry.getKey());
            System.out.println(mapEntry.getValue());
        }
    }

    //keys OR values
    public void iterateKeysOrValues() {
        for (Integer key : hashMap.keySet())
            System.out.println(key);
        for (Integer value : hashMap.values())
            System.out.println(value);
    }

    //remove using Iterator
    public void remove() {
        Iterator<Map.Entry<Integer, Integer>> iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }
}
