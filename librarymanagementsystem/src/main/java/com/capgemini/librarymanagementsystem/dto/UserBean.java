package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBean implements Serializable {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private int numberOfBooks;
	private double fine;

	public UserBean() {

	}

	public UserBean(int userId, String userName, String password, String email, int numberOfBooks, double fine) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.numberOfBooks = numberOfBooks;
		this.fine = fine;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserid() {
		return userId;
	}

	public void setUserid(int userid) {
		this.userId = userid;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", numberOfBooks=" + numberOfBooks + ", fine=" + fine + "]";
	}

}
