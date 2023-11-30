package com.fitmegut.warehousefinalproject.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Session {

	private Long sessionID;
	private Long userID;
	private String email;
	private LocalDate date;
	private LocalTime time;
	private boolean loggedIn;

	public Session(long sessionID, long userID, String email, LocalDate date, LocalTime time, boolean loggedIn) {
		this.sessionID = sessionID;
		this.userID = userID;
		this.email = email;
		this.date = date;
		this.time = time;
		this.loggedIn = loggedIn;
	}

	public long getSessionID() {
		return sessionID;
	}

	public long getUserID() {
		return userID;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(sessionID, other.sessionID);
	}

}
