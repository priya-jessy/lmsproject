package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;

public class UserServiceDAOImplementation implements UserServiceDAO {

	private UserDAO dao = LibraryManagementSystemFactory.getUserDao();

	@Override
	public UserBean login(String email, String password) {

		return dao.login(email, password);
	}

	@Override
	public BookBean searchById(int id) {

		return dao.searchById(id);
	}

	@Override
	public boolean bookRequest(int userId, int bookId) {

		return dao.bookRequest(userId, bookId);
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {

		return dao.bookReturn(userId, bookId);
	}

	@Override
	public boolean changePassword(String emailId, String oldPassword, String newPassword) {

		return dao.changePassword(emailId, oldPassword, newPassword);
	}
}
