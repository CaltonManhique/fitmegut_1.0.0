package com.fitmegut.warehousefinalproject.exception;

import java.security.GeneralSecurityException;

public class LoginException extends GeneralSecurityException {

	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}

}
