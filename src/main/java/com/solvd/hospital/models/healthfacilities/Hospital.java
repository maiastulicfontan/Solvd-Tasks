package com.solvd.hospital.models.healthfacilities;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

import com.solvd.hospital.models.exceptions.EmployeeNotFoundException;
import com.solvd.hospital.models.generalpurpose.Address;
import com.solvd.hospital.models.patientrelated.Appointment;
import com.solvd.hospital.models.staff.administrative.Receptionist;
import com.solvd.hospital.models.staff.medicalstaff.Doctor;
import com.solvd.hospital.models.staff.medicalstaff.Nurse;

public abstract class Hospital {
	private static Logger LOGGER = LogManager.getLogger(Hospital.class);
	
	private String name;
	private Address address;
	private String telNumber;
	private List<Doctor> doctors = new ArrayList<Doctor>();
	private List<Nurse> nurses = new ArrayList<Nurse>();
	private List<Receptionist> recs = new ArrayList<Receptionist>();
	private List<Appointment> appoints = new ArrayList<Appointment>();
	
	//constructors
	public Hospital () { }
	
	public Hospital (String name){
		this.name = name;
	}	

	//setters and getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(ArrayList<Nurse> nurses) {
		this.nurses = nurses;
	}

	public List<Receptionist> getRecs() {
		return recs;
	}

	public void setRecs(ArrayList<Receptionist> recs) {
		this.recs = recs;
	}

	public List<Appointment> getAppoints() {
		return appoints;
	}

	public void setAppoints(ArrayList<Appointment> appoints) {
		this.appoints = appoints;
	}
	
	public void addDoctor (Doctor doctor) {
		this.getDoctors().add(doctor);
		LOGGER.info("Doctor registered successfully.");
	}
	
	public void removeDoctor (Doctor doctor) {
		if (this.getDoctors().contains(doctor)) {
			this.getDoctors().remove(doctor);
			LOGGER.info("Doctor removed successfully");
		} else {
			throw new EmployeeNotFoundException("The doctor you're trying to remove does not belong to this hospital");
	}
	}
	
	public void addNurse (Nurse nurse) {
		this.getNurses().add(nurse);
		LOGGER.info("Nurse registered successfully.");
	}
	
	public void removeNurse (Nurse nurse) {
		if (this.getNurses().contains(nurse)) {
			this.getNurses().remove(nurse);
			LOGGER.info("Nurse removed successfully");
		} else {
			throw new EmployeeNotFoundException("The nurse you're trying to remove does not belong to this hospital");
		}		
}
}
