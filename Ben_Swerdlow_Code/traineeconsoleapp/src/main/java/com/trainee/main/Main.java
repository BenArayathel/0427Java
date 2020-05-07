package com.trainee.main;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.trainee.dao.impl.TraineeDAOImpl;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;
import com.trainee.services.impl.TraineeServiceImpl;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Trainee Management Console App V1.0");
		System.out.println("----------------------------------------------");
		int ch = 0;
		System.out.println("Trainee Menu");
		System.out.println("------------");
		TraineeServiceImpl service = new TraineeServiceImpl();
		
		do {
			System.out.println("1) Create Trainee");
			System.out.println("2) Search Trainee by Id");
			System.out.println("3) Search Trainee by Contact");
			System.out.println("4) Delete Trainee");
			System.out.println("5) Search Trainees by City");
			System.out.println("6) List All Trainees");
			System.out.println("7) EXIT");
			
			String choice = sc.nextLine();
			try {
				ch = Integer.parseInt(choice);
				switch (ch) {
				case 1:
					Trainee trainee = new Trainee();
					System.out.println("Enter Name");
					trainee.setName(sc.nextLine());
					System.out.println("Enter Contact Phone Number");
					try {
						trainee.setContact(Long.parseLong(sc.nextLine()));
					} catch (NumberFormatException e) {
						System.out.println("Phone number must be a 10-digit number. Please try again."); 
					}
					System.out.println("Enter email");
					trainee.setEmail(sc.nextLine());
					System.out.println("Enter date of birth in dd.MM.yyyy format only (eg. 01.01.2000).");
					Date dob = null;
					try {
						dob = TraineeServiceImpl.makeValidDate(sc.nextLine());
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					trainee.setDob(dob);
					System.out.println("Enter city");
					trainee.setCity(sc.nextLine());
//					System.out.println(trainee);
					try {
						trainee = service.createTrainee(trainee);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println("Enter trainee id to get trainee details");
					Trainee t = null;
					try {
						t = service.getTraineeById(sc.nextLine());
						System.out.println(t);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Thank you for your interest. This is still under construction.");
					break;
				case 4:
					System.out.println("Thank you for your interest. This is still under construction.");
					break;
				case 5:
					System.out.println("Thank you for your interest. This is still under construction.");
					
					break;
				case 6:
					List<Trainee> traineeList = null;
					try {
						traineeList = new TraineeDAOImpl().getAllTrainees();
					} catch (BusinessException e) {
						e.getMessage();
					}
					if (traineeList!=null && traineeList.size()>0) {
						System.out.println("We have the following "+traineeList.size()+" trainees:");
					}
					break;
				case 7:
					
					break;
				default: System.out.println("Entered choice should be between 1-6");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid choice. Please try again.");
			}
			
		} while (ch!=7);
		
		sc.close();
	}

}
