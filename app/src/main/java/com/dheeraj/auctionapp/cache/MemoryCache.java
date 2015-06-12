package com.dheeraj.auctionapp.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DBhati on 11-Jun-15.
 */
public class MemoryCache <T>{
    private Map<String, SoftReference<T>> cache = new HashMap<String, SoftReference<T>>();

    public T get(String id){
        if(!cache.containsKey(id))
            return null;
        SoftReference<T> obj = cache.get(id);
        return obj.get();
    }

    public void put(String id, T obj){
        cache.put(id, new SoftReference<T>(obj));
    }

    public void clear() {
        cache.clear();
    }

}
