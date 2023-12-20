package com.fitmegut.warehousefinalproject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitmegut.warehousefinalproject.exception.RegistrationException;

public class DbConnections {

	private Connection myConn = null;
	private PreparedStatement myStmt = null;
	private ResultSet myRs = null;

	private final static String dbUrl = "jdbc:mysql://localhost:3306/fitmegut";
	private final static String user = "root";
	private final static String pass = "1234";

	private String insertUserStatement = "INSERT INTO fitmegut.users(first_name,last_name,nickname,date_of_birth,gender,email,number_phone,\n"
			+ "country,city,address,user_type,password)\n" + "VALUES(?,?,?,?,?,?,?,?,?,\n" + "?,?,?)";

	private String selectToCheckEmail = "select email from fitmegut.users where email=?";

	public void dbConnect() throws SQLException {

		myConn = DriverManager.getConnection(dbUrl, user, pass);

		System.out.println("Database connected successfully!\n");

	}

	public String saveUser(String firstName, String lastName, String nickname, String dateOfbirth, String gender,
			String email, String phoneNumber, String country, String city, String address, String userType,
			String password) throws SQLException {

		String status = "";

		try {

			try {
				if (checkEmail(email) == null) {

					dbConnect();

					myStmt = myConn.prepareStatement(insertUserStatement);

					myStmt.setString(1, firstName);
					myStmt.setString(2, lastName);
					myStmt.setString(3, nickname);
					myStmt.setString(4, dateOfbirth);
					myStmt.setString(5, gender);
					myStmt.setString(6, email);
					myStmt.setString(7, phoneNumber);
					myStmt.setString(8, country);
					myStmt.setString(9, city);
					myStmt.setString(10, address);
					myStmt.setString(11, userType);
					myStmt.setString(12, password);

					int rowsAffected = myStmt.executeUpdate();

					System.out.println(rowsAffected + " ggka");

					status = rowsAffected > 0 ? "success" : "Error";
				}
			} catch (RegistrationException e) {

				System.out.println("Error: " + e.getMessage());
			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

		return status;

	}

	// If used alone, close myStmt, myRs and myConn.
	public String checkEmail(String email) throws RegistrationException, SQLException {
		dbConnect();

		String result = null;

		myStmt = myConn.prepareStatement(selectToCheckEmail);
		myStmt.setString(1, email);

		myRs = myStmt.executeQuery();

		while (myRs.next()) {
			result = myRs.getString("email");
		}

		if (result != null) {
			throw new RegistrationException("User already registered with this email.");
		}

		return result;
	}
}
