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
 */
public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	private static final int DEF_CAPACITY = 31;
    private int size = 0;
	private LinkedList<Dictionary.Entry<K, V>>[] table;

	public HashDictionary() {
		this(DEF_CAPACITY);
	}

    @SuppressWarnings("unchecked")
	public HashDictionary(int n) {
		table = new LinkedList[n];

	}
    
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
        {
            return;	
        }
        LinkedList[] old = table;
        table = new LinkedList[newCapacity];
        System.arraycopy(old, 0, table, 0, size);
    }

	private int hash(K key) {
		int address = key.hashCode();
		if (address < 0) {
			address = -address;
		}
		return address % size();
	}

	@Override
	public V insert(K key, V value) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public V remove(K key) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public V search(K key) {
		if (table[hash(key)] == null) {
			return null;
		}
		return null;
	}

	@Override
	public int size() {
		return table.length;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
