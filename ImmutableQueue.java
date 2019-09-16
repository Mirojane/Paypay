package com.paypay;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ImmutableQueue<T> implements QueueJL<T>, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private transient int size = 0;
	protected transient int modCount = 0;

	// Pointer to first node.
	private transient Node<T> first;

	// Pointer to last node.
	private transient Node<T> last;

	// Constructs an empty list.
	public ImmutableQueue() {
	}

	// insert an element at the rear of the queue and return the new queue
	public QueueJL<T> enQueue(T t) {
		add(t);
		QueueJL<T> q = new ImmutableQueue<>();
		for (Node<T> x = first; x != null; x = x.next)
			q.add(x.item);
		return q;
	}

	// Removes the element at the beginning of the immutable queue, and returns the new queue.
	public QueueJL<T> deQueue() {
		QueueJL<T> q = new ImmutableQueue<>();
		final Node<T> f = first;
		final Node<T> next = f.next;
		f.item = null;
		f.next = null;
		first = next;
		if (next == null)
			last = null;
		else
			next.prev = null;
		size--;
		modCount++;

		for (Node<T> x = first; x != null; x = x.next)
			q.add(x.item);
		return q;
	}

	// Retrieves, but does not remove, the head (first element) of this list.
	public T head() {
		final Node<T> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return f.item;
	}

	// Returns true if, and only if, length is 0
	public boolean isEmpty() {
		return first == last && first == null;
	}

	public boolean add(T t) {
		final Node<T> l = last;
		final Node<T> newNode = new Node<>(l, t, null);
		last = newNode;
		if (l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
		modCount++;
		return true;
	}

	// Returns an array containing all of the elements in this list in proper sequence (from first to last element).
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node<T> x = first; x != null; x = x.next)
			result[i++] = x.item;
		return result;
	}

	public static void main(String[] args) {
		// Integer type Queue
		QueueJL<Integer> q = new ImmutableQueue<>();
		// Adds elements {0, 1, 2, 3, 4} to queue
		for (int i = 0; i < 5; i++)
			q.add(i);

		System.out.println("Elements of queue-q:  " + Arrays.toString(q.toArray()));
		int h1 = q.head();
		System.out.println("Elements of head:  " + h1);
		boolean ie = q.isEmpty();
		System.out.println("Is queue empty:  " + ie);

		QueueJL<Integer> q2 = new ImmutableQueue<>();
		q2 = q.deQueue();
		System.out.println("Elements of deQueue-q2: " + Arrays.toString(q2.toArray()));

		QueueJL<Integer> q3 = new ImmutableQueue<>();
		q3 = q.enQueue(9);
		System.out.println("Elements of enQueue-q3: " + Arrays.toString(q3.toArray()));

		// Character type Queue
		QueueJL<Character> c = new ImmutableQueue<>();
		boolean ic = c.isEmpty();
		System.out.println("Is queue empty 1: " + ic);
		c.add('a');
		c.add('b');
		Character hc = c.head();
		System.out.println("Elements of queue c: " + Arrays.toString(c.toArray()));
		System.out.println("Elements of head:  " + hc);
		boolean ic2 = q.isEmpty();
		System.out.println("Is queue empty 2:  " + ic2);
	}
}

class Node<T> {
	T item;
	Node<T> next;
	Node<T> prev;

	Node(Node<T> prev, T element, Node<T> next) {
		this.item = element;
		this.next = next;
		this.prev = prev;
	}
}
