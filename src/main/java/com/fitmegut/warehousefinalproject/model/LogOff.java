package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fitmegut.warehousefinalproject.service.DbConnections;
import com.opencsv.exceptions.CsvValidationException;

public class LogOff {

//	private final String sessionFileName = "session.csv";
//	private FileReaderAndWriter fileReaderAndWriter;
//	private Set<Session> logins;

	private DbConnections dbConnections;

	public LogOff() {
//		fileReaderAndWriter = new FileReaderAndWriter();
//		logins = new HashSet<>();
		dbConnections = new DbConnections();
	}

	public void logOff(long sessionID) {
//		try {
//
//			// csv version
//			logins = fileReaderAndWriter.fileReaderSession(sessionFileName);
//
//			logins.forEach(s -> {
//				if (s.getSessionID() == sessionID) {
//					s.setLoggedIn(false);
//					s.setTime(LocalTime.now());
//					System.out.println("User logged off.");
//				}
//			});
//
//			fileReaderAndWriter.updateFileSession(logins, sessionFileName);
//
//		} catch (IOException | CsvValidationException e) {
//		}

		try {

			if (dbConnections.logOff(sessionID)) {
				System.out.println("User logged off.");

			} else {
				System.out.println("Error: Something went wrong, please try again!!!");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
