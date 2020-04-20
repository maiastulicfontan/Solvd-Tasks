package com.solvd.hospital.models.patientrelated;

import java.time.LocalDate;

public class Prescription {
	private LocalDate date;
	private String indications;
	private Patient patient;
	private String healthInsuranceCompany;
	
	
	public Prescription() {}
	
	public Prescription(LocalDate date, String indications, Patient patient) {
		this.date = date;
		this.indications = indications;
		this.patient = patient;
		if (patient.getHealthInsuranceCompany() != null) {
			this.healthInsuranceCompany = patient.getHealthInsuranceCompany(); 
		}
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getIndications() {
		return indications;
	}
	public void setIndications(String indications) {
		this.indications = indications;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getHealthInsuranceCompany() {
		return healthInsuranceCompany;
	}
	public void setHealthInsuranceCompany(String healthInsuranceCompany) {
		this.healthInsuranceCompany = healthInsuranceCompany;
	}

}
