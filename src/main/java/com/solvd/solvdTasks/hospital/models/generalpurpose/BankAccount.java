package com.solvd.solvdTasks.hospital.models.generalpurpose;

import com.solvd.solvdTasks.hospital.models.exceptions.InsufficientFundsException;
import com.solvd.solvdTasks.hospital.models.exceptions.InvalidAmountException;

public class BankAccount {
	private int number;
	private String bankName;
	private double balance;
	
	public BankAccount () {}
	
	public BankAccount (double balance) {
		this.setBalance(balance);
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double accountBalance) {
		if (accountBalance >= 0) {
			this.balance = accountBalance;
		} else {
			throw new InvalidAmountException("Account balance can't be less than zero");
		}
	}
	public void deposit (double ammount) {
		if (ammount >= 0) {
			this.setBalance(this.getBalance() + ammount);
		} else {
			throw new InvalidAmountException("Can't deposit a negative amount");
		}
	}
	public void withdraw (double ammount) {
		if (ammount <= this.getBalance()) {
			this.setBalance(this.getBalance() - ammount);
		} else {
			throw new InsufficientFundsException("Can't perform transaction: funds are insufficient");
		}
	}

}
