package com.solvd.hospital.models.staff.administrative;



import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospital.models.exceptions.AppointmentNotFoundException;
import com.solvd.hospital.models.healthfacilities.Hospital;
import com.solvd.hospital.models.healthfacilities.PrivateHospital;
import com.solvd.hospital.models.healthfacilities.PublicHospital;
import com.solvd.hospital.models.patientrelated.Appointment;
import com.solvd.hospital.models.patientrelated.Patient;
import com.solvd.hospital.models.staff.medicalstaff.Doctor;

public class Receptionist extends Administrative {
	private static Logger LOGGER = LogManager.getLogger(Receptionist.class);
	
	public Receptionist() {}
	
	public Receptionist (String firstName, String lastName, int id) {
		super (firstName, lastName, id);
	}
	
	public void addAppointment (PrivateHospital phospital, LocalDate date, LocalTime time, Doctor doctor, Patient patient, double cost) {
		Appointment app = new Appointment(date, time, doctor, patient, cost); 
			phospital.getAppoints().add(app);
			patient.getAppoints().add(app);
			doctor.getAppoints().add(app);
			LOGGER.info("Appointment registered successfully");
		}		
	
	//method overload
	public void addAppointment (PublicHospital hospital, LocalDate date, LocalTime time, Doctor doctor, Patient patient) {
		Appointment app = new Appointment(date, time, doctor, patient);
		hospital.getAppoints().add(app);
		patient.getAppoints().add(app);
		doctor.getAppoints().add(app);
		LOGGER.info("Appointment registered successfully");			
	}
	
	public void removeAppointment (Hospital hospital, Appointment app) {
		if(hospital.getAppoints().contains(app)){
			hospital.getAppoints().remove(app);
			LOGGER.info("Appointment removed successfully");
		} else {
			throw new AppointmentNotFoundException("The appointment you wish to remove has not been found.");
		}
	}
}
