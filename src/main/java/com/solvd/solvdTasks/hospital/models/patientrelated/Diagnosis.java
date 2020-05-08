package com.solvd.solvdTasks.hospital.models.patientrelated;

import java.time.LocalDate;

import com.solvd.solvdTasks.hospital.models.enums.Disease;

public class Diagnosis {
	private LocalDate date;
	private Disease disease;
	private String prognosis;
	
	public Diagnosis() {}
	
	public Diagnosis(LocalDate date) {
		this.date = date;
	}	
	public Diagnosis(Disease disease, LocalDate date) {
		this.date = date;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Disease getDisease() {
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	public String getPrognosis() {
		return prognosis;
	}
	public void setPrognosis(String prognosis) {
		this.prognosis = prognosis;
	}
}
