package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class AdminBean implements Serializable {
	private int adminId = 1;
	private String adminName = "Priya";
	private String adminEmail = "priya@gmail.com";
	private String adminPassword = "Priya@123";

	public int getAdminid() {
		return adminId;
	}

	public void setAdminid(int adminid) {
		this.adminId = adminid;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}
