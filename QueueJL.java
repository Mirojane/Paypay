package com.paypay;


/**
 *  @param <T> the type of elements held in this collection
 *  To avoid the Interface name confliction of Queue in JDK, the customized Queue is named as QueueJL to differentiate them
 */

public interface QueueJL<T> {
	//insert an element at the rear of the queue and return the new queue
    public QueueJL<T> enQueue(T t);
    // Removes the element at the beginning of the immutable queue, and returns the new queue.
    public QueueJL<T> deQueue();
    // get the first element at the front of the queue
    public T head();
    // check if queue is empty
    public boolean isEmpty();
    //Appends the specified element to the end of this list.
	public boolean add(T t);
	//Returns an array containing all of the elements in this list in proper sequence (from first to last element)
	public Object[] toArray();

}
