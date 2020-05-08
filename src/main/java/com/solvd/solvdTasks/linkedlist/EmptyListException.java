package com.solvd.solvdTasks.linkedlist;

public class EmptyListException extends RuntimeException {
	
	public EmptyListException(String message) {
		super(message);
	}
	
	public EmptyListException (String message, Throwable t) {
		super(message, t);
	}
}
