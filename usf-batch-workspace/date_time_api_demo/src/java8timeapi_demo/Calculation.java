package java8timeapi_demo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Calculation {

	public static void main(String[] args) {
		
		LocalDate ld=LocalDate.now();
		System.out.println(ld);
		ld=ld.plusYears(5);
		System.out.println(ld);
		ld=ld.minusWeeks(20);
		System.out.println(ld);
		
		
		LocalDate dob=LocalDate.parse("12/09/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println("dob = "+dob);
		LocalDate curr=LocalDate.now();
		
		Period p=Period.between(dob, curr);
		System.out.println("Age is ");
		System.out.println(p.getYears()+" years"+p.getMonths()+" months"+p.getDays()+" days old");
		
		
		LocalDate dob2=LocalDate.parse("05/11/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println("before dob2 = "+dob2);
		Period p2=Period.between(dob2, curr);
		int age=p2.getYears();
		dob2=dob2.plusYears(age);
		System.out.println("age = "+age);
		System.out.println("after dob2 = "+dob2);
		
		long days=ChronoUnit.DAYS.between(dob2, curr);
		System.out.println(days);
		//LocalTime, LocalDateTime, Zone, Locale

	}

}
