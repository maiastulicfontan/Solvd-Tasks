package com.solvd.hospital.models;

import com.solvd.hospital.models.enums.BloodType;
import com.solvd.hospital.models.enums.Disease;
import com.solvd.hospital.models.enums.Gender;
import com.solvd.hospital.models.enums.Specialty;
import com.solvd.hospital.models.exceptions.AppointmentNotFoundException;
import com.solvd.hospital.models.exceptions.EmployeeNotFoundException;
import com.solvd.hospital.models.healthfacilities.PrivateHospital;
import com.solvd.hospital.models.healthfacilities.Hospital;
import com.solvd.hospital.models.interfaces.functionalinterfaces.IBiCheck;
import com.solvd.hospital.models.interfaces.functionalinterfaces.ICalculate;
import com.solvd.hospital.models.interfaces.functionalinterfaces.IModify;
import com.solvd.hospital.models.interfaces.functionalinterfaces.IPrint;
import com.solvd.hospital.models.patientrelated.Patient;
import com.solvd.hospital.models.staff.Employee;
import com.solvd.hospital.models.staff.administrative.Accountant;
import com.solvd.hospital.models.staff.administrative.Receptionist;
import com.solvd.hospital.models.staff.medicalstaff.Doctor;
import com.solvd.hospital.models.staff.medicalstaff.Nurse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HospitalRunner {

	private static Logger LOGGER = LogManager.getLogger(HospitalRunner.class);

	public static void main (String[] args) {
		LOGGER.info("Starting program");
		Random rd = new Random();
				
		//initializing Private Hospitals
		PrivateHospital privHospA = new PrivateHospital("St. Patrick's Hospital");
		privHospA.getAccount().setBalance(1000000.00);
		
		PrivateHospital privHospB = new PrivateHospital("St. Mary's Private Hospital", -87);
		
		//initializing employees
		Doctor docA = new Doctor ("Katy","Hodges", rd.nextInt(99999), 2117863245L, Specialty.CARD);
		docA.setSalary(5000.00);
		Doctor docB = new Doctor ("Edward", "Harris", rd.nextInt(99999), 4568754698L, Specialty.URO);
		docB.setSalary(4500.00);
		Doctor docC = new Doctor ("Mary","Bell", rd.nextInt(99999), 1115863245L, Specialty.DERM);
		Doctor docD = new Doctor ("Edward", "Johnson", rd.nextInt(99999), 4568754698L, Specialty.URO);
		Nurse nurseA = new Nurse ("Carl", "Miller", rd.nextInt(99999), rd.nextInt(9999999));
		Nurse nurseB = new Nurse ("Frank", "Jameson", rd.nextInt(99999), rd.nextInt(9999999));
		Receptionist recA = new Receptionist ("Sarah","Paulson",rd.nextInt(99999));
		Accountant accA = new Accountant ("Marcellus", "Wallace", rd.nextInt(99999));
		
		privHospA.addDoctor(docA);
		privHospA.addDoctor(docB);
		privHospA.addNurse(nurseA);
		privHospA.addAdministrative(recA);
		privHospA.addAdministrative(accA);
		
		
		privHospB.addDoctor(docC);
		privHospB.addDoctor(docD);
		privHospB.addNurse(nurseB);
			
		//initializing patients
		Patient patA = new Patient ("Mary", "Brown", LocalDate.of(1985,03,11), Gender.F, BloodType.AB_POS, 30000.00);
		Patient patB = new Patient ("Richard", "Wilson", LocalDate.of(1972,05,03), Gender.M, BloodType.O_NEG, "Medicare");
		Patient patC = new Patient ("Joe", "Black", LocalDate.of(1990,11,25), Gender.UNKNOWN, BloodType.A_POS, 100);
		Patient patD = new Patient ("Jane", "Doe", LocalDate.of(1995,06, 29), Gender.F, BloodType.UNKNOWN);
	
		//adding appointments
		recA.addAppointment(privHospA, LocalDate.of(2020,03,28), LocalTime.of(9, 30), docB, patB, 150);
		recA.addAppointment(privHospA, LocalDate.of(2020,04,28), LocalTime.of(14,30), docA, patA, 200);		
		recA.addAppointment(privHospA, LocalDate.of(2020,04,10), LocalTime.of(17,00), docB, patC, 150);
		recA.addAppointment(privHospA, LocalDate.of(2020,04,15), LocalTime.of(11,00), docB, patD, 170);
		
		/* Some exception handling notes: i haven't declared 'throws' in my methods since these exceptions are runtime ones,
		 * similar to NullPointerException or ArithmeticException. Also I'm aware that i could have managed these situations within
		 my methods but I didn't just for the sake of this task. Furthermore, I know that it's the best practice to add a finally block
		 but since I'm not using any kind of resources that i need to release, I don't know what would be the use of a finally block
		 here*/
		//trying to remove an appointment that doesn't belong to the hospital
		try {
			recA.removeAppointment(privHospB,privHospA.getAppoints().get(1));
		} catch (AppointmentNotFoundException e){
			LOGGER.error(e);
		}
		
		//trying to remove a nurse that doesn't work in that hospital
		try {
			privHospB.removeNurse(nurseA);
		} catch (EmployeeNotFoundException e) {
			LOGGER.error(e);
		}
		
		//attending appointments
		docA.attendAppointment(docA.getAppoints().get(0), patA);	
		docB.attendAppointment(docB.getAppoints().get(0), patB);
		docB.attendAppointment (docB.getAppoints().get(1), patC);
		
		try {
			docB.attendAppointment(docA.getAppoints().get(rd.nextInt(docA.getAppoints().size())), patC);
		} catch (AppointmentNotFoundException e) {
			LOGGER.error(e);
		}
		
		try {
			docB.attendAppointment(docB.getAppoints().get(2), patD);
		} catch (AppointmentNotFoundException e) {
			LOGGER.error(e);
		}
		nurseA.curePatient(patC, Disease.getRandomDisease());
		
		accA.payEmployee(privHospA, nurseA);
		
		
		//lambdas
		ICalculate<PrivateHospital, Double> calculateAppointmentsIncome = tmpPrivHosp -> {
			return tmpPrivHosp.getAppoints().stream().collect(Collectors.summingDouble(appointment -> appointment.getCost()));
		};
		
		IModify<Employee,Double> increaserByPercentage = (employee, percentage) -> employee.setSalary(employee.getSalary()+employee.getSalary()*percentage/100);
		IModify<Employee,Double> increaserByAmount = (employee, amount) -> employee.setSalary(employee.getSalary()+amount);
		
		accA.increaseSalary(docA, 5,increaserByPercentage);
		accA.increaseSalary(docB, 300, increaserByAmount);
		
		IPrint<Hospital> doctorPrinter = hospital -> hospital.getDoctors().forEach((doctor)->LOGGER.info(doctor));
		  
		LOGGER.info("Total appointments' income in "+privHospA.getName()+": $"+calculateAppointmentsIncome.calculate(privHospA));
		
		doctorPrinter.print(privHospA);
		
		IBiCheck<Doctor,String> checkDoctorLastName = (doctor, prefix)-> doctor.getLastName().startsWith(prefix);
		
		privHospB.getDoctors().forEach((doctor)->{
			if (checkDoctorLastName.check(doctor, "J")) {
				LOGGER.info(doctor.getLastName());
			}
		});
	}
}
