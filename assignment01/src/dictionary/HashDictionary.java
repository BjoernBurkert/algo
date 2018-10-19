/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import dictionary.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author da851oez
 */
public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	private static final int DEF_CAPACITY = 31;
    private int size = 0;
	//private LinkedList<Entry<K, V>>[] table;
    private LinkedList<K>[] table;

	public HashDictionary() {
		this(DEF_CAPACITY);
	}

    /*@SuppressWarnings("unchecked")
	public HashDictionary(int n) {
		table = new LinkedList[n];

	}*/
	
	public HashDictionary(int n) {
		List<LinkedList<Entry<K,V>>> array = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			array.add(new LinkedList<Entry<K,V>>());
		}
		array.toArray();
		this.size = array.size();
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
		LinkedList<Dictionary.Entry<K,V>> list = table[hash(key)];
		if (list == null) {
			return null;
		}
		
		
		for ()
		
		for(Dictionary.Entry<K, V> e : list) {
			if(e.key.equals(key)) {return V}
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
