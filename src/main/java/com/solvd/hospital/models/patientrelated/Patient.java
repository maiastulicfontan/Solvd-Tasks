package com.solvd.hospital.models.patientrelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.models.enums.BloodType;
import com.solvd.hospital.models.enums.Gender;
import com.solvd.hospital.models.exceptions.AccountNotFoundException;
import com.solvd.hospital.models.generalpurpose.BankAccount;
import com.solvd.hospital.models.generalpurpose.Person;

public class Patient extends Person {
	private static final Logger LOGGER = LogManager.getLogger(Patient.class);

	private String healthInsuranceCompany;
	private BankAccount account;
	private BloodType bloodType;
	private MedicalRecord medRecord;
	private boolean isSick;
	private List<Appointment> appoints = new ArrayList<Appointment>();
	private List<Prescription> prescs = new ArrayList<Prescription>();
	
	//constructors
	public Patient() {}
	
	public Patient(String firstName, String lastName, LocalDate birthDate, Gender gender, BloodType bloodType) {
		super(firstName, lastName, birthDate, gender);
		this.bloodType = bloodType;
		Random rd = new Random();
		this.isSick = rd.nextBoolean();
	}
	
	public Patient(String firstName, String lastName, LocalDate birthDate, Gender gender, BloodType bloodType, double balance) {
		super(firstName, lastName, birthDate, gender);
		this.bloodType = bloodType;
		Random rd = new Random();
		this.isSick = rd.nextBoolean();
		this.account = new BankAccount();
		account.setBalance(balance);
	}
	
	public Patient(String firstName, String lastName, LocalDate birthDate, Gender gender, BloodType bloodType, String healthinsurance) {
		super(firstName, lastName, birthDate, gender);
		this.bloodType = bloodType;
		Random rd = new Random();
		this.isSick = rd.nextBoolean();
		this.healthInsuranceCompany = healthinsurance;
	}
	

	//setters and getters
	public String getHealthInsuranceCompany() {
		return healthInsuranceCompany;
	}

	public void setHealthInsuranceCompany(String healthInsuranceCompany) {
		this.healthInsuranceCompany = healthInsuranceCompany;
	}

	public BankAccount getAccount(){
			return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public MedicalRecord getMedRecord() {
		return medRecord;
	}

	public void setMedRecord(MedicalRecord medRecord) {
		this.medRecord = medRecord;
	}

	public boolean isSick() {
		return isSick;
	}

	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}


	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public List<Appointment> getAppoints() {
		return appoints;
	}

	public void setAppoints(ArrayList<Appointment> appoints) {
		this.appoints = appoints;
	}
	
	public List<Prescription> getPrescs() {
		return prescs;
	}

	public void setPrescs(List<Prescription> prescs) {
		this.prescs = prescs;
	}

	public void doTreatment (Treatment treatment) {
		Random rd = new Random();
		this.setSick(rd.nextBoolean());
		if (this.isSick() == false) {
			treatment.setEffective(true);
			LOGGER.info("The patient has healed of " + treatment.getDisease().getName());
		} else {
			treatment.setEffective(false);
			LOGGER.info("The patient has not healed of " + treatment.getDisease().getName());
		}
		if (this.getMedRecord()!= null) {
			this.getMedRecord().getTreatments().add(treatment);
		} else {
			MedicalRecord medRecord = new MedicalRecord();
			medRecord.getTreatments().add(treatment);
			this.setMedRecord(medRecord);
		}
	}
	
	public void payAppointment(Appointment app) {
		if (this.getAccount() == null) {
			throw new AccountNotFoundException("Patient does not own a bank account");
		} 
		this.getAccount().withdraw(app.getCost());
	}

	@Override
	public String toString() {
		return "Patient information:\n" + "First name: " + getFirstName() + "\nLast name: " + getLastName() + 
		"\nDate of birth: " + getBirthDate() + "\nGender: " + getGender() + "\nBlood Type: " + bloodType;
	}
	
	
	
}


