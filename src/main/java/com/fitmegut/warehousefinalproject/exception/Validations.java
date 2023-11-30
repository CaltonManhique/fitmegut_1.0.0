package com.fitmegut.warehousefinalproject.exception;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fitmegut.warehousefinalproject.model.User;

public class Validations {

	private boolean isEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean validateEmail(String email) throws EmailException {
		if (!isEmail(email)) {
			throw new EmailException("Email doesn't meet the requirements");
		}

		return isEmail(email);
	}

	public boolean validatePassword(String password) throws RegistrationException {
		if (password.length() < 8) {
			throw new RegistrationException("Password must be at least 8 characters long.");
		}
		if (!password.matches(".*[A-Z].*.*[0-9].*")) {
			throw new RegistrationException("Password must contain at least one uppercase letter and number.");
		}
		return password.matches(".*[A-Z].*.*[0-9].*");
	}

	private boolean existsEmailInUserTable(Set<User> users, String email) {
		return users.stream().anyMatch(e -> e.getEmail().equalsIgnoreCase(email));
	}

	private boolean passMatch(Set<User> users, String email, String password) {
		return users.stream().anyMatch(p -> p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password));
	}

	public boolean emailMatchs(Set<User> users, String email) throws LoginException {
		if (!existsEmailInUserTable(users, email)) {
			throw new LoginException("Email doesn't exist!!");
		}
		return existsEmailInUserTable(users, email);
	}

	public boolean passwordMatchs(Set<User> users, String email, String password) throws LoginException {
		if (!passMatch(users, email, password)) {
			throw new LoginException("Password incorrect!!");
		}
		return passMatch(users, email, password);
	}

	public String mandatoryField(String field) throws MandatoryFieldException {
		if (field.isBlank())
			throw new MandatoryFieldException("Mandatory field");

		return field;
	}

}
