package com.solvd.solvdTasks.linkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LinkedList <E> {
	private static final Logger LOGGER = LogManager.getLogger(LinkedList.class);
	
	private Node <E> head;
	private Node <E> last;
	private int length;
	
	
	public LinkedList() {
		length = 0;
	}
	
	public void iterate() {
		if (this.getLength() != 0) {
			LOGGER.info("Iterating list on regular order");
			Node<E> tmp = head;
			LOGGER.info(tmp);
			while (tmp.getNext() != null) {
				tmp = tmp.getNext();
				LOGGER.info(tmp);
			}
		} else {
			throw new EmptyListException("Can't iterate through an empty list");
		}		
	}
	
	public void reverseIterate() {
		if (this.getLength() != 0) {
			LOGGER.info("Iterating list on reverse order");
			Node<E> tmp = last;
			LOGGER.info(tmp);
			while (tmp.getPrevious() != null) {
				tmp = tmp.getPrevious();
				LOGGER.info(tmp);
			}
		} else {
			throw new EmptyListException("Can't iterate through an empty list");
		}		
	}
	
	//adds element to the end of the list
	public void add(E data) {	
		if (this.length == 0) {
			head = new Node<E>(data);
			last = new Node<E>(data);
		} else {
			Node<E> tmp = new Node<E>(data);
			Node<E> actual = head;
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(tmp);
			tmp.setPrevious(actual);
			last = tmp;
		}
		length++;
	}
	
	//adds element at the specified index
	public void add(int index, E data) {
		if (index < this.getLength()) {
			if (index == 0) {
				addFirst(data);
			} else {
				Node<E> tmp = new Node<E>(data);
				Node<E> actual = head;
				for (int i = 0; i < index; i++ ) {
					actual = actual.getNext();
				}
				actual.getPrevious().setNext(tmp);
				tmp.setPrevious(actual.getPrevious());
				actual.setPrevious(tmp);
				tmp.setNext(actual);
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	//adds element at the beginning of the list
	public void addFirst(E data) {
		if (this.getLength() != 0) {
			Node<E> tmp = new Node<E>(data);
			head.setPrevious(tmp);
			tmp.setNext(head);
			head = tmp;
		} else {
			this.add(data);
		}
		length++;
	}
	
	
	
	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}

	public Node<E> getLast() {
		return last;
	}

	public void setLast(Node<E> last) {
		this.last = last;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int size) {
		this.length = size;
	}
	
	public int size() {
		return length;
	}
}
