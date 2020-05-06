package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.LibraryUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBean;
import com.capgemini.librarymanagementsystemhibernate.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemhibernate.fatory.LibraryManagementSystemFactory;

public class LibraryUserServiceImplementation implements LibraryUserService {

	private LibraryUserDAO dao = LibraryManagementSystemFactory.getLibraryUserDAO();

	@Override
	public LibraryUserBean login(String emailId, String password) {
		// TODO Auto-generated method stub
		return dao.login(emailId, password);
	}

	@Override
	public boolean addUser(LibraryUserBean info) {

		return dao.addUser(info);
	}

	@Override
	public List<LibraryUserBean> viewUsers() {

		return dao.viewUsers();
	}

	@Override
	public boolean addBook(BookBean bookDetails) {

		return dao.addBook(bookDetails);
	}

	@Override
	public BookBean search(int bookId) {

		return dao.search(bookId);
	}

	@Override
	public List<BookBean> viewBooks() {

		return dao.viewBooks();
	}

	@Override
	public List<RequestBean> viewRequests() {

		return dao.viewRequests();
	}

	@Override
	public boolean issueBook(int rid) {

		return dao.issueBook(rid);
	}

	@Override
	public boolean removeBook(int bookId) {

		return dao.removeBook(bookId);
	}

	@Override
	public boolean receivedBook(int requestId) {

		return dao.receivedBook(requestId);
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {

		return dao.bookReturn(userId, bookId);
	}

	@Override
	public boolean bookRequest(int id, int bookId) {

		return dao.bookRequest(id, bookId);
	}

	@Override
	public boolean changePassword(int id, String oldPassword, String newPassword) {

		return dao.changePassword(id, oldPassword, newPassword);
	}

}
