package com.solvd.hospital.models.interfaces;

import com.solvd.hospital.models.enums.Disease;
import com.solvd.hospital.models.patientrelated.Patient;

public interface ICure {
	public void curePatient(Patient patient, Disease disease);
}
