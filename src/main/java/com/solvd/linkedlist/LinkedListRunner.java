package com.solvd.linkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedListRunner {
	private static final Logger LOGGER = LogManager.getLogger(LinkedListRunner.class);
	
	public static void main(String[] args) {
		
		LinkedList <String> listA = new LinkedList <String>();
		listA.add("A");
		listA.add("B");
		listA.add("C");
		listA.add("D");
		listA.add("F");		
		LOGGER.info("List head: "+ listA.getHead());
		LOGGER.info("Last element: "+ listA.getLast());
		listA.add(0,"E");
		listA.addFirst("Z");
		LOGGER.info("List head: "+ listA.getHead());
		LOGGER.info("List size: "+ listA.size());
		listA.iterate();
		listA.reverseIterate();
		
		
		LinkedList <String> listB = new LinkedList<String>();
		listB.add("Hello");
		listB.iterate();
		listB.addFirst("Bye");
		listB.iterate();
		LOGGER.info("List head: "+ listB.getHead());
		LOGGER.info("Last element: "+ listB.getLast());
		listB.iterate();
		
		LinkedList <String> listC = new LinkedList<String>();
		listC.addFirst("hi");
		LOGGER.info("List head: "+ listC.getHead());
		LOGGER.info("Last element: "+ listC.getLast());
		
		LinkedList <String> listD = new LinkedList<String>();
		listD.iterate();
	}
}
