package com.capgemini.librarymanagementsystemwithjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestBean;

public interface AdminService {

	boolean addUser(LibraryUserBean user1);

	LibraryUserBean login(String emailId, String password);

	boolean addBook(BookBean info);

	boolean removeBook(int bookId);

	BookBean searchBook(int bookId);

	List<LibraryUserBean> showUsers();

	List<BookBean> showBooks();

	List<RequestBean> showRequests();

	boolean isBookIssued(int requestId);

	boolean isBookReceived(int requestId);

}
