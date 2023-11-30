package com.fitmegut.warehousefinalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fitmegut.warehousefinalproject.exception.EmailException;
import com.fitmegut.warehousefinalproject.exception.RegistrationException;
import com.fitmegut.warehousefinalproject.model.HomePage;
import com.fitmegut.warehousefinalproject.model.Login;
import com.fitmegut.warehousefinalproject.model.Registration;

//@SpringBootApplication
public class DciWarehouseFinalProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DciWarehouseFinalProjectApplication.class, args);

		new HomePage().runApp();
	}

}
