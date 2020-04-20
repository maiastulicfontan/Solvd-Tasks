package com.solvd.hospital.models.staff.medicalstaff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.models.enums.Disease;
import com.solvd.hospital.models.interfaces.ICure;
import com.solvd.hospital.models.patientrelated.Patient;
import com.solvd.hospital.models.staff.Employee;

public class Nurse extends Employee implements ICure {
	private static final Logger LOGGER = LogManager.getLogger(Nurse.class);
	private int licenseNumber;
	
	public Nurse() {}
	
	public Nurse (String firstName, String lastName, int id, int licenseNumber) {
		super(firstName, lastName,id);
		this.licenseNumber = licenseNumber;
	}
	public int getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public void curePatient(Patient patient, Disease disease) {
		LOGGER.info("Medicine for " + disease.getName() + " has been given to the patient.");
	}
	
	public String toString() {
		return "\nNurse Information:\n" + "First Name: " + super.getFirstName() + "\nLast Name: " + super.getLastName()
		+ "\nLicense Number: " + licenseNumber ;
	}
}
