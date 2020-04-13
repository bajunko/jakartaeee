package com.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Test {

	 public static void main(String[] args) {
		    LocalDate myObj = LocalDate.now(); // Create a date object
		    System.out.println(myObj); // Display the current date
		    
		    ZonedDateTime now = ZonedDateTime.now() ;
		    System.out.println(now);
		    
		    
		    
		    
		  TimeZone default1 = TimeZone.getDefault();
		  String id = default1.getID();
			
			
			System.out.println("\nTotal TimeZone ID " + id);
		    
		  }
	
}
