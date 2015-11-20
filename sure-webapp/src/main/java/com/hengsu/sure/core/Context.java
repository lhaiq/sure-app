package com.hengsu.sure.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by haiquanli on 15/11/20.
 */
public class Context<K,V> {

    private Map<K,V> maps = new ConcurrentHashMap<>();

    public void put(K k,V v){
        maps.put(k,v);
    }

    public V get(K k){
        return maps.get(k);
    }
}
