
package com.solvd.solvdTasks.hospital.models.staff.medicalstaff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.solvd.solvdTasks.hospital.models.enums.Disease;
import com.solvd.solvdTasks.hospital.models.enums.Specialty;
import com.solvd.solvdTasks.hospital.models.exceptions.AccountNotFoundException;
import com.solvd.solvdTasks.hospital.models.exceptions.AppointmentNotFoundException;
import com.solvd.solvdTasks.hospital.models.exceptions.InsufficientFundsException;
import com.solvd.solvdTasks.hospital.models.interfaces.ICure;
import com.solvd.solvdTasks.hospital.models.patientrelated.Appointment;
import com.solvd.solvdTasks.hospital.models.patientrelated.Diagnosis;
import com.solvd.solvdTasks.hospital.models.patientrelated.MedicalRecord;
import com.solvd.solvdTasks.hospital.models.patientrelated.Patient;
import com.solvd.solvdTasks.hospital.models.patientrelated.Prescription;
import com.solvd.solvdTasks.hospital.models.patientrelated.Treatment;
import com.solvd.solvdTasks.hospital.models.staff.Employee;
 
public class Doctor extends Employee implements ICure {
	private static final Logger LOGGER = LogManager.getLogger(Doctor.class);
	private long npi; //national provider identifier in the US
	private List<Appointment> appoints = new ArrayList<Appointment>();
	private Specialty specialty;
	
	public Doctor() {
	}
	
	public Doctor(String firstName, String lastName, int id, long npi, Specialty specialty){
		super(firstName, lastName, id);
		this.npi = npi;
		this.specialty = specialty;
	}
	public long getNpi() {
		return npi;
	}
	public void setNpi(long npi) {
		this.npi = npi;
	}
	public List<Appointment> getAppoints() {
		return appoints;
	}
	public void setAppoints(List<Appointment> appoints) {
		this.appoints = appoints;
	}
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}	
	
	public void attendAppointment (Appointment appoint, Patient patient) {
		if (this.getAppoints().contains(appoint) && patient.getAppoints().contains(appoint)) {
			if (appoint.getCost() != 0 && patient.getHealthInsuranceCompany() == null) {
				try {
					patient.payAppointment(appoint);
				} catch (InsufficientFundsException e) {
					LOGGER.error(e);
				} catch (AccountNotFoundException e){
					LOGGER.error(e);
				}
			}
			if (patient.isSick() == true) {
				LOGGER.info("Patient is sick");
				Disease tmpDisease = Disease.getRandomDisease();
				Diagnosis diagnosis = new Diagnosis(tmpDisease, appoint.getDate());
				this.curePatient(patient, tmpDisease);
				if (patient.getMedRecord()!= null) {
					patient.getMedRecord().getDiagnosis().add(diagnosis);
				} else {
					MedicalRecord medRecord = new MedicalRecord();
					medRecord.getDiagnosis().add(diagnosis);
					patient.setMedRecord(medRecord);
				}
			} else {
				LOGGER.info("Patient is not sick");
			}
		} else {
			throw new AppointmentNotFoundException("Can't find appointment");
		}
	}
	
	public void writePrescription(LocalDate date, String indications, Patient patient) {
		Prescription presc = new Prescription(date, indications, patient);
		patient.getPrescs().add(presc);
	}
	
	public void curePatient (Patient patient, Disease disease) {
		Treatment treatment = new Treatment(disease);
		patient.doTreatment(treatment);
		patient.getMedRecord().getTreatments().add(treatment);
	}
	@Override
	public String toString() {
		return "Doctor Information:\n" + "First Name: " + super.getFirstName() + "\nLast Name: " + super.getLastName()
		+ "\nNPI: " + npi + "\nSpecialty: " + specialty;
	}
}


