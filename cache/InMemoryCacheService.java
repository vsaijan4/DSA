package cache;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCacheService {
    // ?, T, Object
    // Assumption: Object has an identifier - id
    Map<Integer, Object> cacheStore = new HashMap<>();
    Map<Integer, Integer> lastAccessMap = new HashMap<>();

    public Object get(Integer id) {
        int currentTime = (int) System.currentTimeMillis();
        int lastAccessedTime = lastAccessMap.get(id);
        if (currentTime - lastAccessedTime > InMemoryCacheProperties.TTL_MILLIS) {
            Object lastestObj = new Object(); // fetch from DB
            cacheStore.put(id, lastestObj);
            lastAccessMap.put(id, currentTime);
            return lastestObj;
        }
        return cacheStore.get(id);
    }
}
