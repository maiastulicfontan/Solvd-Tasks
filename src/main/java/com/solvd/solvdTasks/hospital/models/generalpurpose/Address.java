package com.solvd.solvdTasks.hospital.models.generalpurpose;

public class Address {
	private String country;
	private String state;
	private String city;
	private String street;
	private int streetNumber;
	private int zipCode;
	
	
	public Address() {}
	
	public Address (String street, int streetNumber) {
		this.street = street;
		this.streetNumber = streetNumber;
	}
	
	public Address (String country, String state, String city, String street, int streetNumber) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
}
