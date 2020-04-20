package com.solvd.hospital.models.staff;

import java.time.LocalDate;
import com.solvd.hospital.models.generalpurpose.BankAccount;
import com.solvd.hospital.models.generalpurpose.Person;

public abstract class Employee extends Person {
	
	private int id;
	private LocalDate startDate;
	private double salary;
	private BankAccount account;
	
	
	public Employee() {
		this.account = new BankAccount();
	}
	
	public Employee(String firstName, String lastName, int id) {
		super(firstName, lastName);
		this.id = id;
		this.account = new BankAccount();
	}
	
	public Employee(String firstName, String lastName, int id, LocalDate startDate) {
		super(firstName, lastName);
		this.id = id;
		this.startDate = startDate;
		this.account = new BankAccount();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
}
