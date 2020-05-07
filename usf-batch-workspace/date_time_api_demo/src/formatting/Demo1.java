package formatting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1 {

	public static void main(String[] args) {
		
		Date d=new Date();
		System.out.println(d);
		
		/*
		 * dd - date   dd-05  d-5 , 10
		 * MM - month  MM-04  M-4 MMM-Jan  MMMM - January
		 * yy - year   yy-20  yyyy-2020
		 * hh - 12 hour format
		 * HH - 24 hour format
		 * mm -minutes
		 * ss - seconds
		 * a - AM/PM
		 * z/Z - zone info
		 * 
		 */
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMM-yy hh HH:mm:ss z Z a");
		System.out.println(sdf.format(d)); ///format-> takes dateobj and produces formatted date in String

	}

}
