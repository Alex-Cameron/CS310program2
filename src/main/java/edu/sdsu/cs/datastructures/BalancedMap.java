package edu.sdsu.cs.datastructures;

import java.util.*;

public class BalancedMap<K extends Comparable<K>, V> implements IMap<K, V>{

    Map<K, V> map = new TreeMap<>();

    public BalancedMap(){
    }

    public BalancedMap(IMap<K, V> data){
        for (K key : data.keyset()){
            map.put(key, data.getValue(key));
        }
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean add(K key, V value) {
        if (map.containsKey(key))
            return false;
        else
            map.put(key, value);
            return true;
    }

    @Override
    public V delete(K key) {
        return map.remove(key);
    }

    @Override
    public V getValue(K key) {
        return map.get(key);
    }

    @Override
    public K getKey(V value) {
        for (Map.Entry<K,V> entry : map.entrySet()){
            if (value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Iterable<K> getKeys(V value) {
        List<K> toReturn = new java.util.LinkedList<>();
        for (Map.Entry<K,V> entry : map.entrySet()){
            if (value.equals(entry.getValue())){
                toReturn.add(entry.getKey());
            }
        }
        return toReturn;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.size() == 0;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterable<K> keyset() {
        List<K> toReturn = new java.util.LinkedList<>();
        for (Map.Entry<K,V> entry : map.entrySet()){
            toReturn.add(entry.getKey());
        }
        return toReturn;
    }

    @Override
    public Iterable<V> values() {
        List<V> toReturn = new java.util.LinkedList<>();
        for (Map.Entry<K,V> entry : map.entrySet()){
            toReturn.add(entry.getValue());
        }
        return toReturn;
    }
}
