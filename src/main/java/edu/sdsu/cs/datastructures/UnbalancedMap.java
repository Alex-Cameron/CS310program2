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

    private boolean contains(K key, MapNode node) {
        int comparedVal = node.contents.key.compareTo(key);
        if (comparedVal == 0) {
            return true;
        } else if (comparedVal > 0) {
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

    @Override
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
        return add(newNode, root);

    }

    private boolean add(MapNode node, MapNode parent){
        if(parent == null || node == null){
            return false;
        }
        int eval = node.contents.key.compareTo(parent.contents.key);
        if(eval < 0){
            if(parent.left == null){
                parent.left = node;
                return true;
            } else {
                return add(node, parent.left);
            }

        } else if(eval > 0){
            if(parent.right == null){
                parent.right = node;
                return true;
            } else {
                return add(node, parent.right);
            }
        } else {
            return false;
        }
    }



    @Override
    public V delete(K key) {
        V oldVal = getValue(key);
        if(oldVal == null){
            return null;
        }
        root = delete(key, root);
        return oldVal;
    }

    private MapNode delete(K key, MapNode node){
        if(node == null){
            return null;
        }
        int eval = node.contents.key.compareTo(key);
        if(eval == 0){
            if(node.left == null){
                return node.right;
            } else if (node.right == null){
                return node.left;
            }
            node.contents = getMin(node.right).contents;
            node.right = delete(node.contents.key, node.right);
        } else if (eval > 0){
            node.left = delete(key, node.left);
        } else if (eval < 0){
            node.right = delete(key, node.right);
        }

        return node;
    }

    @Override
    public V getValue(K key) {
        return getValue(key, root);
    }

    private V getValue(K key, MapNode node){
        if(node == null){
            return null;
        }
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

    private MapNode getMin(MapNode node){
        if(node.left == null){
            return node;
        }
        return getMin(node.left);
    }

    @Override
    public K getKey(V value) {
        LinkedList<K> keys = getKeys(value, root);
        if(keys.isEmpty()){
            return null;
        }
        return keys.getFirst();
    }

    @Override
    public Iterable<K> getKeys(V value) {
        return getKeys(value, root);
    }

    private LinkedList<K> getKeys(V value, MapNode node){
        LinkedList<K> toReturn = new LinkedList<K>();
        if(node == null){
            return toReturn;
        }
        if(node.left != null){
            toReturn.addAll(getKeys(value, node.left));
        }
        if(node != null && node.contents.value.equals(value)){
            toReturn.add(node.contents.key);
        }
        if(node.right != null){
            toReturn.addAll(getKeys(value, node.right));
        }
        return toReturn;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(MapNode node){
        int tot = 0;
        if(node == null){
            return 0;
        } else {
            tot++;
        }
        if(node.left != null){
            tot += size(node.left);
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
    private LinkedList<K> keyset(MapNode node){
        LinkedList<K> tot = new LinkedList<K>();
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

    private List<V> values(MapNode node){
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
