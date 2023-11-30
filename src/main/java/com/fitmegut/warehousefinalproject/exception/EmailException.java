package com.fitmegut.warehousefinalproject.exception;

import java.security.GeneralSecurityException;

public class EmailException extends GeneralSecurityException {

	private static final long serialVersionUID = 1L;

	public EmailException(String message) {
		super(message);
	}
}
