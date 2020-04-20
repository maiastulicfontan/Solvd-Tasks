package com.solvd.hospital.models.patientrelated;

import com.solvd.hospital.models.enums.Disease;

public class Treatment {
	
	private boolean isEffective;
	private Patient patient;
	private String description;
	private Disease disease;
	
	public Treatment() {}
	
	public Treatment (Disease disease) {
		this.disease = disease;
		this.description = disease.getTreatment();
	}
	
	public boolean isEffective() {
		return isEffective;
	}
	public void setEffective(boolean isEffective) {
		this.isEffective = isEffective;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.disease.toString();
	}
	
	@Override
	public String toString() {
		return "Treatment: "+ this.description;
	}
}
