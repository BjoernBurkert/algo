/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import dictionary.Dictionary.Entry;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		// Compute the hash value
		final int hashValue = hash(key);
		// Insert for the case that the list is empty
		if (table[hashValue] == null) {
			table[hashValue] = new Node<K, V>(new Entry<K, V>(key, value), null);
			return null;
		}
		// Insert for the case that the list isn't empty
		if (table[hashValue] != null) {
			Node<K, V> prev = table[hashValue];
			Node<K, V> tmp = table[hashValue];
			table[hashValue] = new Node<K, V>(new Entry<K, V>(key, value), tmp);
			return prev.data.value;
		}
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new Iterator<Entry<K, V>>() {
			int currentIndex = 0;
			Node<K, V> currentNode = null;

			@Override
			public boolean hasNext() {
				return currentIndex < size() && table[currentIndex] != null;
			}

			@Override
			public Entry<K, V> next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				currentNode = table[currentIndex];
				Node<K, V> prev = currentNode;
				// Iterate over list in 1 step
				if (currentNode != null) {
					currentNode = currentNode.next;
				}
				// Check if the list is ending and move to the next array index if so
				if (currentNode == null) {
					++currentIndex;
				}
				
				return prev.data;
			}
		};
	}

	@Override
	public V remove(K key) {
		// Check if the entry exists
		if (search(key) == null) {
			return null;
		}
		// Compute the hash index for the table (array)
		int hashIndex = hash(key);
		// Check if the first entry is a match and remove it if it is
		if (table[hashIndex].data.key.equals(key)) {
			Node<K, V> bak = table[hashIndex];
			Node<K, V> tmp = table[hashIndex].next;
			table[hashIndex] = tmp;
			return bak.data.value;
		}
		// Get the list from the table
		Node<K, V> n = table[hashIndex];
		Node<K, V> prev = n;
		// Find the entry to remove and remove it
		for (Node<K, V> p = n; p != null; p = p.next) {
			if (p.data.key.equals(key)) {
				Node<K, V> bak = p;
				Node<K, V> tmp = p.next;
				prev.next = tmp;
				return bak.data.value;
			}
			prev = p;
		}
		return null;
	}

	@Override
	public V search(K key) {
		int hashIndex = hash(key);
		// Get the linked list
		Node<K, V> nodes = table[hashIndex];
		// Check if the list is empty
		if (nodes == null) {
			return null;
		}
		// Look for the entry
		for (Node<K, V> p = nodes; p != null; p = p.next) {
			if (p.data.key.equals(key)) {
				return p.data.value;
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
