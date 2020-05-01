package com.capgemini.librarymanagementsystemwithjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemwithjdbc.dao.AdminDAO;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;

import com.capgemini.librarymanagementsystemwithjdbc.factory.LibraryManagementSystemFactory;

public class AdminServiceImplementation implements AdminService {
	private AdminDAO dao = LibraryManagementSystemFactory.getAdminDAO();

	@Override
	public boolean addUser(LibraryUserBean userBean) {

		return dao.addUser(userBean);
	}

	@Override
	public LibraryUserBean login(String emailId, String password) {

		return dao.login(emailId, password);
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
	public BookBean searchBook(int bookId) {

		return dao.searchBook(bookId);
	}

	@Override
	public List<LibraryUserBean> showUsers() {

		return dao.showUsers();
	}

	@Override
	public List<BookBean> showBooks() {

		return dao.showBooks();
	}

	@Override
	public List<RequestInfo> showRequests() {

		return dao.showRequests();
	}

	@Override
	public boolean bookIssue(int requestId) {

		return dao.bookIssue(requestId);
	}

	@Override
	public boolean isBookReceived(int requestId) {

		return dao.isBookReceived(requestId);
	}

}
