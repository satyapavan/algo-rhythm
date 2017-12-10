import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

	private int qSize;

	final private boolean ENABLE_LOG = true;

	private Node head; // also called first
	private Node tail; // also called last

	private class Node
	{
		Item item;
		Node next;
		Node prev;
	}

	public Deque()                           // construct an empty deque
	{
		head = null;
		tail = null;
		qSize = 0;
	}

	public boolean isEmpty()                 // is the deque empty?
	{
		if(ENABLE_LOG) System.out.println("isEmpty = " + (qSize == 0));
		
		return (qSize == 0);
	}

	public int size()                        // return the number of items on the deque
	{
		if(ENABLE_LOG) System.out.println("Size = " + qSize);

		return qSize;
	}

	public void addFirst(Item item)          // add the item to the front
	{
		Node oldHead ;
		
		if(isEmpty())
			oldHead = null;
		else
			oldHead = head;

		head.item = item;
		head.next = oldHead;
		head.prev = null;
		
		qSize++;
	}

	public void addLast(Item item)           // add the item to the end
	{
		Node oldTail;
		
		if(isEmpty())
			oldTail = null;
		else
			oldTail = tail;
		
		tail.item = item;
		tail.next = null;
		oldTail.next = tail;
		
		qSize++;
	}

	public Item removeFirst()                // remove and return the item from the front
	{
		Item obj = head.item;

		head = head.next;

		head.prev = null;
		
		return obj;
		
		qSize--;
	}

	public Item removeLast()                 // remove and return the item from the end
	{
		Item obj = tail.item;
		return obj;
		//	last = ding dong, how how how without previous? Lets work on this tomorrow.
		
		qSize--;
	}

	/*
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{

	}
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
