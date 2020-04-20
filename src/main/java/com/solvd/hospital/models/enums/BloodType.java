package com.solvd.hospital.models.enums;

public enum BloodType{
	O_NEG("O-"), O_POS("O+"), A_NEG("A-"), A_POS("A+"), B_NEG("B-"), B_POS("B+"), AB_NEG("AB-"), AB_POS("AB+"), UNKNOWN("Unknown");
	
	private final String tag;
	
	private BloodType(String tag) { this.tag = tag; }
	
	public String getTag() {
		return tag;
	}

	@Override
	public String toString() { return tag; }
}
