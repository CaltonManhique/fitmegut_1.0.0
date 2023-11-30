package com.fitmegut.warehousefinalproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fitmegut.warehousefinalproject.model.Login;

public class LoginTest {

	@Test
	void sessionIdGeneratorTest() {
		Login login = new Login();
		assertEquals(101, login.sessionIdGenerator());
	}
}
