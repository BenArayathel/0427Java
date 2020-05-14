package main;

import dao.TraineeDAOImpl;
import exception.BusinessException;
import model.Trainee;
import service.TraineeServiceImpl;

import java.util.List;
import java.util.Scanner;

/*
	Presentation Layer
 */
public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Trainee Management Console App v1.0");
		System.out.println("----------------------------------------------");
		TraineeServiceImpl service = new TraineeServiceImpl();
		int selection = 0;
		do {
			System.out.println("Trainee Menu");
			System.out.println("------------");
			System.out.println("1. Create Trainee");
			System.out.println("2. Search Trainee by ID");
			System.out.println("3. Update Trainee Contact");
			System.out.println("4. Delete Trainee");
			System.out.println("5. Search Trainee by City");
			System.out.println("6. List ALL Trainees");
			System.out.println("7. Exit");
			try {
				selection = Integer.parseInt(sc.nextLine()); // Best practice
				switch (selection) {
					case 1:
						Trainee trainee = new Trainee();
						try {
							System.out.println("Enter name: ");
							trainee.setName(sc.nextLine());
							System.out.println("Enter contact number: ");
							trainee.setContact(Long.parseLong(sc.nextLine())); // Best practice
							System.out.println("Enter email: ");
							trainee.setEmail(sc.nextLine());
							System.out.println("Enter DOB in MM.dd.yyyy format: ");
							String dob = sc.nextLine();
							trainee.setDob(service.isValidDate(dob));
							System.out.println("Enter city: ");
							trainee.setCity(sc.nextLine());
							if (trainee.getId() != null) {
								System.out.println("Trainee registered!");
								System.out.println(trainee);
							}
						} catch (NumberFormatException e) {
							System.out.println("Contact should be numbers only.");
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2:
						System.out.println("Enter trainee ID: ");
						String id = sc.nextLine();
						try {
							Trainee trainee2 = service.getTraineeByID(id);
							if (trainee2 != null) {
								System.out.println("Trainee with ID " + id + " + found!");
								System.out.println(trainee2);
							}
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						System.out.println("Still under construction.");
						break;
					case 4:
						System.out.println("Still under construction.");
						break;
					case 5:
						System.out.println("Still under construction.");
						break;
					case 6:
						try {
							List<Trainee> traineeList = new TraineeDAOImpl().getAllTrainees();
							if (traineeList != null && traineeList.size() > 0) {
								System.out.println("Total trainees: " + traineeList.size());
								System.out.println("Printing details..");
								for (Trainee t : traineeList) {
									System.out.println(t);
								}
							}
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 7:
						System.exit(0);
						System.out.println("Thanks for using the app!");
						break;
					default:
						System.out.println("Selection must be between 1-7.");
						break;
				}
			} catch (NumberFormatException e) {
				//
			}
		} while (selection != 7);
	}
}
