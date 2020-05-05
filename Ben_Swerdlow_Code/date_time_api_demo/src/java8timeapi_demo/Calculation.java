package java8timeapi_demo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Calculation {

	public static void main(String[] args) {
		
		// The following is the Java 8 standard
		
		// LocalDate only focuses on date, not time or timezone, etc.
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		ld = ld.plusYears(5);
		System.out.println(ld);
		ld = ld.minusWeeks(2);
		System.out.println(ld);
		
		// not lenient by default :)
		LocalDate dob = LocalDate.parse("12/10/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println("dob "+dob);
		
		
		LocalDate curr = LocalDate.now();
		DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currDateString = ddMMyyyy.format(curr);
		System.out.println("Today's date: "+currDateString);
		
		Period p = Period.between(dob, curr);
		
		System.out.println("Age is "+p.getYears()+" years "+p.getMonths()+" months "+p.getDays()+" days ");
		System.out.println("Age is "+p.getYears()+" years ");
		
		LocalDate dob2 = LocalDate.parse("05/11/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Period p2 = Period.between(dob2, curr);
		int age = p2.getYears();
		dob2 = dob2.plusYears(age);
		System.out.println("Age "+age);
		System.out.println("after dob 2 = "+dob2);
		// User ChronoUnit to get chronological units of days (total number of days between dates)
		long days = ChronoUnit.DAYS.between(dob2, curr);
		long months = ChronoUnit.MONTHS.between(dob2, curr); // always rounds down the months (5 months and 27 days will return 5)
		System.out.println("Months until birthday: "+months);
		System.out.println("Days until birthday: "+days);
		
		
		
		// LocalTime only focuses on time not date
		
		// LocalDateTime does the whole shebang

	}

}
