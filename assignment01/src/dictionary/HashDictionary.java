/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import dictionary.Dictionary.Entry;

import java.util.Iterator;

/**
 *
 * @author da851oez
 */
public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

	private static class Node<K, V> {
		public Entry<K, V> data;
		public Node<K, V> next;

		public Node(Entry<K, V> t_data, Node t_next) {
			data = t_data;
			next = t_next;
		}
	}

	private static final int DEF_CAPACITY = 31;
	private Node<K, V>[] table;

	public HashDictionary() {
		this(DEF_CAPACITY);
	}

	public HashDictionary(int n) {
		table = (Node<K, V>[]) new Node[n];
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
		// Check if the element is already there
		if (search(key) != null) {
			return value;
		}
		// Compute the hash value
		final int hashValue = hash(key);
		// Insert for the case that the list is empty
		if (table[hashValue] == null) {
			table[hashValue] = new Node<K, V>(new Entry<K, V>(key, value), null);
			return value;
		}
		// Insert for the case that the list isn't empty
		if (table[hashValue] != null) {
			Node<K, V> tmp = table[hashValue];
			table[hashValue] = new Node<K, V>(new Entry<K, V>(key, value), tmp);
			return value;
		}
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new Iterator<Entry<K, V>>() {
			int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < size() && table[currentIndex] != null;
			}

			@Override
			public Entry<K, V> next() {
				return null;
			}
		};
	}

	@Override
	public V remove(K key) {
		return null;
	}

	@Override
	public V search(K key) {
		/*
		 * LinkedList<Dictionary.Entry<K, V>> list = table[hash(key)]; if (list == null)
		 * { return null; } for (Dictionary.Entry<K, V> e : list) { if
		 * (e.key.equals(key)) { return e.value; } }
		 */
		return null;
	}

	@Override
	public int size() {
		return table.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node<K, V> n : table) {
			sb.append("{");
			for (Node<K, V> p = n; p != null; p = p.next) {
				sb.append("(");
				sb.append(p.data.key);
				sb.append("; ");
				sb.append(p.data.value);
				sb.append(")");
				sb.append(", ");
			}
			sb.append("} ");
		}
		return sb.toString();
	}

}
