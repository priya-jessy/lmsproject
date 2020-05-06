package com.capgemini.librarymanagementsystem.dao;

import java.util.Calendar;
import java.util.Date;


import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;


public class UserDAOImplementation implements UserDAO {



	Date returnedDate;

	@Override
	public UserBean login(String email, String password) {
		for (UserBean userBean : LibraryManagementSystemDataBase.users)
			if (userBean.getEmail().equalsIgnoreCase(email) && userBean.getPassword().equals(password)) {
				return userBean;

			}
		throw new LibraryManagementSystemException("Invalid user credentials");
	}

	@Override
	public BookBean searchById(int id) {
		for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
			if (bookBean.getBookId() == id) {
				return bookBean;
			}
		}
		throw new LibraryManagementSystemException("Book not found");
	}

	@Override
	public boolean bookRequest(int userId, int bookId) {
		RequestBean requestInfo = new RequestBean();
		boolean isRequestExists = false;

		for (RequestBean info : LibraryManagementSystemDataBase.request) {
			if (bookId == info.getBookId()) {
				isRequestExists = true;
			}
		}

		if (!isRequestExists) {
			for (UserBean userBean : LibraryManagementSystemDataBase.users) {
				if (userId == userBean.getUserid()) {
					for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
						if ((bookBean.getBookId() == bookId) && (bookBean.isAvaliable() == true)) {
							requestInfo.setBookId(bookId);
							requestInfo.setUserId(userId);
							requestInfo.setIssued(false);
							LibraryManagementSystemDataBase.request.add(requestInfo);
							return true;
						}
					}
				}
			}
		}

		throw new LibraryManagementSystemException("Invalid Request");

	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		Calendar calendar2=Calendar.getInstance();
		calendar2.add(Calendar.DATE,20);
		returnedDate=calendar2.getTime();
		for (RequestBean requestInfo : LibraryManagementSystemDataBase.request) {

			if (requestInfo.getBookId() == bookId && requestInfo.getUserId() == userId
					&& requestInfo.isIssued() == true) {
				requestInfo.setReturned(true);
				requestInfo.setReturnedDate(returnedDate);
				return true;
			}

		}

		throw new LibraryManagementSystemException("Invalid Book return to library");

	}

	@Override
	public boolean changePassword(String emailId, String oldPassword, String newPassword) {

		for (UserBean userInfo : LibraryManagementSystemDataBase.users) {
			if ((userInfo.getEmail().equals(emailId)) && (userInfo.getPassword().equals(oldPassword))) {
				userInfo.setPassword(newPassword);
				return true;
			}
		}


		throw new LibraryManagementSystemException("Password Can't be Changed Due To Invalid Credentials");
	}

}
