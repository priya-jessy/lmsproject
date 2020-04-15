package com.capgemini.librarymanagementsystemwithjdbc.service;

import com.capgemini.librarymanagementsystemwithjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;
import com.capgemini.librarymanagementsystemwithjdbc.factory.LibraryManagementSystemFactory;

public class UserServiceImplemenatation implements UserService {

	private UserDAO dao = LibraryManagementSystemFactory.getUserDAO();

	@Override
	public LibraryUserBean login(String email, String password) {

		return dao.login(email, password);
	}

	@Override
	public BookBean searchById(int id) {

		return dao.searchById(id);
	}

	@Override
	public boolean bookReturn(int UserId, int bookId) {

		return dao.bookReturn(UserId, bookId);
	}

	@Override
	public RequestInfo bookRequest(int id, int bookId) {

		return dao.bookRequest(id, bookId);
	}

}
