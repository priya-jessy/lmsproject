package com.capgemini.librarymanagementsystem.dao;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;
import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public class AdminDAOImplementation implements AdminDAO {
	Date date = new Date();
	Date expectedReturnedDate = new Date();
	Date returnedDate = new Date();
	Calendar calendar = Calendar.getInstance();
	Date actualReturnDate = calendar.getTime();

	@Override
	public boolean adminLogin(String adminEmail, String adminPassword) {
		AdminBean adminBean = new AdminBean();
		if (adminBean.getAdminEmail().equals(adminEmail) && adminBean.getAdminPassword().equals(adminPassword)) {
			return true;
		}

		throw new LibraryManagementSystemException("Invalid credentials");
	}

	@Override
	public boolean addBook(BookBean info) {
		for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
			if (bookBean.getBookId() == info.getBookId()) {
				throw new LibraryManagementSystemException("Book already exists");
			}
		}
		LibraryManagementSystemDataBase.book.add(info);
		return true;
	}

	@Override
	public boolean removeBook(int bookId) {

		for (BookBean bookin : LibraryManagementSystemDataBase.book) {
			if (bookin.getBookId() == bookId) {
				LibraryManagementSystemDataBase.book.remove(bookin);
				return true;
			}
		}

		throw new LibraryManagementSystemException("Book cannot be removed");
	}

	@Override
	public boolean returnedBook(int bookId) {
		for (BookBean books : LibraryManagementSystemDataBase.book) {
			if (books.getBookId() == bookId) {
				return true;
			}
		}
		throw new LibraryManagementSystemException("Book cannot be returned");
	}

	@Override
	public boolean addUser(UserBean userBean) {
		for (UserBean userInfo : LibraryManagementSystemDataBase.users) {
			if ((userInfo.getUserid() == userBean.getUserid())) {
				throw new LibraryManagementSystemException("User already exists");

			}

		}
		LibraryManagementSystemDataBase.users.add(userBean);

		return true;
	}

	@Override
	public BookBean searchBook(int bookId) {
		for (BookBean bookBeans : LibraryManagementSystemDataBase.book) {
			if (bookBeans.getBookId() == bookId) {
				return bookBeans;
			}
		}
		throw new LibraryManagementSystemException("Book not found");
	}

	@Override
	public List<UserBean> showUsers() {

		List<UserBean> infos = new LinkedList<UserBean>();

		for (UserBean userInfo : LibraryManagementSystemDataBase.users) {
			userInfo.getUserid();
			userInfo.getUsername();
			userInfo.getEmail();
			userInfo.getNumberOfBooks();
			infos.add(userInfo);

		}
		if(infos.isEmpty()) {
			throw new LibraryManagementSystemException("No users are present in library");
		}
		return infos;

	}

	@Override
	public List<BookBean> showBooks() {
		List<BookBean> booksList = new LinkedList<BookBean>();
		for (BookBean book : LibraryManagementSystemDataBase.book) {

			book.getBookId();
			book.getBookName();
			book.getAuthorName();
			booksList.add(book);

		}
		if(booksList.isEmpty()) {
			throw new LibraryManagementSystemException("No books present in library");
		}
		return booksList;
	}

	@Override
	public List<RequestInfo> showRequests() {
		List<RequestInfo> reqInfo = new LinkedList<RequestInfo>();
		for (RequestInfo requestInfo : LibraryManagementSystemDataBase.request) {
			requestInfo.getBookBean();
			requestInfo.getUserBean();
			requestInfo.isIssued();
			requestInfo.isReturned();
			requestInfo.getIssuedDate();
			requestInfo.getExpectedReturnedDate();
			requestInfo.getReturnedDate();
			reqInfo.add(requestInfo);

		}
		if(reqInfo.isEmpty()) {
			throw new LibraryManagementSystemException("No Requests are found");
		}

		return reqInfo;
	}

	@Override
	public boolean bookIssue(int userId, int bookId) {
		UserBean user = new UserBean();
		BookBean book = new BookBean();
		RequestInfo requestInfo = new RequestInfo();
		int noOfBooksBorrowed = 0;
		boolean isValidRequest = false;
		calendar.add(Calendar.DATE, 15);
		expectedReturnedDate = calendar.getTime();

		for (RequestInfo info : LibraryManagementSystemDataBase.request) {
			if (info.getUserId() == userId) {
				if (info.getBookId() == bookId) {
					isValidRequest = true;
					requestInfo = info;
				}
			}
		}

		if (isValidRequest) {
			for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
				if (bookBean.getBookId() == bookId) {
					book = bookBean;
				}
			}

			for (UserBean userBean : LibraryManagementSystemDataBase.users) {
				if (userBean.getUserid() == userId) {
					user = userBean;
					noOfBooksBorrowed = user.getNumberOfBooks();
				}
			}

			if (noOfBooksBorrowed < 3) {
				book.setAvaliable(false);
				noOfBooksBorrowed++;
				user.setNumberOfBooks(noOfBooksBorrowed);
				requestInfo.setIssued(true);
				requestInfo.setIssuedDate(date);
				requestInfo.setExpectedReturnedDate(expectedReturnedDate);
				return true;
			} else {
				throw new LibraryManagementSystemException(
						" Book cannot be issued due toStudent Exceeds maximum Borrowing limit");
			}

		} else {
			throw new LibraryManagementSystemException("Book Can't Be Issued");
		}

	}

	@Override
	public boolean isBookReceived(int userId, int bookId) {
		boolean isBookReceived = false;
		RequestInfo requestInfo = new RequestInfo();
		int noOfBooksBorrowed;

		for (RequestInfo requestInfo1 : LibraryManagementSystemDataBase.request) {
			if (requestInfo1.getBookId() == bookId && requestInfo1.getUserId() == userId
					&& requestInfo1.isReturned() == true) {
				isBookReceived = true;
				requestInfo = requestInfo1;
			}
		}

		if (isBookReceived) {
			for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
				if (bookBean.getBookId() == bookId) {
					bookBean.setAvaliable(true);
					break;
				}
			}

			for (UserBean userBean : LibraryManagementSystemDataBase.users) {
				if (userBean.getUserid() == userId) {
					noOfBooksBorrowed = userBean.getNumberOfBooks();
					noOfBooksBorrowed--;
					userBean.setNumberOfBooks(noOfBooksBorrowed);
					break;
				}
			}

			LibraryManagementSystemDataBase.request.remove(requestInfo);
			return true;
		}

		throw new LibraryManagementSystemException("Book is Not Received ");

	}

}
