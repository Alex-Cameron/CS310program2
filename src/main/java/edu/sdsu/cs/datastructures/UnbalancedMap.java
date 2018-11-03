package edu.sdsu.cs.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UnbalancedMap<K extends Comparable<K>, V> implements IMap<K, V> {

    K key;
    V value;
    MapNode root;

    public UnbalancedMap() {
    }

    public UnbalancedMap(IMap<K, V> data) {
        for (K key : data.keyset()) {
            add(key, data.getValue(key));
        }
    }

    private class MapNode implements Comparable<MapNode> {
        MapEntry contents;
        MapNode left;
        MapNode right;

        @Override
        public int compareTo(MapNode mapNode) {
            return contents.compareTo(mapNode.contents);
        }
    }

    private class MapEntry implements Comparable<MapEntry> {
        K key;
        V value;

        @Override
        public int compareTo(MapEntry mapEntry) {
            return key.compareTo(mapEntry.key);
        }
    }

    @Override
    public boolean contains(K key) {
        return contains(key, root);
    }

    @Override
    public boolean add(K key, V value) {
        return false;
    }

    @Override
    public V delete(K key) {
        return null;
    }

    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public K getKey(V value) {
        return null;
    }

    @Override
    public Iterable<K> getKeys(V value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterable<K> keyset() {
        return null;
    }

    @Override
    public Iterable<V> values() {
        return null;
    }

    public boolean contains(K key, MapNode node) {
        int comparedVal = node.contents.key.compareTo(key);
        if (comparedVal == 0) {
            return true;
        } else if (comparedVal < 0) {
            if (node.left != null) {
                return contains(key, node.left);
            } else {
                return false;
            }
        } else {
            if (node.right != null) {
                return contains(key, node.right);
            } else {
                return false;
            }
        }
    }
}/*
    @Override
    /*
    Need to return false under correct conditions

    public boolean add(K key, V value) {
        MapNode cur = root;
        MapEntry newVal = new MapEntry();
        newVal.key = key;
        newVal.value = value;
        MapNode newNode = new MapNode();
        newNode.contents = newVal;

        if(root == null){
            root = newNode;
            return true;
        }

        int destination = -1;
        while(destination == -1){
            //System.out.println(cur.contents.value + " : " + value + "  :  " + cur.contents.compareTo(newVal));
            if(cur.contents.compareTo(newVal) > 0){
                if(cur.left != null) {
                    cur = cur.left;
                } else {
                    destination = 1;
                }
            } else {
                if(cur.right != null) {
                    cur = cur.right;
                } else {
                    destination = 2;
                }
            }
        }

        if(destination == 1){
            cur.left = newNode;
        } else {
            cur.right = newNode;
        }

        return true;
    }



    @Override
    public V delete(K key) {
        return null;
    }

    @Override
    public V getValue(K key) {
        return getValue(key, root);
    }

    public V getValue(K key, MapNode node){
        int comparedVal = node.contents.key.compareTo(key);
        if(comparedVal == 0){
            return node.contents.value;
        } else if (comparedVal > 0){
            if(node.left != null){
                return getValue(key, node.left);
            } else {
                return null;
            }
        } else {
            if(node.right != null){
                return getValue(key, node.right);
            } else {
                return null;
            }
        }
    }

    @Override
    public K getKey(V value) {
            return getKey(value, root);
        }

        public K getKey(V value, MapNode node) {
            if(node.contents.value == value){
                return node.contents.key;
            }
            K leftKey = getKey(value, node.left);
            if(leftKey != null){
                return leftKey;
            }
            K rightKey = getKey(value, node.left);
            if(rightKey != null){
                return rightKey;
            }
            return null;
        }

    @Override
    public Iterable<K> getKeys(V value) {
        List<K> toReturn = new java.util.LinkedList<>();
        return null;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(MapNode node){
        int tot = 0;
        if(node == null){
            return 0;
        }
        if(node.left != null){
            tot += size(node.left);
        }
        if(node != null){
            tot += 1;
        }
        if(node.right != null){
            tot += size(node.right);
        }
        return tot;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterable<K> keyset() {
        return keyset(root);
    }
        public List<K> keyset(MapNode node){
            List<K> tot = new LinkedList<K>();
            if(node == null){
                return tot;
            }
            if(node.left != null){
                tot.addAll(keyset(node.left));
            }
            if(node.contents != null){
                tot.add(node.contents.key);
            }
            if(node.right != null){
                tot.addAll(keyset(node.right));
            }
            return tot;
    }

    @Override
    public Iterable<V> values() {
        return values(root);
    }

        public List<V> values(MapNode node){
            List<V> tot = new LinkedList<V>();
            if(node == null){
                return tot;
            }
            if(node.left != null){
             tot.addAll(values(node.left));
            }
            if(node.contents != null){
               tot.add(node.contents.value);
            }
            if(node.right != null){
                tot.addAll(values(node.right));
            }
            return tot;
    }
}
*/