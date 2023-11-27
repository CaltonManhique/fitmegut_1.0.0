package com.fitmegut.warehousefinalproject.model;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String nickname;
	private String dateOfbirth; // Date
	private String gender; // radio button
	private String email;
	private String phoneNumber;
	private String country;
	private String city;
	private String address;
	private String userType; // Private or company
	private String password;

	public User(String firstName, String lastName, String nickname, String dateOfbirth, String gender, String email,
			String phoneNumber, String country, String city, String address, String userType, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.dateOfbirth = dateOfbirth;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.city = city;
		this.address = address;
		this.userType = userType;
		this.password = password;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public String getDateOfbirth() {
		return dateOfbirth;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getUserType() {
		return userType;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setDateOfbirth(String dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
