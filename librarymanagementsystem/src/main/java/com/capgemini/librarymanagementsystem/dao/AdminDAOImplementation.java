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
	Date expectedReturnedDate ;
	Date returnedDate;

	@Override
	public boolean adminLogin(String adminEmail, String adminPassword) {
		AdminBean adminBean = new AdminBean();
		if (adminBean.getAdminEmail().equals(adminEmail) && adminBean.getAdminPassword().equals(adminPassword)) {
			return true;
		}

		throw new LibraryManagementSystemException("Invalid admin credentials");
	}

	@Override
	public boolean addBook(BookBean info) {
		for (BookBean bookBean : LibraryManagementSystemDataBase.book) {
			if (bookBean.getBookId() == info.getBookId()) {
				throw new LibraryManagementSystemException("Cannot add Book beacause Book with same id is already existing in library");
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

		throw new LibraryManagementSystemException("Can't remove the Book,Book Id not found");
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
				throw new LibraryManagementSystemException("User with same id is already registered in library");

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
		throw new LibraryManagementSystemException("Book is not present in library");
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
			throw new LibraryManagementSystemException("No users present in library");
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
			throw new LibraryManagementSystemException("No books found in library");
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
		Calendar calendar = Calendar.getInstance();
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
				LibraryManagementSystemDataBase.request.remove(requestInfo);
				throw new LibraryManagementSystemException(
						" Book cannot be issued due to Student Exceeds maximum Borrowing limit");
			}

		} else {
			throw new LibraryManagementSystemException("Book Can't be Issued due to invalid userid or bookid");
		}

	}

	@Override
	public boolean isBookReceived(int userId, int bookId) {
		boolean isBookReceived = false;
		RequestInfo requestInfo = new RequestInfo();
		int noOfBooksBorrowed;
		double fine;

		for (RequestInfo requestInfo1 : LibraryManagementSystemDataBase.request) {
			if (requestInfo1.getBookId() == bookId && requestInfo1.getUserId() == userId
					&& requestInfo1.isReturned() == true) {
				isBookReceived = true;
				expectedReturnedDate=requestInfo1.getExpectedReturnedDate();
				returnedDate=requestInfo1.getReturnedDate();
				requestInfo = requestInfo1;
			}
		}

		if (isBookReceived) {
			long expReturnDate=expectedReturnedDate.getTime();
			long returnDate=returnedDate.getTime();
			long diffIndays=returnDate-expReturnDate;
			int NoOfDaysDelayed=(int)(diffIndays/(24*60*60*1000));
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
					fine=userBean.getFine();
					if(NoOfDaysDelayed>0) {
						fine=fine+(NoOfDaysDelayed*5);
					}
					userBean.setFine(fine);
					break;
				}
			}

			LibraryManagementSystemDataBase.request.remove(requestInfo);
			return true;
		}

		throw new LibraryManagementSystemException("Book can't be received as invalid userid or bookid ");

	}

}
