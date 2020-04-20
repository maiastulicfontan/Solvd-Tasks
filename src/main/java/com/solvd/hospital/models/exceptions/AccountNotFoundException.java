package com.solvd.hospital.models.exceptions;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException() {}
	
	public AccountNotFoundException(String message) {
		super(message);
	}
	
	public AccountNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
