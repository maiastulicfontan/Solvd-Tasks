package com.solvd.solvdTasks.hospital.models.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

	public AppointmentNotFoundException() {}
	
	public AppointmentNotFoundException(String message) {
		super(message);
	} 
	
	public AppointmentNotFoundException(String message, Throwable t) {
		super(message, t);
	} 
}
