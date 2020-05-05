package formatting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1 {

	public static void main(String[] args) {
		
		// Java.util.Date ---> Java.sql.date
		// when within java, use Java.util.date
		// when interacting with database, use Java.sql.date
		
		// Default constructor captures the current date as
		// Day Month DD HH:MM:SS TimeZone YYYY
		Date d = new Date();
		
		System.out.println(d);
		
		/*
		 * dd - day		-->  05, 10
		 * d - day		-->  5, 10
		 * D - day of the year	--> 126
		 * (same pattern of repeated D's as above?) 
		 * MM - month	--> 04, 05, 10
		 * M - month	--> 4
		 * MMM - month	--> Jan
		 * MMMM - month --> January
		 * yy - 20 yyy - 2020
		 * 
		 * hh - 12 hour format
		 * HH - 24 hour format
		 * mm - minutes
		 * ss - seconds
		 * 
		 * a - AM/PM
		 * z - time zone info --> EDT/EST
		 * Z - time zone info relative to GMT --> -0400 
		 * 
		 */
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy");
		System.out.println(sdf.format(d)); // format -> takes dateobj and produces formatted date in String
		
		System.out.println(new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a Z").format(d));
		
		// Date validation
		

	}

}
