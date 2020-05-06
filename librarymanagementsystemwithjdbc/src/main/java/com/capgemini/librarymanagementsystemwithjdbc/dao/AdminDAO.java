package com.capgemini.librarymanagementsystemwithjdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestBean;

public interface AdminDAO {

	boolean addUser(LibraryUserBean userBean);

	LibraryUserBean login(String emailId, String password);

	boolean addBook(BookBean info);

	boolean removeBook(int bookId);

	BookBean searchBook(int bookId);

	List<LibraryUserBean> getAllUsers();

	List<BookBean> getAllBooks();

	List<RequestBean> getAllRequests();

	boolean isBookIssued(int requestId);

	boolean isBookReceived(int requestId);

}
