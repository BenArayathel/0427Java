package com.trainee.main;

import java.util.List;
import java.util.Scanner;

import com.trainee.dao.impl.TraineeDaoImpl;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;
import com.trainee.service.TraineeService;
import com.trainee.service.impl.TraineeServiceImpl;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Trainee Management Console App V1.0");
		System.out.println("--------------------------------------------------");
		TraineeService service = new TraineeServiceImpl();
		int ch = 0;
		do {
			System.out.println("Trainee Menu");
			System.out.println("-------------");
			System.out.println("1)Create Trainee");
			System.out.println("2)Search Trainee By Id");
			System.out.println("3)Update Trainee Contact");
			System.out.println("4)Delete Trainee");
			System.out.println("5)Search Trainees By City");
			System.out.println("6)List All Trainees");
			System.out.println("7)EXIT");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {

			case 1:
				Trainee trainee = new Trainee();
				System.out.println("Enter Name");
				trainee.setName(scanner.nextLine());
				try {
					System.out.println("Enter Contact");
					trainee.setContact(Long.parseLong(scanner.nextLine()));
					System.out.println("Enter email");
					trainee.setEmail(scanner.nextLine());
					System.out.println("Enter Dob in dd.MM.yyyy format only");
					String dob = scanner.nextLine();
					trainee.setDob(TraineeServiceImpl.isValidDate(dob));
					System.out.println("Enter city");
					trainee.setCity(scanner.nextLine());
					trainee = service.createTrainee(trainee);
					if (trainee.getId() != null) {
						System.out.println("Trainee Registered here is the details");
						System.out.println(trainee);
					}
				} catch (NumberFormatException e) {
					System.out.println("Contact should be alphabets only kindly re-enter");
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter Trainee Id to get trainee details");
				String id=scanner.nextLine();
				try {
					Trainee trainee2=service.getTraineeById(id);
					if(trainee2!=null) {
						System.out.println("Trainee with id "+id+" found...");
						System.out.println(trainee2);
					}
				} catch (BusinessException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 3:
				System.out.println("Thankq for your interest this is still under construction");

				break;
			case 4:
				System.out.println("Thankq for your interest this is still under construction");

				break;
			case 5:
				System.out.println("Thankq for your interest this is still under construction");

				break;
			case 6:
				try {
					List<Trainee> traineeList = new TraineeDaoImpl().getAllTrainees();
					if (traineeList != null && traineeList.size() > 0) {
						System.out.println("We have total of " + traineeList.size() + " no of trainee/s.. details are");
						for (Trainee t : traineeList) {
							System.out.println(t);
						}
					}

				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 7:
				System.out.println("Thankq for using our App.......");
				System.exit(0);
				break;

			default:
				System.out.println("Entered choice should be between(1-7)");
				break;
			}
			ch = 0;

		} while (ch != 7);

	}
}
