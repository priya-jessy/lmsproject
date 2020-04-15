package com.capgemini.librarymanagementsystem.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public class UserDAOImplementation implements UserDAO {

	Date returnedDate = new Date();

	@Override
	public UserBean login(String email, String password) {
		for (UserBean userBean : LibraryManagementSystemDataBase.users)
			if (userBean.getEmail().equals(email) && userBean.getPassword().equals(password)) {
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
		throw new LibraryManagementSystemException("Invalid search");
	}

	@Override
	public boolean bookRequest(int userId, int bookId) {
		RequestInfo requestInfo = new RequestInfo();
		boolean isRequestExists = false;

		for (RequestInfo info : LibraryManagementSystemDataBase.request) {
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

		throw new LibraryManagementSystemException("Request Can't be place to Admin");

	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		for (RequestInfo requestInfo : LibraryManagementSystemDataBase.request) {

			if (requestInfo.getBookId() == bookId && requestInfo.getUserId() == userId
					&& requestInfo.isIssued() == true) {
				requestInfo.setReturned(true);
				requestInfo.setReturnedDate(returnedDate);
				return true;
			}

		}

		throw new LibraryManagementSystemException("Invalid Book Return");

	}

}
