package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.fitmegut.warehousefinalproject.exception.EmailException;
import com.fitmegut.warehousefinalproject.exception.LoginException;
import com.fitmegut.warehousefinalproject.exception.RegistrationException;
import com.fitmegut.warehousefinalproject.exception.Validations;
import com.fitmegut.warehousefinalproject.service.DbConnections;
//import com.opencsv.exceptions.CsvValidationException;

public class Login {

//	private final String sessionFileName = "session.csv";
//	private final String userFileName = "users.csv";

	private Validations validations = new Validations();
	private Scanner scanner = new Scanner(System.in);
//	private FileReaderAndWriter fileReaderAndWriter;
	private LoggedHomePage loggedHomePage = new LoggedHomePage();;
	private DbConnections dbConnections;

	private long sessionID = 100;
	private long currentSessionID = 0;
//	private User loggedUser = new User();

//	private Set<User> users;

	private Set<Session> logins;

	public Login() {
		logins = new HashSet<>();
//		users = new HashSet<User>();
//		fileReaderAndWriter = new FileReaderAndWriter();
		dbConnections = new DbConnections();
	}

	public long sessionIdGenerator() {
		return logins.isEmpty() ? sessionID : sessionID + 1;
	}

	public Long getLastSessionID() {
		return logins.stream().mapToLong(Session::getSessionID).max().getAsLong();
	}

	public long getCurrentSessionID() {
		return currentSessionID;
	}

	public void resetPassword(User user, String password) {
		try {
			if (validations.validatePassword(password)) {
				user.setPassword(password);
			}
		} catch (RegistrationException e) {
			System.out.println(e.getMessage());
		}

	}

	// csv version
//	private User getUser(String email) {
//		User user = null;
//		for (User us : users) {
//			if (us.getEmail().strip().equals(email)) {
//				user = us;
//				break;
//			}
//		}
//		return user;
//	}
//
//	public User getloggedUser() {
//		return loggedUser;
//	}

	// csv version
//	public String performLogin(long sessionID, String email, String password, LocalDate date, LocalTime time) {
//		Session session = null;
//		String status = "";
//
//		try {
//
//			if (validations.emailMatchs(users, email) && validations.passwordMatchs(users, email, password)) {
//
//				loggedUser = getUser(email);
//				session = new Session(sessionID, loggedUser.getId(), loggedUser.getEmail(), date, time, true);
//				logins.add(session);
//
//				fileReaderAndWriter.fileWriterSession(session, sessionFileName);
//
//				status = "success";
//
//			}
//		} catch (LoginException e) {
//			status = "Error: " + e.getMessage();
//		} catch (IOException e) {
//			status = "Error: " + e.getMessage();
//		}
//
//		return status;
//	}

	public void login() throws EmailException, IOException {

		String email = null;
		do {

			System.out.println("Enter email:");
			email = scanner.nextLine();

		} while (!validations.validateEmail(email));

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		try {

			long[] ids = dbConnections.performLogin(email, password);
			long sessionID = ids[0];
			long userID = ids[1];

			if (sessionID > 0) {

				// redirection to member home page
				loggedHomePage.loggedMember(sessionID, userID);
			} else {
				System.out.println("Error: Problem occurred while login in. Please, try again.");
			}

		} catch (LoginException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
