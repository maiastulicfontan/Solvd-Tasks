package com.solvd.hospital.models.patientrelated;

import java.util.List;
import java.util.ArrayList;

public class MedicalRecord {
	private List<Diagnosis> diagnosis = new ArrayList<Diagnosis> ();
	private List<Treatment> treatments = new ArrayList<Treatment> ();
	
	public MedicalRecord() {}
	
	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	
}
