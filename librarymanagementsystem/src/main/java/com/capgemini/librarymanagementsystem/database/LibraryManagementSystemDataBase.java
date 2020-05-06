package com.capgemini.librarymanagementsystem.database;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public class LibraryManagementSystemDataBase {

	public static final List<AdminBean> admin = new ArrayList<AdminBean>();
	public static final List<UserBean> users = new ArrayList<UserBean>();
	public static final List<BookBean> book = new ArrayList<BookBean>();
	public static final List<RequestBean> request = new ArrayList<RequestBean>();

	public static void addToDatabase() {

		admin.add(new AdminBean(1, "Priya", "priya@gmail.com", "Priya123@"));

		book.add(new BookBean(456, "Java", "James gosiling", "Neelima", "Technical", true));
		book.add(new BookBean(111, "Physics", "Verma", "Pearson", "Technical", true));
		book.add(new BookBean(222, "Maths", "Ramanujam", "Wiley", "Technical", true));
		book.add(new BookBean(333, "Three Mistakes Of My Life", "Savir sharma", "Pearson", "NonTechnical", false));

		users.add(new UserBean(100, "sweety", "Sweety123@", "sweety@gmail.com", 0, 0));
		users.add(new UserBean(101, "shwetha", "Shwetha123@", "shwetha@gmail.com", 1, 5));
		users.add(new UserBean(102, "chikki", "Chikki123@", "chikki@gmail.com", 3, 25));

	}
}
