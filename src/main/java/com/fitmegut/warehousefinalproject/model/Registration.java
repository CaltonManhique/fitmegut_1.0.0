package com.fitmegut.warehousefinalproject.model;

//import java.io.IOException;
import java.sql.SQLException;
//import java.util.HashSet;
import java.util.Scanner;
//import java.util.Set;

import com.fitmegut.warehousefinalproject.exception.EmailException;
import com.fitmegut.warehousefinalproject.exception.RegistrationException;
import com.fitmegut.warehousefinalproject.exception.Validations;
import com.fitmegut.warehousefinalproject.service.DbConnections;
//import com.opencsv.exceptions.CsvValidationException;

public class Registration {

//	private Set<User> users;
//	private FileReaderAndWriter fileReaderAndWriter;
	private Scanner scanner = new Scanner(System.in);
	private Validations validations = new Validations();

	private DbConnections dbConnections;

//	private Long id = 1000L;

	public Registration() {
//		users = new HashSet<User>();
//		fileReaderAndWriter = new FileReaderAndWriter();
		dbConnections = new DbConnections();
	}

//	private final String fileName = "users.csv";

	// csv version
//	public boolean checkEmail(String email) throws RegistrationException {
//		boolean doesntExists = true;
//
//		// Db select to the users table
//		for (User user : users) {
//			if (user.getEmail().equalsIgnoreCase(email))
//				doesntExists = false;
//		}
//
//		if (!doesntExists) {
//			throw new RegistrationException("User already registered with this email.");
//		}
//		return doesntExists;
//	}

	// csv version
//	public long idGenerator() {
//		return users.isEmpty() ? id : id + 1;
//	}

	// csv version
//	public Long getLastId() {
//		return users.stream().mapToLong(User::getId).max().orElse(0);
//	}

//	// csv version
//	public String saveUser(String firstName, String lastName, String nickname, String dateOfbirth, String gender,
//			String email, String phoneNumber, String country, String city, String address, String userType,
//			String password) {
//
//		String status = "";
//
//		try {
//
//			if (checkEmail(email)) {
//
//				User user = new User(idGenerator(), firstName, lastName, nickname, dateOfbirth, gender, email,
//						phoneNumber, country, city, address, userType, password);
//				users.add(user);
//
//				// DB
//				fileReaderAndWriter.fileWriterUser(user, fileName);
//
//				status = "User successfully registered!!";
//
//			}
//
//		} catch (RegistrationException e) {
//			status = "Error: " + e.getMessage();
//		} catch (IOException e) {
//			status = "Error: " + e.getMessage();
//		}
//
//		return status;
//
//	}

	// returns success or Error
	public String regist() throws RegistrationException, EmailException {

//		//csv version
//		try {
//
//			// DB
////			users = fileReaderAndWriter.fileReaderUser(fileName); // Temporary db
//			// id = getLastId();
//
//		} catch (CsvValidationException | IOException e) {
//
//		}

		String registrationStatus = "";

		// Stub
		System.out.println("Enter first name:");
		String firstName = scanner.nextLine();

		System.out.println("Enter first name:");
		String lastName = scanner.nextLine();

		System.out.println("Enter nickname:");
		String nickname = scanner.nextLine();

		System.out.println("Enter date of birth:");
		String dateOfBirth = scanner.nextLine();

		System.out.println("Enter gender:");
		String gender = scanner.nextLine();

		String email = null;
		do {

			System.out.println("Enter email:");
			email = scanner.nextLine();

		} while (!validations.validateEmail(email));

		System.out.println("Enter number phone:");
		String numberPhone = scanner.nextLine();

		System.out.println("Enter country:");
		String country = scanner.nextLine();

		System.out.println("Enter city:");
		String city = scanner.nextLine();

		System.out.println("Enter address:");
		String address = scanner.nextLine();

		System.out.println("Choose user type (Private or Company):");
		String userType = scanner.nextLine();

		String password = null;
		do {

			System.out.println("Enter password:");
			password = scanner.nextLine();

		} while (!validations.validatePassword(password));

		try {
			registrationStatus = dbConnections.saveUser(firstName, lastName, nickname, dateOfBirth, gender, email,
					numberPhone, country, city, address, userType, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return registrationStatus;
	}
}
