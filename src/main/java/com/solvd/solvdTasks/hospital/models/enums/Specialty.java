package com.solvd.solvdTasks.hospital.models.enums;

public enum Specialty {
	ANAES("Anaesthetics"), CARD("Cardiology"), DERM("Dermatology"),
	ENDO ("Endocrinology"), GEN_PRAC ("General Practice"), GEN_SUR ("General Surgery"),
	IMMU("Immunology"), NEUR("Neurology"), PAED ("Paedriatics"), PLAS_SUR ("Plastic Surgery"),
	PSY ("Psychiatry"), URO ("Urology");
	
	private final String specLabel;
	
	private Specialty(String label) { this.specLabel = label;}
	
	public String getSpecLabel() {
		return specLabel;
	}

	@Override
	public String toString() { return specLabel; }
}
