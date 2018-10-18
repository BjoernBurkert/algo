package dictionary;

public final class LinkedList<T> {
	private class Node<S> {
		public S data;
		public Node<S> next;

		public Node(S t_data, Node<S> t_next) {
			data = t_data;
			next = t_next;
		}
	}

	private Node<T> head;

	public LinkedList() {
		head = null;
	}

	public boolean contains(T t_data) {
		if (head == null) {
			return false;
		}

		Node<T> p = head;
		for (; p.next != null; p = p.next) {
			if ((p.data).equals(t_data)) {
				return true;
			}
		}

		return false;

	}

	public void insert(T t_data) {
		if (head == null) {
			head = new Node<T>(t_data, null);
			return;
		}
		Node<T> p = head;
		for (; p.next != null; p = p.next) {
		}
		p.next = new Node<T>(t_data, null);
	}

	@Override
	public String toString() {
		if (head == null) {
			return "";
		}
		Node<T> p = head;
		StringBuilder sb = new StringBuilder();
		for (; p.next != null; p = p.next) {
			sb.append(p.data.toString());
			sb.append("->");
		}
		sb.append(p.data.toString());
		return sb.toString();
	}
}
