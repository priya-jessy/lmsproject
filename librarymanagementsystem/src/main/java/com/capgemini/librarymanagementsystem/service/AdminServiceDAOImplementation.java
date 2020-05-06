package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;

public class AdminServiceDAOImplementation implements AdminServiceDAO {
	private AdminDAO dao = LibraryManagementSystemFactory.getAdminDAO();

	@Override
	public boolean adminLogin(String adminEmail, String adminPassword) {

		return dao.adminLogin(adminEmail, adminPassword);
	}

	@Override
	public boolean addBook(BookBean info) {

		return dao.addBook(info);
	}

	@Override
	public boolean removeBook(int bookId) {

		return dao.removeBook(bookId);
	}

	@Override
	public boolean returnedBook(int bookId) {

		return dao.returnedBook(bookId);
	}

	@Override
	public boolean addUser(UserBean userBean) {

		return dao.addUser(userBean);
	}

	@Override
	public BookBean searchBook(int bookId) {

		return dao.searchBook(bookId);
	}

	@Override
	public List<UserBean> getAllUsers() {

		return dao.getAllUsers();
	}

	@Override
	public List<BookBean> getAllBooks() {

		return dao.getAllBooks();
	}

	@Override
	public List<RequestBean> getAllRequests() {

		return dao.getAllRequests();
	}

	@Override
	public boolean bookIssue(int userId, int bookId) {

		return dao.bookIssue(userId, bookId);
	}

	@Override
	public boolean isBookReceived(int userId, int bookId) {

		return dao.isBookReceived(userId, bookId);
	}

}
