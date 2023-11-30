package com.fitmegut.warehousefinalproject.model;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;

public class LogOff {

	private final String sessionFileName = "session.csv";
	private FileReaderAndWriter fileReaderAndWriter;
	private Set<Session> logins;

	public LogOff() {
		fileReaderAndWriter = new FileReaderAndWriter();
		logins = new HashSet<>();
	}

	public void logOff(long sessionID) {
		try {

			// DB access to session info
			logins = fileReaderAndWriter.fileReaderSession(sessionFileName);

			logins.forEach(s -> {
				if (s.getSessionID() == sessionID) {
					s.setLoggedIn(false);
					s.setTime(LocalTime.now());
					System.out.println("User logged off.");
				}
			});

			fileReaderAndWriter.updateFileSession(logins, sessionFileName);

		} catch (IOException | CsvValidationException e) {
		}
	}

}
