package com.solvd.hospital.models.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

	public  EmployeeNotFoundException() {}
	
	public  EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public  EmployeeNotFoundException(String message, Throwable t) {
		super(message, t);
	}

}
