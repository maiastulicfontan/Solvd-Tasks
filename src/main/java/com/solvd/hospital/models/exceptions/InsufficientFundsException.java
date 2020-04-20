package com.solvd.hospital.models.exceptions;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException() {}
	
	public InsufficientFundsException(String message) {
		super(message);
	}
	
	public InsufficientFundsException(String message, Throwable t) {
		super(message, t);
	}
}
