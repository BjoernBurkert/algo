/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import dictionary.Dictionary;
import java.util.Iterator;

/**
 *
 * @author da851oez
 */
public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	private static final int DEF_CAPACITY = 31;
	private LinkedList<Entry<K, V>>[] table;

	public HashDictionary() {
		this(DEF_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public HashDictionary(int n) {
		table = new LinkedList[n];
		for (int i = 0; i < n; ++i) {
			table[i] = new LinkedList<Entry<K, V>>();
		}

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
		LinkedList<Entry<K, V>> l = table[hash(key)];
		if (search(key) != null) {
			return value;
		} else {
			l.insert(new Entry<K, V>(key, value));
			return null;
		}
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public V remove(K key) {
		LinkedList<Entry<K, V>> l = table[hash(key)];
		V value = this.search(key);
		if (value == null) {
			return null;
		}
		Entry<K, V> e = l.remove(new Entry<>(key, value));
		System.out.println(e);
		if (e == null) {
			return null;
		}
		return e.value;
	}

	@Override
	public V search(K key) {
		LinkedList<Dictionary.Entry<K, V>> list = table[hash(key)];
		if (list == null) {
			return null;
		}
		for (Dictionary.Entry<K, V> e : list) {
			if (e.key.equals(key)) {
				System.out.println(e.value);
				return e.value;
			}
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
