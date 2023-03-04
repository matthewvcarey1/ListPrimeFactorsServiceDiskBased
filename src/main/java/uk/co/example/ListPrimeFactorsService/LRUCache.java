package uk.co.example.ListPrimeFactorsService;
import java.util.*;

class LRUCache {

    Map<Long,String> cache;
    int capacity;

    public LRUCache(int capacity)
    {
        this.cache = Collections.synchronizedMap(new LinkedHashMap<Long,String>(capacity));
        this.capacity = capacity;
    }
    public String get(long key)
    {
        if (!cache.containsKey(key))
            return null;
        String value=cache.get(key);
        cache.remove(key);
        cache.put(key, value);

        return value;
    }


    public void put(long key, String value)
    {
        while (cache.size() >= capacity) {
            synchronized(cache) {
                long firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
        }
        cache.put(key, value);
    }


}