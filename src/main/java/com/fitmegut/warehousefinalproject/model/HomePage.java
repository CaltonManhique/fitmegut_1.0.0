package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.fitmegut.warehousefinalproject.exception.EmailException;
import com.fitmegut.warehousefinalproject.exception.RegistrationException;

public class HomePage {

	private final String homepage = "src/main/resources/static/homepage.txt";

	private Login login = new Login();
	private Registration registration = new Registration();

	private Scanner scanner = new Scanner(System.in);

	public void runApp() {

		try {
			Files.lines(Paths.get(homepage)).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int option = 0;

		do {
			option = scanner.nextInt();

			switch (option) {
			case 1 -> {

				try {
					String str = registration.regist();
					System.out.println(str);

					System.out.println("Sign in to access more features.");

				} catch (RegistrationException | EmailException e) {
					System.out.println(e.getMessage());
				}

			}
			case 2 -> {

				try {
					login.login();
				} catch (EmailException | IOException e) {
					System.out.println(e.getMessage());
				}

			}
			case 3 -> System.out.println("Donation - to be implemented");
			case 4 -> System.out.println("Exchange - to be implemented");
			case 5 -> System.out.println("Closing app...");
			default -> throw new IllegalArgumentException("Unexpected value: " + option);
			}
		} while (option != 5);

		scanner.close();

	}

}
