package com.capgemini.librarymanagementsystem.controller;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;

public class LmsController {
	public static void main(String[] args) {
		LibraryManagementSystemDataBase.addToDatabase();
		LibraryManagementSystem.lmsController();
		
	}

}
