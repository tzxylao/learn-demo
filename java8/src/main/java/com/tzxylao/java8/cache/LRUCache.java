package com.tzxylao.java8.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author laoliangliang
 * @since 2019-08-03 12:39
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;


    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return CACHE_SIZE < size();
    }

    public static void main(String[] args) {
        LRUCache<String, Object> lruCache = new LRUCache<>(2);
        lruCache.put("key1", 1);
        System.out.println(lruCache.keySet());
        System.out.println(lruCache.values());
        lruCache.put("key2", 2);
        System.out.println(lruCache.keySet());
        System.out.println(lruCache.values());
        Object key1 = lruCache.get("key1");
        System.out.println("查询key1："+key1);
        lruCache.put("key3", 3);
        System.out.println(lruCache.keySet());
        System.out.println(lruCache.values());
    }
}
