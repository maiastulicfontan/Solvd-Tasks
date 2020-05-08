package com.solvd.solvdTasks.hospital.models.patientrelated;



import java.time.LocalDate;
import java.time.LocalTime;

import com.solvd.solvdTasks.hospital.models.staff.medicalstaff.Doctor;

public class Appointment {
	private LocalDate date;
	private LocalTime time;
	private Doctor doctor;
	private Patient patient;
	private double cost;
	
	public Appointment() {}
	
	public Appointment(LocalDate date, LocalTime time, Doctor doctor, Patient patient) {
		this.date = date;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
	}
	
	public Appointment(LocalDate date, LocalTime time, Doctor doctor, Patient patient, double cost) {
		this.date = date;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
		this.cost = cost;
	}

	//setters and getters
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
