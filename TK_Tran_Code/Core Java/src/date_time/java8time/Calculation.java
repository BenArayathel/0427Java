package date_time.java8time;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Calculation {

    public static void main(String[] args) {

        LocalDate ld = LocalDate.now(); // First local date
        System.out.println("Current date: " + ld);
        ld = ld.plusYears(5);
        System.out.println("After adding 5 years: " + ld);
        ld = ld.minusWeeks(20);
        System.out.println("After minusing 10 weeks: " + ld);

        // Protip: use parse() whenever reading input
        LocalDate dob = LocalDate.parse("12/10/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("dob = " + dob);

        LocalDate curr = LocalDate.now(); // Second local date
        Period p = Period.between(dob, curr); //  Returns time between two local dates.
        System.out.println("Age is : ");
        System.out.println(p.getYears() + " years " +  p.getMonths() + " months " + p.getDays() + " days old.\n");



        // Example using real birthday.
        LocalDate dob2 = LocalDate.parse("09/04/1993", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Born: " + dob2);
        Period p2 = Period.between(dob2, curr); // Compares birthday to current date
        int age = p2.getYears();
        dob2 = dob2.plusYears(age);
        System.out.println("Years old: " + age);
        System.out.println("Last birthday: " + dob2);
        long days = ChronoUnit.DAYS.between(dob2, curr); // Period does end-to-end calculation; ChronoUnit can return actual number of days.
        System.out.println("Days old: " + days);
        
    }
}