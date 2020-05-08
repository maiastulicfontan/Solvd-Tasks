package com.solvd.solvdTasks.hospital.models.interfaces;

import com.solvd.solvdTasks.hospital.models.enums.Disease;
import com.solvd.solvdTasks.hospital.models.patientrelated.Patient;

public interface ICure {
	public void curePatient(Patient patient, Disease disease);
}
