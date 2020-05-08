package com.solvd.solvdTasks.hospital.models.healthfacilities;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.solvdTasks.hospital.models.exceptions.InvalidAmountException;
import com.solvd.solvdTasks.hospital.models.generalpurpose.BankAccount;
import com.solvd.solvdTasks.hospital.models.staff.administrative.Administrative;

public class PrivateHospital extends Hospital {
	private static Logger LOGGER = LogManager.getLogger(PrivateHospital.class);
	private BankAccount account;
	private List<Administrative> adms = new ArrayList<Administrative>();
	
	public PrivateHospital() {}
	
	public PrivateHospital(String name) {
		super(name);
		this.account = new BankAccount();
	}
	public PrivateHospital(String name, double balance) {
		super(name);
		this.account = new BankAccount();
		try {
			this.getAccount().setBalance(balance);
		} catch (InvalidAmountException e) {
			LOGGER.error(e);
		}
	}
	
	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public List<Administrative> getAdms() {
		return adms;
	}
	public void setAdms(ArrayList<Administrative> adms) {
		this.adms = adms;
	}
	
	public void addAdministrative (Administrative adm) {
		this.getAdms().add(adm);
		LOGGER.info("Employee registered successfully.");
	}
	
	public void removeAdministrative (Administrative adm) {
		if (this.getAdms().contains(adm)) {
			this.getAdms().remove(adm);
			LOGGER.info("Employee removed successfully");
		} else {
			LOGGER.info("Error. The employee you wish to remove does not exist");
		}	
	}
	
}
