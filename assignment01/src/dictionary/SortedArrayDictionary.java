/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Iterator;

/**
 *
 * @author da851oez
 * @param <K>
 * @param <V>
 */
public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
    private static final int DEF_CAPACITY = 16;
    private int size = 0;
    private Dictionary.Entry<K,V>[] data;
    
    @SuppressWarnings("unchecked")
    public SortedArrayDictionary() {
        data = new Entry[DEF_CAPACITY];
    }
    
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
            return;
        Entry[] old = data;
        data = new Entry[newCapacity];
        System.arraycopy(old, 0, data, 0, size);
    }
    
    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);
        
        // Available entry is getting overritten:
        if (i != -1) {
            V r = data[i].value;
            data[i].value = value;
            return r;
        }
        
        // New entry:
        if (data.length == size) {
            ensureCapacity(2 * size);
        }
        int j = size - 1;
        while (j >= 0 && key.compareTo(data[j].key) < 0) {
            data[j + 1] = data[j];
            j--;
        }
        data[j + 1] = new Entry<>(key, value);
        size++;
        return null;
    }

    private int searchKey(K key) {
        int left = 0;
        int right = size - 1;
        
        while (right >= left) {
            int m = (left + right) / 2;
            if (key.compareTo(data[m].key) < 0) {
                right = m - 1;   
            } else if (key.compareTo(data[m].key) > 0) {
                left = m + 1;
            } else {
                return m; // key not found
            }
        }
        return -1; // key not found
    }
    
    @Override
    public V search(K key) {
        int i = searchKey(key);
        if (i >= 0) {
            return data[i].value;
        } else {
            return null;
        }
    }

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i == -1) {
            return null;
        }
        
        // Delete record and close the gap
        V r = data[i].value;
        for (int j = i; j < size - 1; j++)
        {
            data[j] = data[j + 1];
        }
        data[--size] = null;
        return r;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(data);
    }
    
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size && data[currentIndex] != null;
            }

            @Override
            public Entry<K, V> next() {
                return data[currentIndex++];
            }
        };
    }
}
