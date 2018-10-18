/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * @author da851oez
 */
public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
    private static final int DEF_CAPACITY = 31;
    private int size = 0;
    private ArrayList<LinkedList<Dictionary.Entry<K,V>>> array;
    
    public HashDictionary()
    {
        this.array = new ArrayList<>(DEF_CAPACITY);
    }
    
    public HashDictionary(int n)
    {
        this.array = new ArrayList<>(n);
    }
    
    private int hash(int k)
    {
        return k % size; 
    }
    
    @Override
    public V insert(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public V search(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
