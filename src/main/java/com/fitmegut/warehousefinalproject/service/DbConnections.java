package com.fitmegut.warehousefinalproject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fitmegut.warehousefinalproject.exception.LoginException;
import com.fitmegut.warehousefinalproject.exception.RegistrationException;
import com.fitmegut.warehousefinalproject.model.ClothingCategory;
import com.fitmegut.warehousefinalproject.model.Item;
import com.fitmegut.warehousefinalproject.model.ItemCondition;
import com.fitmegut.warehousefinalproject.model.User;

public class DbConnections {

	private Connection myConn = null;
	private PreparedStatement myStmt = null;
	private ResultSet myRs = null;

	private final static String dbUrl = "jdbc:mysql://localhost:3306/fitmegut";
	private final static String user = "root";
	private final static String pass = "1234";

	private String insertUserStatement = "INSERT INTO fitmegut.users(first_name,last_name,nickname,date_of_birth,gender,email,number_phone,\n"
			+ "country,city,address,user_type,password)\n" + "VALUES(?,?,?,?,?,?,?,?,?,\n" + "?,?,?)";

	private String selectToCheckEmail = "SELECT * FROM fitmegut.users WHERE email=?";

	private String insertSessionLogin = "INSERT INTO fitmegut.session_login(user_id,email,date,time,session_status)\n"
			+ "VALUES(?,?,?,?,?)";

	private String updateSessionLogOff = "UPDATE fitmegut.session_login SET date = ?, time = ?, session_status = false WHERE id = ?";

	private String insertItem = "INSERT INTO fitmegut.items(item_name,clothing_category,item_brand,size,color,item_condition,description,posted)\n"
			+ "VALUES(?,?,?,?,?,?,?,false)";

	private String createWardrobeEntry = "INSERT INTO fitmegut.wardrobe(member_id,item_id,clothing_category,item_brand,posted)\n"
			+ "VALUES(?,?,?,?,?);";

	public void dbConnect() throws SQLException {

		myConn = DriverManager.getConnection(dbUrl, user, pass);

		System.out.println("Database connected successfully!\n");

	}

	public String saveUser(String firstName, String lastName, String nickname, String dateOfbirth, String gender,
			String email, String phoneNumber, String country, String city, String address, String userType,
			String password) throws SQLException {

		String status = "";
		User user = null;

		try {

			dbConnect();

			try {

				user = checkEmail(email);

				if (user != null) {
					throw new RegistrationException("User already registered with this email.");
				}

				else {

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

					status = rowsAffected > 0 ? "success" : "Error";
				}

			} catch (RegistrationException e) {

				System.out.println("Error: " + e.getMessage());
			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
		} finally {
			if (myRs != null)
				myRs.close();

			if (myStmt != null)
				myStmt.close();

			if (myConn != null)
				myConn.close();

		}

		return status;

	}

	// If used alone, close myStmt, myRs and myConn.
	public User checkEmail(String email) throws RegistrationException, SQLException {

		User result = null;

		myStmt = myConn.prepareStatement(selectToCheckEmail);
		myStmt.setString(1, email);

		myRs = myStmt.executeQuery();

		while (myRs.next()) {
			result = new User(myRs.getLong("user_id"), myRs.getString("first_name"), myRs.getString("last_name"),
					myRs.getString("nickname"), myRs.getString("date_of_birth"), myRs.getString("gender"),
					myRs.getString("email"), myRs.getString("number_phone"), myRs.getString("country"),
					myRs.getString("city"), myRs.getString("address"), myRs.getString("user_type"),
					myRs.getString("password"));
		}

		return result;
	}

	// Returns session id
	public long[] performLogin(String email, String password) throws SQLException, LoginException {
		long[] ids = new long[2];
		User user = null;

		try {

			dbConnect();

			try {

				user = checkEmail(email);

				if (user == null) {
					throw new LoginException("User doesn't exist!! Please, sign up before login.");
				} else {

					if (user.getPassword().equals(password)) {

						// Insert an entry to the session table and retrieve the entry's key with
						// Statement.RETURN_GENERATED_KEYS
						myStmt = myConn.prepareStatement(insertSessionLogin, Statement.RETURN_GENERATED_KEYS);

						myStmt.setLong(1, user.getId());
						myStmt.setString(2, user.getEmail());
						myStmt.setString(3, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						myStmt.setString(4, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
						myStmt.setBoolean(5, true);

						myStmt.executeUpdate();
						ResultSet rs = myStmt.getGeneratedKeys();

						// If myStmt.executeUpdate() is success returns session id else 0;
						long sessionID = rs.next() ? rs.getLong(1) : 0;
						ids[0] = sessionID;
						ids[1] = user.getId();

					} else {

						throw new LoginException("Password doesn't match!");
					}
				}

			} catch (RegistrationException e) {
				e.getMessage();
			}

		} catch (SQLException e) {

			System.out.println("Error: " + e.getMessage());
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

		return ids;
	}

	public boolean logOff(long id) throws SQLException {
		boolean isLoggedOff = false;

		try {

			dbConnect();

			myStmt = myConn.prepareStatement(updateSessionLogOff);

			myStmt.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			myStmt.setString(2, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			myStmt.setLong(3, id);

			int rowsAffected = myStmt.executeUpdate();

			isLoggedOff = rowsAffected > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

		return isLoggedOff;
	}

	public Item insertItem(String itemName, ClothingCategory clothingCategories, String itemBrand, String size,
			String color, ItemCondition itemCondition, String description) throws SQLException {

		Item item = null;

		try {
			dbConnect();

			myStmt = myConn.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);

			myStmt.setString(1, itemName);
			myStmt.setString(2, clothingCategories.toString());
			myStmt.setString(3, itemBrand);
			myStmt.setString(4, size);
			myStmt.setString(5, color);
			myStmt.setString(6, itemCondition.getMeaning());
			myStmt.setString(7, description);

			myStmt.executeUpdate();
			ResultSet rs = myStmt.getGeneratedKeys();

			if (rs.next()) {
				long itemID = rs.getLong(1);

				item = new Item(itemID, itemName, clothingCategories, itemBrand, size, color, itemCondition,
						description, false);
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
//		} finally {
//			if (myRs != null)
//				myRs.close();
//
//			if (myStmt != null)
//				myStmt.close();
//
//			if (myConn != null)
//				myConn.close();
		}
		return item;
	}

	public void createWardrobeEntry(long userId, long itemId, ClothingCategory clothingCategories, String itemBrand,
			boolean posted) throws SQLException {

		try {
//			dbConnect();

			myStmt = myConn.prepareStatement(createWardrobeEntry);

			myStmt.setLong(1, userId);
			myStmt.setLong(2, itemId);
			myStmt.setString(3, clothingCategories.toString());
			myStmt.setString(4, itemBrand);
			myStmt.setBoolean(5, posted);

			myStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (myStmt != null)
				myStmt.close();

			if (myConn != null)
				myConn.close();

			if (myRs != null)
				myRs.close();
		}

	}
}
