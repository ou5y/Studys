package com.example.practice.hashmap;

/**
 * Created by baipan
 * Date: 2018-07-12
 */
public interface MyMap<K, V> {

    void put(K k, V v) throws Exception;

    V get(K k) throws Exception;

    int size() throws Exception;

    interface Entry<K,V>{
        K getKey() throws Exception;

        V getValue() throws Exception;
    }


}
