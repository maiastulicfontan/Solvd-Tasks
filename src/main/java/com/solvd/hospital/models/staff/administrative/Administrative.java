package com.solvd.hospital.models.staff.administrative;

import com.solvd.hospital.models.staff.Employee;

public abstract class Administrative extends Employee {

	public Administrative () {}
	
	public Administrative(String firstName, String lastName, int id) {
		super(firstName, lastName,id);
	}

}
